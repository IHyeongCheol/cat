package com.project.cat.manage.controller;

import java.util.ArrayList;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.cat.manage.model.service.ManageService;
import com.project.cat.measure.model.vo.CatProfile;
import com.project.cat.member.model.vo.Member;


@Controller
public class ManageController {
	private static final Logger logger = LoggerFactory.getLogger(ManageController.class);
	
	@Autowired
	private ManageService manageService;
	

	@RequestMapping("mycat.do")
	public String cat() {
		return "catmanage/catmanageListView";
	}
	
	@RequestMapping("cmlist.do")
	public ModelAndView selectAllCatProfile(@RequestParam(name="user_id", required=false) String user_id, ModelAndView mv) {
		System.out.println("확인:"+user_id);
		try {
			ArrayList<CatProfile> list = manageService.selectAllCatProfile(user_id);
			System.out.println("확인:"+list.get(0));
			if(list != null && list.size() > 0) {
				mv.addObject("list", list);
				mv.setViewName("catmanage/catmanageListView");
			}else {
				mv.addObject("message", "측정 테스트 기록이 없다냥!");
				mv.setViewName("common/commonview");
			}
			return mv;
		} catch (Exception e) {
			mv.addObject("message", "측정 테스트 기록이 없다냥!");
			mv.setViewName("common/commonview");
			return mv;
		}
	}
	
	//이름으로 검색용
	@RequestMapping(value="cmsearchName.do", method=RequestMethod.POST)
	public String selectNameCatProfile(@RequestParam(name="user_id", required=false) String user_id, @RequestParam("keyword") String keyword, Model model) {
		System.out.println(user_id);
		System.out.println(keyword);
		
		CatProfile catprofile = new CatProfile();
		catprofile.setUser_id(user_id);
		catprofile.setCat_name(keyword);
		ArrayList<CatProfile> list = manageService.selectNameCatProfile(catprofile);
		
		if(list.size() > 0) {
			model.addAttribute("list", list);
			return "catmanage/catmanageListView";
		}else {
			model.addAttribute("message", keyword + "는 측정 테스트 기록이 없다냥! ");
			return "common/commonview";
		}
	}
	
	//상세 페이지
	@RequestMapping("cmdetail.do")
	public String selectOneCatProfile(HttpServletRequest request, @RequestParam("user_id") String user_id, @RequestParam("cat_name") String cat_name, @RequestParam("repeat_number") int repeat_number, Model model, HttpSession session) {
		String savePath = "C:/CatProject/cat/src/main/webapp/resources/ai_cat_img";
//		String savePath = request.getSession().getServletContext().getRealPath("resources/ai_cat_img");
		
		CatProfile catprofile = new CatProfile();
		catprofile.setUser_id(user_id);
		catprofile.setCat_name(cat_name);
		catprofile.setRepeat_number(repeat_number);
		
		System.out.println(catprofile.getUser_id());
		System.out.println(catprofile.getCat_name());
		System.out.println(catprofile.getRepeat_number());
		
		catprofile = manageService.selectOneCatProfile(catprofile);
		
		System.out.println("값?"+catprofile.getMeasure_value());
		
		if(catprofile != null) {
			model.addAttribute("cat_name", catprofile.getCat_name());
			model.addAttribute("breed", catprofile.getBreed());
			model.addAttribute("age", catprofile.getAge());
			model.addAttribute("stature", catprofile.getStature());
			model.addAttribute("weight", catprofile.getWeight());
			model.addAttribute("neutering", catprofile.getNeutering());
			model.addAttribute("repeat_number", catprofile.getRepeat_number());
			model.addAttribute("measure_value", catprofile.getMeasure_value());
			model.addAttribute("measure_date", catprofile.getMeasure_date());
			//이미지
//			String savePath = "C:/CatProject/cat/src/main/webapp/resources/ai_cat_img/";
//			String savePath = request.getSession().getServletContext().getRealPath("resources/ai_cat_img/");
			model.addAttribute("imgfile", catprofile.getCat_rename_img());
			System.out.println("경로;"+savePath+"\\"+catprofile.getCat_rename_img());
			return "catmanage/mycatdetail";

		}else {
			model.addAttribute("message", "내 고양이 상세보기 에러다냥!");
			return "common/commonview";
		}
	}
	
	@RequestMapping(value="catdelete.do", method = RequestMethod.POST)
	public String memberDeleteMethod(CatProfile catprofile, HttpServletRequest request, Model model) {
		catprofile.setUser_id(request.getParameter("duser_id"));
		catprofile.setCat_name(request.getParameter("dcat_name"));
		catprofile.setRepeat_number(Integer.parseInt(request.getParameter("drepeat_number")));
		manageService.deleteCatProfile(catprofile);

		model.addAttribute("message", "기록 삭제 완료다냥!");
		return "common/commonview";
	}
}
