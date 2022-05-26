package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

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
//			empList = session.selectList("tkEmpListAll3", emp);
			empList = session.selectList("tkEmpListAll", emp);
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

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insert Start...");
		try {
			result = session.insert("insertEmp", emp);
			System.out.println("EmpDaoImpl insert After...");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insert Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		System.out.println("EmpDaoImpl delete Start...");
		try {
			result = session.delete("delete", empno);
			System.out.println("EmpDaoImpl delete After result->"+result);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Emp> listEmpKeyword(Emp emp) {
		List<Emp> listEmpKeyword = null;
		System.out.println("EmpDaoImpl listEmpKeyword Start...");
		System.out.println("EmpDaoImpl listEmpKeyword emp.getEname->"+emp.getEname());
		System.out.println("EmpDaoImpl listEmpKeyword emp.getKeyword->"+emp.getKeyword());
		if(emp.getKeyword() == null) emp.setKeyword("%");
//		if(emp.getEname() == null) emp.setEname("%");
		try {
			//	keyword 검색
			//	Naming Rule					Map 	ID				parameter
			listEmpKeyword = session.selectList("tkEmpListKeyword", emp);
			System.out.println("EmpDaoImpl listEmpKeyword.size()->"+listEmpKeyword.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpKeyword Exception->"+e.getMessage());
		}
		return listEmpKeyword;
	}

	@Override
	public int totalKeyword(Emp emp) {
		int tot = 0;
		System.out.println("EmpDaoImpl totalKeyword Start...");
		try {
			//	Naming Rule		Map 	ID				parameter
			tot = session.selectOne("tkEmpTotalKeyword", emp);
			System.out.println("EmpDaoImpl listEmpKeyword.size()->"+tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpKeyword Exception->"+e.getMessage());
		}
		return tot;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDept = null;
		System.out.println("EmpDaoImpl listEmpKeyword Start...");
		try {
			//	Naming Rule				Map 	ID
			empDept = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl listEmpKeyword.size()->"+empDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpKeyword Exception->"+e.getMessage());
		}
		return empDept;
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpDaoImpl listEmp Start...");
		List<EmpDept> empDept3 = null;
		try {
			//	Naming Rule				Map 	ID
			empDept3 = session.selectList("tkListEmpDept", empDept);
			System.out.println("EmpDaoImpl listEmpKeyword.size()->"+empDept3.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpKeyword Exception->"+e.getMessage());
		}
		return empDept3;
	}


}
