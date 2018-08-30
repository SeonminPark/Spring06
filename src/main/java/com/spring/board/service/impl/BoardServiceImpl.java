package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
/*	@Override
	public void addBoard(BoardVo vo) {
		boardDao.addBoard(vo);
	}*/

	@Override
	public List<BoardVo> getList(HashMap<String,Object> map) {
		List<BoardVo> list = boardDao.getList(map);
		
		return list;
	}

	@Override
	public BoardVo getView(HashMap<String, Object> map) {
		BoardVo vo = boardDao.getView(map);
		return vo;
	}

	@Override
	public void addBoard(HashMap<String, Object> map) {
		boardDao.addBoard(map);
		
	}

	@Override
	public void updateBoard(HashMap<String, Object> map) {
		boardDao.updateBoard(map);
		
	}

	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		boardDao.deleteBoard(map);
		
	}



}
