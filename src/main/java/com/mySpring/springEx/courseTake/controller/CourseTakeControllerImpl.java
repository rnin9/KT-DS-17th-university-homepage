package com.mySpring.springEx.courseTake.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.courseTake.service.CourseTakeService;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;
import com.mySpring.springEx.syllabus.vo.SyllabusVO;

@Controller("courseTakeController")
//@EnableAspectJAutoProxy
public class CourseTakeControllerImpl implements CourseTakeController {
	@Autowired
	private CourseTakeService courseTakeService;
	@Autowired
	CourseTakeVO courseTakeVO;
	/*
	 * @RequestMapping(value = "/courseTake/courseApplyList.do", method =
	 * RequestMethod.GET) public ModelAndView courseApplyList(HttpServletRequest
	 * request, HttpServletResponse response) { List courseApplyList =
	 * courseTakeService.courseApplyList(); String viewName = (String)
	 * request.getAttribute("viewName"); ModelAndView mav = new ModelAndView();
	 * mav.setViewName(viewName); mav.addObject("courseApplyList", courseApplyList);
	 * return mav; }
	 */

	@Override
	@RequestMapping(value = "/courseTake/courseApplyList.do", method = RequestMethod.GET)
	
	public ModelAndView courseCApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String) request.getAttribute("viewName");
		List courseApplyList =  courseTakeService.SelectAllCourseApplyList();
		ModelAndView mav = new ModelAndView(viewName);
		// ó���� �κ��� ȭ�鿡 ����
		mav.addObject("courseApplyList", courseApplyList);
		return mav;
	}

	// ���δ��->�������� update

//	@RequestMapping(value = "/courseTake/updateConsentCheck.do", method = RequestMethod.POST)
//	public ModelAndView updateApplyConsent(@ModelAttribute("courseTake") CourseTakeVO courseTakeVO,
//		@RequestParam List<String> valueArr) throws Exception {
//		ModelAndView mav = new ModelAndView("redirect:/courseTake/courseApplyList.do");
//		
//		for (int i = 0; i < valueArr.size(); i++) {
//			String arr[] = valueArr.get(i).split(" ");
//			courseTakeVO.setUserID(arr[0]);
//			courseTakeVO.setCourseID(Integer.parseInt(arr[1]));
//			courseTakeVO.setCourseTake_State(arr[2]);
//
//			courseTakeService.updateApplyConsent(courseTakeVO);
//			courseTakeService.updatePosition1(courseTakeVO);
//			
//			System.out.println(courseTakeVO);
//			System.out.println(courseTakeVO.getCourseTake_State());
//		}
//		return mav;
//	}
	
	@RequestMapping(value = "/courseTake/updateConsentCheck.do", method = RequestMethod.POST)
	public ModelAndView updateApplyConsent(@ModelAttribute("courseTake") CourseTakeVO courseTakeVO,
		@RequestParam List<String> valueArr) throws Exception {
		
		List<String> TF = new ArrayList<String>();
		String ox1 = "o";
		
		for (int i = 0; i < valueArr.size(); i++) {
			String arr[] = valueArr.get(i).split(" ");
			TF.add(arr[2]);			
		}
		
		for(int i=0; i<TF.size(); i++) {
			if(!TF.get(i).equals("���δ��")){
				ox1 = "x";
			}
		}
		
		if(ox1=="o") {
			for (int i = 0; i < valueArr.size(); i++) {
				String arr[] = valueArr.get(i).split(" ");
				courseTakeVO.setUserID(arr[0]);
				courseTakeVO.setCourseID(Integer.parseInt(arr[1]));
				
				courseTakeService.updateApplyConsent(courseTakeVO);
				courseTakeService.updatePosition1(courseTakeVO);	
			}
		}
		ModelAndView mav = new ModelAndView("redirect:/courseTake/courseApplyList.do");
		
		mav.addObject("ox1", ox1);
		
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	// ����->���δ��� update

	@RequestMapping(value = "/courseTake/updateConsentCancelCheck.do", method = RequestMethod.POST)
	public ModelAndView updateApplyConsentCancel(@ModelAttribute("courseTake") CourseTakeVO courseTakeVO,
			@RequestParam List<String> valueArr) throws Exception {
		
		List<String> TF = new ArrayList<String>();
		String ox2 = "o";
		
		for (int i = 0; i < valueArr.size(); i++) {
			String arr[] = valueArr.get(i).split(" ");
			TF.add(arr[2]);			
		}
		
		for(int i=0; i<TF.size(); i++) {
			if(!TF.get(i).equals("����")){
				ox2 = "x";
			}
		}
		
		if(ox2=="o") {
			for (int i = 0; i < valueArr.size(); i++) {
				String arr[] = valueArr.get(i).split(" ");
				courseTakeVO.setUserID(arr[0]);
				courseTakeVO.setCourseID(Integer.parseInt(arr[1]));
				
				courseTakeService.updateApplyConsentCancel(courseTakeVO);
				courseTakeService.updatePosition2(courseTakeVO);	
			}
		}

		ModelAndView mav = new ModelAndView("redirect:/courseTake/courseApplyList.do");
		mav.addObject("ox2", ox2);
		mav.setViewName("jsonView");
		return mav;

	}

	// ������->����� update
	@RequestMapping(value = "/courseTake/updateCompletionCheck.do", method = RequestMethod.POST)
	public ModelAndView updateCompletion(@ModelAttribute("courseTake") CourseTakeVO courseTakeVO,
			@RequestParam List<String> valueArr) throws Exception {
		
		List<String> TF = new ArrayList<String>();
		String ox3 = "o";
		
		for (int i = 0; i < valueArr.size(); i++) {
			String arr[] = valueArr.get(i).split(" ");
			TF.add(arr[2]);			
		}
		
		for(int i=0; i<TF.size(); i++) {
			if(!TF.get(i).equals("������")){
				ox3 = "x";
			}
		}
		
		if(ox3=="o") {
			for (int i = 0; i < valueArr.size(); i++) {
				String arr[] = valueArr.get(i).split(" ");
				courseTakeVO.setUserID(arr[0]);
				courseTakeVO.setCourseID(Integer.parseInt(arr[1]));
				
				courseTakeService.updateCompletion(courseTakeVO);	
			}
		}

		ModelAndView mav = new ModelAndView("redirect:/courseTake/courseApplyList.do");
		mav.addObject("ox3", ox3);
		mav.setViewName("jsonView");
		return mav;
	}

	// �� ����
	@RequestMapping(value = "/courseTake/deleteCourseTake.do", method = RequestMethod.POST)
	public ModelAndView deleteCourseTake(@ModelAttribute("courseTake") CourseTakeVO courseTakeVO,
			@RequestParam List<String> valueArr) throws Exception {

		for (int i = 0; i < valueArr.size(); i++) {
			String arr[] = valueArr.get(i).split(" ");
			courseTakeVO.setUserID(arr[0]);
			courseTakeVO.setCourseID(Integer.parseInt(arr[1]));
			courseTakeService.deleteCourseTake(courseTakeVO);
		}

		ModelAndView mav = new ModelAndView("redirect:/courseTake/courseApplyList.do");
		return mav;

	}
	//������ ������
	@RequestMapping(value = "/courseTake/certificate.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewCertificate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println("============"+request.getParameter("userName"));
		return mav;
	}

	// �׽�Ʈ������
	@RequestMapping(value = "/courseTake/courseCompleteList.do", method = RequestMethod.GET)
	public ModelAndView courseCompleteList(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/course/insertCourseTable.do", method=RequestMethod.POST)
	public ModelAndView insertCourseTable(@RequestParam List<String> valueArr) throws Exception {
		courseTakeVO.setCourseID(Integer.parseInt(valueArr.get(0)));
		courseTakeVO.setUserID(valueArr.get(1));
		System.out.println(courseTakeVO.getCourseID());
		System.out.println(courseTakeVO.getUserID());
		courseTakeService.insertCourseTake(courseTakeVO);
		ModelAndView mav = new ModelAndView("redirect:/course/userCourseList.do");
		return mav;
	}

}
