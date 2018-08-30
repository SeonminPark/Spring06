package com.spring.board.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.board.vo.BoardVo;

public interface BoardDao {
	//public void addBoard(BoardVo vo);

	public List<BoardVo> getList(HashMap<String,Object> map);

	public BoardVo getView(HashMap<String, Object> map);

	public void addBoard(HashMap<String, Object> map);

	public void updateBoard(HashMap<String, Object> map);

	public void deleteBoard(HashMap<String, Object> map);
}
