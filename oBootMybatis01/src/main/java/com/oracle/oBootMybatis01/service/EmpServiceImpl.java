package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private	EmpDao	ed;

	@Override
	public int total() {
		System.out.println("EmpServiceImpl STart total...");
		int totCnt = ed.total();
		System.out.println("EmpSErviceImpl total totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp Start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->"+empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = null;
		System.out.println("EmpServiceImpl detail Start...");
		emp = ed.detail(empno);
		return emp;
	}

}
