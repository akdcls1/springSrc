package com.oracle.oBootMybatis01.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Member1DaoImpl implements Member1Dao {
	
	@Autowired
	private SqlSession session;

	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl id->"+id);
		try {
			result = session.selectOne("memCount",id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl memCount Exception->"+e.getMessage());
		}
		return result;
	}

}
