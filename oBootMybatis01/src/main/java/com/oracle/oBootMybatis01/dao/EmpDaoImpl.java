package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;

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
			tot = session.selectOne("tkEmpTotal");	// " "안에 있는 값을 통해서 Mybatis를 찾아간다.
			System.out.println("EmpDaoImpl total tot->"+tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->"+e.getMessage());
		}
		
		return tot;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start...");
		try {
			//	Naming Rule					Map ID		parameter
			empList = session.selectList("tkEmpListAll3", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()->"+empList.size());
			for(Emp rtnEmp : empList) {
				System.out.println("EmpDaoImpl listEmp rtnEmp.getEname()->"+rtnEmp.getEname());
				System.out.println("EmpDaoImpl listEmp rtnEmp.getSal()->"+rtnEmp.getSal());
			}
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->"+e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = new Emp();
		System.out.println("EmpDaoImpl detail Start...");
		try {
			//						mapper ID	, Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->"+emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->"+e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl update Start...");
		int kkk = 0;
		try {
			kkk = session.update("tkEmpUpdate", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception->"+e.getMessage());
		}
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager Start...");
		try {
//					emp 관리자만 select	Naming rule
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->"+e.getMessage());
		}
		
		return empList;
	}

}
