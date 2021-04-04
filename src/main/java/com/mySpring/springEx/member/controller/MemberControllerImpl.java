package com.mySpring.springEx.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.member.service.MemberService;
import com.mySpring.springEx.member.vo.MemberVO;



@Controller("memberController")
//@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;

	// ����ȭ��
	@RequestMapping(value = { "/", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String) request.getAttribute("viewName"); // string���·� request viewName�� ����
		ModelAndView mav = new ModelAndView(); // mav ����
		mav.setViewName(viewName); // mav�� view��ġ�� request�߾��� (/,main.do) viewName�� �־� �ش���ġ�� �̵��Ѵ�
		return mav;
	}

	// ������
	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	

	// ȸ������
	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//�α���
	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
				              RedirectAttributes rAttr,
		                       HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView mav = new ModelAndView();
	memberVO = memberService.login(member);
	if(memberVO != null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("member", memberVO);
	    session.setAttribute("isLogOn", true);
	    mav.addObject("result",true);
	    mav.setViewName("jsonView");
		/*
		 * //mav.setViewName("redirect:/member/listMembers.do"); String action =
		 * (String)session.getAttribute("action"); session.removeAttribute("action");
		 * if(action!= null) { mav.setViewName("redirect:"+action); }else {
		 * mav.setViewName("redirect:/member/listMembers.do"); }
		 */

	}else {
		rAttr.addAttribute("result", "loginFailed");/*
													 * mav.setViewName("redirect:/member/loginForm.do");
													 */
	   mav.addObject("result",false);
	   mav.setViewName("jsonView");
	}
	return mav;
	}

	// �α׾ƿ�
	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}

	// form���� �����°� ���� �Ȱŵ������
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	// ȸ�����Կ� ���� (munsu) 
	// ���̵� �ߺ� �˻�(AJAX)
		@RequestMapping(value = "/member/check_id.do", method = RequestMethod.POST)
		public void check_id(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
			memberService.check_id(id, response);
		}

		// �̸��� �ߺ� �˻�(AJAX)
		@RequestMapping(value = "/member/check_email.do", method = RequestMethod.POST)
		public void check_email(@RequestParam("email") String email, HttpServletResponse response) throws Exception {
			memberService.check_email(email, response);
		}

		//ȸ������ ���� 
		@Override
		@RequestMapping(value = "member/join_member.do", method = RequestMethod.POST)
		public String join_member(@ModelAttribute MemberVO member, RedirectAttributes rttr, HttpServletResponse response)
				throws Exception {

			rttr.addFlashAttribute("result", memberService.join_member(member, response));

			return "memberJoinForm.jsp";
		}

		// ȸ�� ����
		@RequestMapping(value = "member/approval_member.do", method = RequestMethod.POST)
		public String approval_member(@ModelAttribute MemberVO member, HttpServletResponse response) throws Exception {
			memberService.approval_member(member, response);
			
			return "main.jsp"; //�ƹ��ų��ص� �������
		}




}
