package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

/*	@Override
	public void addBoard(BoardVo vo) {
		sqlSession.insert("Board.BoardInsert", vo);
	}*/

	@Override
	public List<BoardVo> getList(HashMap<String,Object> map) {
		System.out.println("daoImpl에서 menu_id " + map.get("menu_id"));
		sqlSession.selectList("Board.BoardList", map);		

		List<BoardVo> list = (List<BoardVo>) map.get("result");
		System.out.println(list);
		return list;

	}

	@Override
	public BoardVo getView(HashMap<String, Object> map) {
		sqlSession.selectOne("Board.BoardView", map);
		List<BoardVo> list = (List<BoardVo>) map.get("result");
		BoardVo vo = list.get(0);
		//BoardVo vo = (BoardVo) map.get("result"); //이건 안된당..
		System.out.println(vo);
		return vo;
	}

	@Override
	public void addBoard(HashMap<String, Object> map) {
		System.out.println("menu_name :" + map.get("menu_name"));
		sqlSession.insert("Board.BoardInsert", map);
		
	}

	@Override
	public void updateBoard(HashMap<String, Object> map) {
		sqlSession.update("Board.BoardUpdate", map);
		
	}

	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		sqlSession.delete("Board.BoardDelete", map);
		
	}


}
