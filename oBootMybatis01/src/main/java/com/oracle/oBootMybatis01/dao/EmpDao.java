package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.model.Emp;

public interface EmpDao {
	int			total();

	List<Emp> 	listEmp(Emp emp);

	Emp 		detail(int empno);
}
