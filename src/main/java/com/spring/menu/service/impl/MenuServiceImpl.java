package com.spring.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.menu.dao.MenuDao;
import com.spring.menu.service.MenuService;
import com.spring.menu.vo.MenuVo;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<MenuVo> getList() {		
		List<MenuVo> list = menuDao.getList();
		return list;
	}

	@Override
	public void insertMenu(HashMap<String, Object> map) {
		menuDao.insertMenu(map);
		
	}

	@Override
	public MenuVo selectMenu(HashMap<String, Object> map) {
		MenuVo vo = menuDao.selectMenu(map);
		return vo;
	}

	@Override
	public void updateMenu(HashMap<String, Object> map) {
		menuDao.updateMenu(map);
		
	}

	@Override
	public void deleteMenu(HashMap<String, Object> map) {
		menuDao.deleteMenu(map);
		
	}


}
