package com.spring.menu.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.menu.dao.MenuDao;
import com.spring.menu.vo.MenuVo;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<MenuVo> getList() {
		HashMap<String, Object> map = new HashMap<>();
		sqlSession.selectList("Menu.MenuList", map);
		
		List<MenuVo> list = (List<MenuVo>) map.get("result");
		//System.out.println(list);
		return list;
	}


	@Override
	public void insertMenu(HashMap<String, Object> map) {
		System.out.println(map);
		System.out.println(map.get("menu_name"));
		sqlSession.insert("Menu.InsertMenu", map);
	}


	@Override
	public MenuVo selectMenu(HashMap<String, Object> map) {
		sqlSession.selectOne("Menu.SelectMenu", map);
		List<MenuVo> list = (List<MenuVo>) map.get("result");
		MenuVo vo = list.get(0);
		
		return vo;
	}


	@Override
	public void updateMenu(HashMap<String, Object> map) {
		sqlSession.update("Menu.UpdateMenu", map);
	
	}


	@Override
	public void deleteMenu(HashMap<String, Object> map) {
		sqlSession.delete("Menu.DeleteMenu", map);
		
	}
	

	
	

}
