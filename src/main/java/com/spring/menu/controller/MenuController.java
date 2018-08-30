package com.spring.menu.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.menu.service.MenuService;
import com.spring.menu.vo.MenuVo;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	//메뉴목록
	@RequestMapping("/Menus/List")
	public ModelAndView list() {
		
		ModelAndView mv = new ModelAndView();
		List<MenuVo> list = menuService.getList();
		
		mv.addObject("menuList", list);
		mv.setViewName("menus/menulist");
		return mv;
	}
	
	//메뉴추가폼 이동
	@RequestMapping("/Menus/WriteForm")
	public ModelAndView writeForm() {
		
		ModelAndView mv = new ModelAndView();

		mv.setViewName("menus/writemenu");
		return mv;
	}
	
	//메뉴추가
	@RequestMapping("/Menus/Write")
	public ModelAndView write(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		
		/*System.out.println(map);
		System.out.println(map.get("menu_name"));*/
		menuService.insertMenu(map);
		mv.setViewName("redirect:/Menus/List");
		return mv;
	}

	
	//메뉴수정폼
	@RequestMapping("/Menus/UpdateForm")
	public ModelAndView updateForm(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		
		MenuVo vo = menuService.selectMenu(map);
		mv.addObject("menu",vo);
		mv.setViewName("menus/updatemenu");
		return mv;
	}
	
	//메뉴수정
	@RequestMapping("/Menus/Update")
	public ModelAndView update(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		
		menuService.updateMenu(map);
		
		mv.setViewName("redirect:/Menus/List");
		return mv;
	}
	
	//메뉴삭제
	@RequestMapping("/Menus/Delete")
	public ModelAndView delete(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		
		menuService.deleteMenu(map);
		
		mv.setViewName("redirect:/Menus/List");
		return mv;
	}
	
	
	
	
	
}
