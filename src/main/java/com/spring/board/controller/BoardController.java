package com.spring.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.menu.service.MenuService;
import com.spring.menu.vo.MenuVo;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("/")
	public String first() {
		return "redirect:/Login";
	}
	
	@RequestMapping("/Home")
	public String home() {
		return "home";
	}
/*	@RequestMapping("/Board/List")
	public String list(HttpServletRequest request) {
		String menu_id = request.getParameter("menu_id");
		System.out.println(menu_id);
		return "list";
	}*/
	
	//목록이동
	@RequestMapping("/Board/List")
	//public String list(@RequestParam("menu_id") String menu_id) {
	//public String list(@RequestParam String menu_id) {
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		//map.put("menu_id", map.get("menu_id"));
		System.out.println("menu_id 들어가있는지 확인 " + map.get("menu_id"));
		
		//메뉴들고오기
		List<MenuVo> menuList = menuService.getList();
		mv.addObject("MenuList",menuList);
		
		List<BoardVo> list = boardService.getList(map);
		mv.addObject("BoardList",list);
		mv.setViewName("list");
		return mv;
	}

	//게시물입력폼
	@RequestMapping("/Board/WriteForm")
	public ModelAndView writeForm(@RequestParam HashMap<String, Object> map) {
		
		ModelAndView mv = new ModelAndView();
		
		//메뉴들고오기
		List<MenuVo> menuList = menuService.getList();
		mv.addObject("MenuList",menuList);
		
		mv.setViewName("write");
		mv.addObject("map",map);
		return mv;
	}
/*	@RequestMapping("/Board/Write")
	public String writeForm(
			@RequestParam BoardVo vo, Model model) {
		model.addAttribute("vo", vo);
		return "write";
	}*/
	//게시물입력
/*	@RequestMapping("/Board/Write")
	public String writeForm(@ModelAttribute("vo") BoardVo vo, Model model) {
		//model Attribute는 vo다.
		boardService.addBoard(vo);
		return "redirect:/Board/List";
	}*/
	
	@RequestMapping("/Board/Write")
	public ModelAndView write(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println(map);
		System.out.println(map.get("menu_name"));
		
		boardService.addBoard(map);
		
		mv.setViewName("redirect:/Board/List?menu_id=" + map.get("menu_id"));
		mv.addObject("map", map);
		return mv;
	}
	
	//게시물상세보기
	@RequestMapping("/Board/View")
	public ModelAndView view(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv = new ModelAndView();
		BoardVo vo = boardService.getView(map);
		mv.addObject("brd", vo);
		mv.setViewName("view");
		return mv;
	}
	
	//수정폼
	@RequestMapping("/Board/UpdateForm")
	public ModelAndView updateForm(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv = new ModelAndView();
		BoardVo vo = boardService.getView(map);
		
		mv.addObject("brd", vo);
		mv.setViewName("update");
		return mv;
	}
	
	//수정하기
	@RequestMapping("/Board/Update")
	public ModelAndView update(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv = new ModelAndView();
		boardService.updateBoard(map);
		
		mv.setViewName("redirect:/Board/List?menu_id="+ map.get("menu_id"));
		mv.addObject("map", map);
		return mv;
	}

	@RequestMapping("/Board/Content")
	public String content(@RequestParam HashMap<String,Object> map) {
		return "content";
	}
	
	//삭제하기
	@RequestMapping("/Board/Delete")
	public ModelAndView delete(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv = new ModelAndView();
		boardService.deleteBoard(map);
		
		mv.setViewName("redirect:/Board/List?menu_id="+ map.get("menu_id"));
		mv.addObject("map", map);
		return mv;
	}
	
	
	
}






