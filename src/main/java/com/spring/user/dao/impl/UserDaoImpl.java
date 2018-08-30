package com.spring.user.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVo;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVo login(HashMap<String, Object> map) {
		UserVo userVo = sqlSession.selectOne("User.Login",map);
		return userVo;
	}

}
