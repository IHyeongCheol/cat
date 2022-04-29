package com.project.cat.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.cat.board.model.service.BoardService;
import com.project.cat.board.model.vo.Board;
import com.project.cat.common.Paging;
import com.project.cat.common.SearchDate;
import com.project.cat.member.model.vo.Member;



@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	
	//게시글 페이지단위로 목록 조회 처리용
	@RequestMapping("blist.do")
	public ModelAndView boardListMethod(@RequestParam(name="page", required=false) String page, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}			
		//패이징 계산 처리 -- 별도 클래스로 작성해서 사용해도 됨----------
		int limit = 10; //한 페이지에 출력할 목록 갯수
		//페이지 수 계산을 위해 총 목록갯수 조회
		int listCount = boardService.selectListCount();
		//페이지 수 계산
		//주의 : 목록이 11개 이면, 페이지 수는 2가됨
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 포함된 페이지 그룹의 시작값 지정 (뷰 아래쪽에 표시할 페이지 수를 10개씩 한 경우)
		int startPage = (int)((double)currentPage / 10 + 0.9);
		//현재 페이지가 포함된 페이지그룹의 끝값
		int endPage = startPage + 10 - 1;
		//페이징처리 분류
		String menu = "board";
			
		if(maxPage < endPage) {
			endPage = maxPage;
		}
			

		//쿼리문에 전달할 현재 페이지에 출력할 목록의 첫행과 끝행 객체 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow);
		//-----------------------------------------
		//서비스 메소드 실행하고 결과 받기
		ArrayList<Board> list = boardService.selectList(paging);
			
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);
			mv.addObject("menu", menu);
			mv.setViewName("board/boardListView");
		}else {
			mv.addObject("message", currentPage + "페이지 목록 조회 실패");
			mv.setViewName("common/commonview");
		}
		return mv;
	}
		
	
	//게시글 쓰기 페이지로 이동
	@RequestMapping("bwform.do")
	public String boardWrite() {
		return "board/boardWrite";
	}
		
		
	//게시글 등록 처리 : 파일 첨부
	@RequestMapping(value="binsert.do", method=RequestMethod.POST)
	public String boardInsertMethod(Board board, HttpServletRequest request, Model model, @RequestParam(name="upfile", required=false) MultipartFile mfile) {
		//업로드된 파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		System.out.println("확인1:"+savePath);
		System.out.println("확인1:"+!mfile.isEmpty());
		//첨부파일이 있을때만 업로드된 파일을 지정 폴더로 옮기기
		if(!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			System.out.println("확인1:"+fileName);
			//이름바꾸기 처리 : 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {
				//바꿀 파일명에 대한 문자열 만들기
				//공지글 등록 요청시점의 날짜정보를 이용함
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				//변경할 파일이름 만들기
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "_" + board.getUser_id();
				//원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				
				//파일 객체 만들기
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				
				System.out.println("확인1:"+renameFileName);
				//업로드 파일 저장시키고, 바로 이름바꾸기 실행함
				try {
					mfile.transferTo(renameFile);

				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "전송파일 저장 실패.");
					return "common/commonview";
				}
				
				board.setBoard_original_img(fileName);
				board.setBoard_rename_img(renameFileName);
			}
		}  //첨부파일이 있을때만

		if(boardService.insertOriginBoard(board) > 0) {
			//새 게시글 등록 성공시
			return "redirect:blist.do";
		}else {
			model.addAttribute("message", "새 게시글 등록 실패");
			return "common/commonview";
		}
		
	}
		
		
	//게시글 상세보기
	@RequestMapping("bdetail.do")
	public String boardDetailMethod(@RequestParam("board_no") int board_no, Model model, HttpSession session){
		Board board = boardService.selectBoard(board_no);
		
			//조회수 1증가 처리
		boardService.updateAddReadcount(board_no);
						
		if(board != null) {
			model.addAttribute("board", board);
					
			Member loginMember = (Member)session.getAttribute("loginMember");
					
			return "board/boardDetail";
				
		}else {
			model.addAttribute("message", board_no + "번 게시글 상세보기 실패");
			return "common/error";
		}
	}
		
	//첨부파일 다운로드 요청 처리용
	@RequestMapping("bfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("ofile") String originFileName, @RequestParam("rfile") String renameFileName, ModelAndView mv) {
		//공지사항 첨부파일 저장 폴더 경로 지정
		System.out.println("확인이다");
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles/");
		//저장 폴더에서 읽을 파일에 대해 경로 추가
		File renameFile = new File(savePath + renameFileName);
		//다운을 위해 내보내는 파일 객체 생성
		File originFile = new File(originFileName);
		
		System.out.println("경로"+renameFile);

		//filedown - servlet-context.xml 파일 다운 위한 뷰클래스 id명
		mv.setViewName("filedown");	//등록된 파일다운로드 처리용 뷰 클래스 id 명
		mv.addObject("renameFile", renameFile); //전달할 파일객체 Model에 저장
		mv.addObject("originFile", originFile);
			
		return mv;
	}
		
		
	//글 삭제
	@RequestMapping("bdel.do")
	public String boardDeleteMethod(Board board, HttpServletRequest request, Model model) {
		if(boardService.deleteBoard(board) > 0) {
			//글삭제 성공시 저장폴더에 첨부파일도 삭제 처리
			if(board.getBoard_rename_img() != null) {
				new File(request.getSession().getServletContext().getRealPath("resources/board_upfiles") + "\\" + board.getBoard_rename_img()).delete();
			}
			return "redirect:blist.do?page=1";
		}else{
			model.addAttribute("message", board.getBoard_no() + "번 글 삭제 실패");
			return "common/commonview";
		}
	}
		
		
	//글 수정 이동
	@RequestMapping("bupview.do")
	public String moveBoardUpdateView(@RequestParam("board_no") int board_no, Model model) {
		Board board = boardService.selectBoard(board_no);
		if(board != null) {
			model.addAttribute("board", board);
	
			return "board/boardUpdate";
		}else {
			model.addAttribute("message", board_no + "번 글 수정페이지로 이동 실패");
			return "common/commonview";
		}
	}
		
	//글 수정
	@RequestMapping(value="boardupdate.do", method=RequestMethod.POST)
	public String boardUpdateMethod(Board board, HttpServletRequest request, Model model, @RequestParam(name="delFlag", required=false) String delFlag, @RequestParam(name="upfile", required=false) MultipartFile mfile) {
		//업로드된 파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		
		//첨부파일 수정 처리 -----------------------------
		//업로드된 파일 저장 폴더 지정
		System.out.println("확인1:"+savePath);
		System.out.println("확인1:"+!mfile.isEmpty());
		//첨부파일이 있을때만 업로드된 파일을 지정 폴더로 옮기기
		if(!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			System.out.println("확인1:"+fileName);
			//이름바꾸기 처리 : 년월일시분초.확장자
			if(fileName != null && fileName.length() > 0) {
				//바꿀 파일명에 대한 문자열 만들기
				//공지글 등록 요청시점의 날짜정보를 이용함
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				//변경할 파일이름 만들기
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "_" + board.getUser_id();
				//원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				
				//파일 객체 만들기
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				
				System.out.println("확인1:"+renameFileName);
				//업로드 파일 저장시키고, 바로 이름바꾸기 실행함
				try {
					mfile.transferTo(renameFile);

				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "전송파일 저장 실패.");
					return "common/commonview";
				}
				board.setBoard_original_img(fileName);
				board.setBoard_rename_img(renameFileName);
			}
		}

		//기존 첨부파일 있고 삭제를 선택한 경우
		if(board.getBoard_original_img() != null && delFlag != null && delFlag.equals("yes")) {
			//저장 폴더에서 해당 파일을 삭제함
			new File(savePath + "\\" + board.getBoard_rename_img()).delete();
			//board의 파일도 제거
			board.setBoard_original_img(null);
			board.setBoard_rename_img(null);
		}
		//--------------------------------------------------------------
		//서비스 메소드 실행시키고 결과받아서 성공 | 실패 페이지 내보내기
		if(boardService.updateOrigin(board) > 0) {
			//원글 수정 성공시 상세페이지로
			return "redirect:blist.do";
		}else {
			model.addAttribute("message", board.getBoard_no() + "번 게시글 수정 실패");
			return "common/commonview";
		}
		
	}	
		
		
	@RequestMapping(value="btop3.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardReadCountTop3(HttpServletResponse response) throws UnsupportedEncodingException {
		//조회수 많은 게시 원글 3개 조회해 옴
		ArrayList<Board> list = boardService.selectTop3();
		//전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		//list 옮길 json 배열 준비
		JSONArray jarr = new JSONArray();
		
		//list 를 jarr 로 옮기기(복사)
		for(Board board: list) {
			//notice 필드값 저장용 json 객체 생성
			JSONObject job = new JSONObject();
			
			job.put("board_no", board.getBoard_no());
			job.put("board_title", URLEncoder.encode(board.getBoard_title(), "utf-8"));
			//한글 데이터는 반드시 인코딩해서 json에 담아야 한글이 깨지지 않음
			job.put("board_readcount", board.getBoard_readcount());
			
			jarr.add(job);  //job를 jarr에 저장
		}
		
		//전송용 객체에 jarr을 담음
		sendJson.put("list", jarr);
		System.out.println("인기게시글 : "+list);
		return sendJson.toJSONString();  //json 을 json string 형으로 바꿔서 전송함
		//뷰리졸버에게로 리턴됨	
	}


	// 제목 검색 페이징
	@RequestMapping("bsearchTitle.do")
	public ModelAndView searchTitleMethod(@RequestParam(name="page", required=false) String page, @RequestParam("keyword") String keyword, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//패이징 계산 처리 -- 별도 클래스로 작성해서 사용해도 됨----------
		int limit = 10; //한 페이지에 출력할 목록 갯수
		//페이지 수 계산을 위해 총 목록갯수 조회
		int listCount = boardService.searchTitleCount(keyword);
		System.out.println("제목  " + keyword);
		//페이지 수 계산
		//주의 : 목록이 11개 이면, 페이지 수는 2가됨
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 포함된 페이지 그룹의 시작값 지정 (뷰 아래쪽에 표시할 페이지 수를 10개씩 한 경우)
		int startPage = (int)((double)currentPage / 10 + 0.9);
		//현재 페이지가 포함된 페이지그룹의 끝값
		int endPage = startPage + 10 - 1;
		// 검색어
		String searchkeyword  = keyword;
		//페이징처리 분류
		String menu = "board";
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
	
		//쿼리문에 전달할 현재 페이지에 출력할 목록의 첫행과 끝행 객체 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow, searchkeyword);
		//-----------------------------------------
		//서비스 메소드 실행하고 결과 받기
		ArrayList<Board> list = boardService.selectSearchTitle(paging);

		
		System.out.println("검색" + listCount +",  키워드 : " + searchkeyword);
		
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);
			mv.addObject("menu", menu);
			mv.addObject("action", "bsearchTitle");
			mv.addObject("keyword", searchkeyword);
			mv.setViewName("board/boardListView");
		}else {
			mv.addObject("message", currentPage + "페이지 목록 조회 실패");
			mv.setViewName("common/commonview");
		}
		return mv;
	}

	// 작성자 검색 페이징
	@RequestMapping("bsearchWriter.do")
	public ModelAndView searchWriterMethod(@RequestParam(name="page", required=false) String page, @RequestParam("keyword") String keyword, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//패이징 계산 처리 -- 별도 클래스로 작성해서 사용해도 됨----------
		int limit = 10; //한 페이지에 출력할 목록 갯수
		//페이지 수 계산을 위해 총 목록갯수 조회
		int listCount = boardService.searchWriterCount(keyword);
		System.out.println("작성자   " + keyword);
		//페이지 수 계산
		//주의 : 목록이 11개 이면, 페이지 수는 2가됨
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 포함된 페이지 그룹의 시작값 지정 (뷰 아래쪽에 표시할 페이지 수를 10개씩 한 경우)
		int startPage = (int)((double)currentPage / 10 + 0.9);
		//현재 페이지가 포함된 페이지그룹의 끝값
		int endPage = startPage + 10 - 1;
		// 검색어
		String searchkeyword  = keyword;
		//페이징처리 분류
		String menu = "board";
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
	
		//쿼리문에 전달할 현재 페이지에 출력할 목록의 첫행과 끝행 객체 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow, searchkeyword);
		
		System.out.println(paging);
		//-----------------------------------------
		//서비스 메소드 실행하고 결과 받기
		ArrayList<Board> list = boardService.selectSearchWriter(paging);

		
		System.out.println("검색" + listCount +",  키워드 : " + searchkeyword);
		System.out.println(list);
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);
			mv.addObject("menu", menu);
			mv.addObject("action", "bsearchWriter");
			mv.addObject("keyword", searchkeyword);
			mv.setViewName("board/boardListView");
		}else {
			mv.addObject("message", currentPage + "페이지 목록 조회 실패");
			mv.setViewName("common/commonview");
		}
		return mv;
	}
		
	
	// 날짜 검색 페이징
//		@RequestMapping("bsearchDate.do")
//		public ModelAndView searchDateMethod(@RequestParam(name="page", required=false) String page, SearchDate date, ModelAndView mv) {
//			int currentPage = 1;
//			if(page != null) {
//				currentPage = Integer.parseInt(page);
//			}
//			
//			//패이징 계산 처리 -- 별도 클래스로 작성해서 사용해도 됨----------
//			int limit = 10; //한 페이지에 출력할 목록 갯수
//			//페이지 수 계산을 위해 총 목록갯수 조회
//			int listCount = boardService.searchDateCount(date);
//			System.out.println("검색" + listCount);
//			//페이지 수 계산
//			//주의 : 목록이 11개 이면, 페이지 수는 2가됨
//			int maxPage = (int)((double)listCount / limit + 0.9);
//			//현재 페이지가 포함된 페이지 그룹의 시작값 지정 (뷰 아래쪽에 표시할 페이지 수를 10개씩 한 경우)
//			int startPage = (int)((double)currentPage / 10 + 0.9);
//			//현재 페이지가 포함된 페이지그룹의 끝값
//			int endPage = startPage + 10 - 1;
//			// 검색어
//			Date begin = date.getBegin();
//			Date end = date.getEnd();
//			//페이징처리 분류
//			String menu = "board";
//			
//			if(maxPage < endPage) {
//				endPage = maxPage;
//			}
//
//
//			//쿼리문에 전달할 현재 페이지에 출력할 목록의 첫행과 끝행 객체 처리
//			int startRow = (currentPage - 1) * limit + 1;
//			int endRow = startRow + limit - 1;
//			Paging paging = new Paging(startRow, endRow, begin, end);
//			//-----------------------------------------
//			//서비스 메소드 실행하고 결과 받기
//			ArrayList<Board> list = boardService.selectSearchDate(paging);
//
//			
//			System.out.println(list);
//			
//			if(list != null && list.size() > 0) {
//				mv.addObject("list", list);
//				mv.addObject("listCount", listCount);
//				mv.addObject("maxPage", maxPage);
//				mv.addObject("currentPage", currentPage);
//				mv.addObject("startPage", startPage);
//				mv.addObject("endPage", endPage);
//				mv.addObject("limit", limit);
//				mv.addObject("menu", menu);
//				mv.addObject("action", "bsearchDate");
//				mv.addObject("begin", begin);
//				mv.addObject("end", end);
//				mv.setViewName("board/boardListView");
//			}else {
//				mv.addObject("message", currentPage + "페이지 목록 조회 실패");
//				mv.setViewName("common/commonview");
//			}
//			return mv;
//		}
//	
	
	
	
	
	
	
	
	
	
}
