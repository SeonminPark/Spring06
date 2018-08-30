package com.spring.user.dao;

import java.util.HashMap;

import com.spring.user.vo.UserVo;

public interface UserDao {

	UserVo login(HashMap<String, Object> map);

}
