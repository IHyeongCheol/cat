package com.project.cat.measure.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.cat.measure.ai.pythonAi;
import com.project.cat.measure.model.service.CatProfileService;
import com.project.cat.measure.model.vo.CatProfile;



@Controller
public class CatProfileController {
	private static final Logger logger = 
			LoggerFactory.getLogger(CatProfileController.class);
	
	private CatProfile resultCatProfile = new CatProfile();
	
	@Autowired
	private CatProfileService catprofileService;

	
	@RequestMapping("measure.do")
	public String moveMeasurePage(Model model) {
		model.addAttribute("imgfile", resultCatProfile.getCat_rename_img());
		return "measure/measure";
	}
	
	@RequestMapping(value = "catproImgflieUpload.do", method = RequestMethod.POST)
	public String CatProfileIMGInsertMethod(HttpServletRequest request, CatProfile catprofile, Model model, 
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
//		String savePath = "C:/CatProject/cat/src/main/webapp/resources/ai_cat_img";
		String savePath = request.getSession().getServletContext().getRealPath("resources/ai_cat_img");
		System.out.println("이미지 업로드 경로 : "+savePath);
		System.out.println(!mfile.isEmpty());
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
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "_" + catprofile.getUser_id();
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
					model.addAttribute("message", "이미지 저장 실패.");
					return "common/commonview";
				}
				
				catprofile.setCat_original_img(fileName);
				catprofile.setCat_rename_img(renameFileName);
				catprofile.setMeasure_date(renameFileName.substring(0, 7));
				//날짜 입력
				catprofile.setCat_rename_img(renameFileName);
				
				//값 입력
				resultCatProfile.setCat_original_img(fileName.trim());
				resultCatProfile.setCat_rename_img(renameFileName.trim());
				
				model.addAttribute("imgfile", resultCatProfile.getCat_rename_img());
				System.out.println("파일명: "+resultCatProfile.getCat_rename_img());
			}
		}
		return "measure/measureupdate";
	}
		
		//첨부파일이 있을때만
//		/////////////multi////////////
//		MultipartRequest multi;
//		
//		try {
//			multi = new MultipartRequest(request, savePath, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
//			//파일 입출력 구문
//			//파일명 수정 & 파일 받은거 입력
//			Enumeration files = multi.getFileNames();
//			String str = (String)files.nextElement();
//	        //파일명 중복이 발생했을 때 정책에 의해 뒤에 1,2,3 처럼 숫자가 붙어 고유 파일명을 생성한다.
//	        //이때 생성된 이름을 FilesystemName이라고 하여 그 이름 정보를 가져온다. (중복 처리)
//	        String fileName = multi.getFilesystemName(str);
//	        
//			//업로드 파일 없을시 구분 처리
//	        System.out.println("입력4:"+fileName);
//	        if(fileName == null) {
//	        	return "redirect:measure.do";
//	        }
//	        else {
//		        //실제 파일 이름을 가져온다.
//		        String originalFileName = multi.getOriginalFileName(str);
//		        System.out.println("입력5:"+originalFileName);
//		        //변경할 파일명에 날짜 추출
//		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		        String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//		        renameFileName += "_" + multi.getParameter("user_id");
//				// 원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
//				renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//				System.out.println("실제 파일명: " + originalFileName);
//				System.out.println("변경 파일명: " + renameFileName);
//				//값 입력
//				resultCatProfile.setCat_original_img(originalFileName.trim());
//				resultCatProfile.setCat_rename_img(renameFileName.trim());
//				//파일명 수정
//				Path source = Paths.get(savePath + "\\" + fileName.trim());
//		        Files.move(source, source.resolveSibling(savePath + "\\" + renameFileName.trim()));
//		        
//		        model.addAttribute("imgfile", resultCatProfile.getCat_rename_img());
//				System.out.println("파일명: "+resultCatProfile.getCat_rename_img());
//				return "measure/measureupdate";
//	        }	
//		} catch (IOException e) {
//			e.printStackTrace();
//			model.addAttribute("message", "입력 잘못 됐다냥!");
//	    	return "common/commonview";
//		}
	
	@RequestMapping(value = "catproflieInsert.do", method = RequestMethod.POST)
	public String CatProfileInsertMethod(CatProfile catprofile, HttpServletRequest request, Model model) {
		System.out.println("파일 저장 확인:"+resultCatProfile.getCat_original_img());
//		String savePath = "C:/CatProject/cat/src/main/webapp/resources/ai_cat_img";
		String savePath = request.getSession().getServletContext().getRealPath("resources/ai_cat_img");
		try {
			if(catprofile.getUser_id().equals("")) {
				model.addAttribute("message", "로그인해야 이용할 수 있다냥!!");
		    	return "common/commonview";
			}else if(resultCatProfile.getCat_rename_img() == null) {
				System.out.println("출력 확인");
				model.addAttribute("message", "사진을 등록해라냥!");
				return "measure/measure";
			}else {
				//중성화 대문자 값 등록
				catprofile.setNeutering(catprofile.getNeutering().toUpperCase());
				//반복 횟수 값  등록 구문
				System.out.println("확인1:"+catprofile.getCat_name());
				ArrayList<CatProfile> selectCatprofile = catprofileService.selectCatOneCount(catprofile);
				System.out.println("확인2:"+selectCatprofile);
				if(selectCatprofile.size() != 0) {
					CatProfile repeatcount= selectCatprofile.get(0);
					System.out.println(repeatcount);
					System.out.println("반복 최대 횟수:"+repeatcount.getRepeat_number());
					catprofile.setRepeat_number(repeatcount.getRepeat_number()+1);
				}else {
					catprofile.setRepeat_number(1);
				}
				//파일명 등록
				catprofile.setCat_original_img(resultCatProfile.getCat_original_img());
				catprofile.setCat_rename_img(resultCatProfile.getCat_rename_img());
				
				//AI측정 값 등록
				pythonAi ai = new pythonAi();
				catprofile.setMeasure_value(Double.parseDouble(ai.aiValue(savePath, resultCatProfile.getCat_rename_img())));
			
				//AI측정 날짜 등록
				String d = catprofile.getCat_rename_img().substring(0, 8);
				System.out.println("날짜:"+d);
				catprofile.setMeasure_date(d);
				
				resultCatProfile = catprofile;
				
				//기록 값 저장
				resultCatProfile.setUser_id(catprofile.getUser_id());
				resultCatProfile.setCat_name(catprofile.getCat_name());
				resultCatProfile.setBreed(catprofile.getBreed());
				resultCatProfile.setAge(catprofile.getAge());
				resultCatProfile.setStature(catprofile.getStature());
				resultCatProfile.setWeight(catprofile.getWeight());
				resultCatProfile.setNeutering(catprofile.getNeutering());
				resultCatProfile.setRepeat_number(catprofile.getRepeat_number());
				resultCatProfile.setMeasure_value(catprofile.getMeasure_value());
				resultCatProfile.setMeasure_date(catprofile.getMeasure_date());
			}
			//출력 결과
		    if (catprofile == resultCatProfile) {
		    	if(catprofile.getMeasure_value() >= 33 && catprofile.getMeasure_value() <= 66) {
		    		model.addAttribute("img", "cimg2.png");
		    		model.addAttribute("message", catprofile.getCat_name()+"는 정상 체형이네 \n 관리 잘하고 있다냥!");
		    	}
		    	if(catprofile.getMeasure_value() > 66 && catprofile.getMeasure_value() < 101) {
		    		model.addAttribute("img", "cimg4.png");
		    		model.addAttribute("message", catprofile.getCat_name()+" 다이어트 좀 시켜라냥!");
		    	}
		    	if(catprofile.getMeasure_value() > 0 && catprofile.getMeasure_value() < 33) {
		    		model.addAttribute("img", "cimg3.png");
		    		model.addAttribute("message", catprofile.getCat_name()+" 굶기고 있냥? 밥좀 먹여라냥!");
		    	}
		    	model.addAttribute("value", catprofile.getCat_name()+"의 비만도 : "+(int)catprofile.getMeasure_value()+"%");
		    	return "measure/measureresult";
		    } else {
		    	model.addAttribute("message", "입력 잘못 됐다냥!");
		    	return "common/commonview";
		    }
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "입력 잘못 됐다냥!");
	    	return "common/commonview";
		}
	}
	
	@RequestMapping("catproflieDbInsert.do")
	public String CatProfileInsertMethod(Model model) {
		System.out.println("먼대"+resultCatProfile.getAge());
		System.out.println("먼대"+resultCatProfile.getCat_rename_img());
		System.out.println("먼대"+resultCatProfile.getCat_original_img());
		catprofileService.insertCatProfile(resultCatProfile);
		model.addAttribute("message", "기록 등록 됐다냥!");
    	return "common/commonview";
	}

}

