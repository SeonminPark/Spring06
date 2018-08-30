package com.spring.pds.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.file.vo.FileVo;
import com.spring.pds.vo.PdsVo;

public interface PdsDao {
	List<PdsVo> getList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map);

	PdsVo getContent(HashMap<String, Object> map);

	List<FileVo> getFiles(HashMap<String, Object> map);

	void deletePds(HashMap<String, Object> map);

	void setUpdate(HashMap<String, Object> map);



}
