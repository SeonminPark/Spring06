package com.spring.pds.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.file.vo.FileVo;
import com.spring.pds.vo.PdsVo;

public interface PdsService {
	List<PdsVo> getList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map, HttpServletRequest request);

	PdsVo getContent(HashMap<String, Object> map);

	List<FileVo> getFiles(HashMap<String, Object> map);

	void setDelete(HashMap<String, Object> map);

	void setUpdate(HashMap<String, Object> map, HttpServletRequest request);
}
