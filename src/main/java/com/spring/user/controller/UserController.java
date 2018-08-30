package com.spring.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@Controller
public class UserController {
	
	//로그인 정보를 보관할 session 설정
	private  HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/Login")
	public ModelAndView loginForm(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/loginForm");
		return mv;
	}
	
	@RequestMapping("/LoginProcess")
	public ModelAndView loginProcess(@RequestParam HashMap<String, Object> map,
			HttpServletRequest request) {
		//map {userid, userpass}
		
		UserVo vo = userService.login(map);
		System.out.println("vo:" + vo);
		ModelAndView mv = new ModelAndView();
		
		if(vo == null) { //로그인 실패
			mv.setViewName("login/loginForm");						
		} else { //로그인 성공
			String userid = vo.getUserid();
			session = request.getSession(); 
			session.setAttribute("userid", userid); 
			//session에 담는 이유 map과는 다르게 서버가 유지되있는 동안
			//이 정보를 유지할 수 있기 때문에 커넥션이 접속되있는 순간부터 계속
			//다른 페이지에서 꺼내써도 문제없음
			mv.setViewName("redirect:/Home");			
		}
		return mv;
	}
	
	@RequestMapping("/Logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); //세선 전체 초기화
		
		//session.removeAttribute("userid"); // 세션 해당키만 지움		
		ModelAndView mv = new ModelAndView("/Login");
		return mv;
	}
}
