package com.spring.pds.service.typehandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.jdbc.support.nativejdbc.CommonsDbcpNativeJdbcExtractor;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

//배열을 stored 프로시저에 전달하는 것이 목표
public class ListMapTypeHandler implements TypeHandler<Object>{
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		List<String> list = (List<String>)parameter;
		
		System.out.println("parameter:" + parameter);
		//parameter:[윈도우98.jpg, googlelogo.jpg]
		System.out.println("list:" + list);
		//list:[윈도우98.jpg, googlelogo.jpg]
		
		
/*		if(list==null) {
			ps.setNull(i, java.sql.Types.INTEGER);
			return;
		}*/
		
		//파일이 없을 때 오류생김
		String[]  strings = new String[list.size()];
		for (int j = 0; j < list.size(); j++) {
			strings[j] = list.get(j);
			System.out.println(list.get(j));
			//윈도우981234.jpg
			//googlelogo1234.jpg
		}
		
		//db연결
		CommonsDbcpNativeJdbcExtractor extractor = new CommonsDbcpNativeJdbcExtractor();
		Connection conn = extractor.getNativeConnection(ps.getConnection());
		
		//오라클의 FILE_ARRAY타입을 사용하겠다.
		ArrayDescriptor desc = ArrayDescriptor.createDescriptor("FILE_ARRAY",conn);
		
		//오라클에 있는 ARRAY 타입으로 변환.->오라클이 배열을 받을 수 있게 함.
		parameter = new ARRAY(desc, conn, strings); 
		
		ps.setArray(i, (oracle.sql.ARRAY)parameter);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		if(cs.wasNull()) { //결과가 null일때
			return null; 
		} else {
			return cs.getString(columnIndex);
		}
	}
}
