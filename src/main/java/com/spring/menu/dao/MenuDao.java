package com.spring.menu.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.menu.vo.MenuVo;

public interface MenuDao {

	List<MenuVo> getList();

	void insertMenu(HashMap<String, Object> map);

	MenuVo selectMenu(HashMap<String, Object> map);

	void updateMenu(HashMap<String, Object> map);

	void deleteMenu(HashMap<String, Object> map);


}