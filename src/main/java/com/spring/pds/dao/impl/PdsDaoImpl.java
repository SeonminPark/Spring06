package com.spring.pds.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.file.vo.FileVo;
import com.spring.pds.dao.PdsDao;
import com.spring.pds.vo.PdsVo;

@Repository("pdsDao")
public class PdsDaoImpl implements PdsDao {

	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<PdsVo> getList(HashMap<String, Object> map) {
		sqlSession.selectList("PDS.PdsList",map);
		List<PdsVo> list = (List<PdsVo>) map.get("result");
		return list;
	}

	@Override
	public void setWrite(HashMap<String, Object> map) {
		System.out.println("DaoImpl:" + map);
		//System.out.println("DaoImpl:" + map.get("menu_name"));
		System.out.println("filename:" + map.get("filename"));
		sqlSession.insert("PDS.PdsInsert",map);
	}

	@Override
	public PdsVo getContent(HashMap<String, Object> map) {
		sqlSession.selectList("PDS.PdsContent", map);
		List<PdsVo> list = (List<PdsVo>) map.get("result");
		
		PdsVo pdsVo = list.get(0);
		return pdsVo;
	}

	@Override
	public List<FileVo> getFiles(HashMap<String, Object> map) {
		sqlSession.selectList("File.FileList", map);
		List<FileVo> filesList = (List<FileVo>) map.get("result");
		return filesList;
	}

	@Override
	public void deletePds(HashMap<String, Object> map) {
		sqlSession.delete("PDS.PdsDelete",map);
	}

	@Override
	public void setUpdate(HashMap<String, Object> map) {
		System.out.println(map);
		sqlSession.update("PDS.PdsUpdate", map);
	}


}
