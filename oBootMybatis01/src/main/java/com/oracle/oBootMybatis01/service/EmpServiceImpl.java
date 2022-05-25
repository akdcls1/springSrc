package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private	EmpDao	ed;
	
	@Autowired
	private DeptDao dd;
	
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

	@Override
	public int update(Emp emp) {
		// Biz
		System.out.println("EmpServiceImpl update...");
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listManager Start...");
		empList = ed.listManager();
		return empList;
	}

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect Start...");
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->"+deptList.size());
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		
		result = ed.insert(emp);
		
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		result = ed.delete(empno);
		return result;
	}

	@Override
	public List<Emp> listEmpKeyword(Emp emp) {
		List<Emp> listEmpKeyword = null;
		System.out.println("EmpServiceImpl listEmp Start...");
		listEmpKeyword = ed.listEmpKeyword(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->"+listEmpKeyword.size());
		return listEmpKeyword;
	}

	@Override
	public int totalKeyword(Emp emp) {
		System.out.println("EmpServiceImpl totalKeyword Start...");
		int totCnt = ed.totalKeyword(emp);
		System.out.println("EmpServiceImpl totalKeyword totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpServiceImpl listEmpDept Start...");
		empDeptList = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empDeptList.size()->"+empDeptList.size());
		return empDeptList;
	}

}
