package com.oracle.oBootMybatis01.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoImpl implements EmpDao {

	@Autowired
	private SqlSession 		session;
	
	@Override
	public int total() {
		int tot = 0;
		System.out.println("EmpDaoImpl Start total...");
		
		try {
			// Naming Rule(조별로 정하래) ---> Map ID
			tot = session.selectOne("tkEmpTotal");
			System.out.println("EmpDaoImpl total tot->"+tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->"+e.getMessage());
		}
		
		return tot;
	}

}
