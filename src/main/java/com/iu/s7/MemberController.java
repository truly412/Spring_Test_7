package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value="memberIdCheck", method=RequestMethod.GET)
	public ModelAndView memberIdCheck(String id) throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO = memberService.memberIdCheck(id);
		if(memberDTO==null) {
			mv.addObject("result", "possible");
		} else {
			mv.addObject("result", "impossible");
		}
		mv.setViewName("member/memberIdCheckResult");
		return mv;
	}
	
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public void memberJoin() {}
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(MemberDTO memberDTO,MultipartFile file, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		result = memberService.memberJoin(memberDTO, file, session);
		if(result>0) {
			mv.addObject("message", "JoinSuccess");
			mv.addObject("path", "../");
		} else {
			mv.addObject("message", "JoinFail");
			mv.addObject("path", "../");
		}
		mv.setViewName("common/result");
		return mv;
	}
	
	@RequestMapping(value="memberLogin", method=RequestMethod.GET)
	public void memberLogin() {}
	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView memberLogin(HttpSession session,MemberDTO memberDTO) {
		ModelAndView mv = new ModelAndView();
		try {
			memberDTO = memberService.memberLogin(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO.equals("null")) {
			mv.addObject("message", "fail");
			mv.addObject("path", "./memberLogin");
		} else {
			session.setAttribute("member", memberDTO);
			mv.addObject("message", "success");
			mv.addObject("path", "../");
		}
		mv.setViewName("./common/result");
		return mv;
	}
	
	@RequestMapping(value="memberView", method=RequestMethod.GET)
	public ModelAndView memberView(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		String id = memberDTO.getId();
		try {
			memberDTO = memberService.memberView(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO != null) {
			mv.addObject("list", memberDTO);
			mv.setViewName("member/memberView");
		}
		return mv;
	}
	
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public ModelAndView memberUpdate(MemberDTO memberDTO) {
		ModelAndView mv =new ModelAndView();
		int result = 0;
		try {
			result = memberService.memberUpdate(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0){
			mv.addObject("message", "success");
			mv.addObject("path", "../");
			mv.setViewName("./common/result");
		}
		return mv;
	}
	
	/*
	@RequestMapping("memberDelete")
	public ModelAndView memberDelete(HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		String id = memberDTO.getId();
		String pw = memberDTO.getPw();
		ModelAndView mv = new ModelAndView();
		int result=0;
		try {
			result = memberService.memberDelete(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result>0) {
			System.out.println("delete?");
			session.invalidate();
			mv.addObject("message", "success");
			mv.addObject("path", "../");
			mv.setViewName("./common/result");
		}
		return mv;
	}*/
	
	
	
	
}
