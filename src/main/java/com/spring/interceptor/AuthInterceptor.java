package com.spring.interceptor;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.menu.service.MenuService;
import com.spring.menu.vo.MenuVo;



public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private MenuService menuService;

	//컨트롤러보다 먼저 수행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		//로그인 체크
		HttpSession session = request.getSession(); 
		//dispatcher-servlet이 가지고 있는 세션
		//클라이언트가 주소를 치고 들어오는 순간 만들어짐
		Object obj = session.getAttribute("userid");
		
		
		System.out.println("현재경로:" + request.getRequestURI());

		List<MenuVo> menuList  = menuService.getList(); //메뉴리스트
		request.setAttribute("menuList", menuList);
		
		//두 컨트롤러는 체크에서 제외
		//로그인 부분에서는 세션체크 x
		switch(request.getRequestURI()){
		case "/Login" :
		case "/LoginProcess" :
			return true; 
		}
		
		if(obj==null) { //로그인 안된 상태
			response.sendRedirect("/Login");
			// 더이상 컨트롤러 요청으로 가지 않는다. 
			return false;
		}else {			
			// prehandler의 return은 컨트롤러의 요청 uri으로 가도되는지 여부
			return true;  
		}
	}

	//컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
