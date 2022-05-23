package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.oracle.oBootMybatis01.model.Dept;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("DeptDaoImpl deptSelect Start...");
		deptList = session.selectList("tkSelectDept");
		System.out.println("DeptDaoImpl deptSelect deptList.size()->"+deptList.size());
		return deptList;
	}

}
