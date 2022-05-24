package com.oracle.oBootMybatis01.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

@Controller
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService es;

	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		logger.info("EmpController Start list...");
		
		int total = es.total();	// Emp Count -> 19

		System.out.println("EmpController total->"+total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());	// 시작시 1
		emp.setEnd(pg.getEnd());		// 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>"+listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "test01/test01";
	}
	
	// keyword(조건) 조회
	@RequestMapping(value="listKeyword")
	public String listKeyword(Emp emp, String currentPage, Model model) {
		logger.info("EmpController Start listKeyword keyword(조건) 조회...");
		
		// Keyword 맞는 Count 도출
		int total = es.totalKeyword(emp);	// Emp Count -> 19

		System.out.println("EmpController total->"+total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());	// 시작시 1
		emp.setEnd(pg.getEnd());		// 시작시 10
		System.out.println("EmpController pg.getTotal->"+pg.getTotal());
		System.out.println("EmpController pg.getStart()->"+pg.getStart());
		System.out.println("EmpController pg.getEnd()->"+pg.getEnd());
		List<Emp> listEmpKeyword = es.listEmpKeyword(emp);
		System.out.println("EmpController list listEmp.size()=>"+listEmpKeyword.size());
		model.addAttribute("listEmp", listEmpKeyword);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("keyword", emp.getKeyword());
		if(emp.getKeyword() == null)
			return "list";
		else
			return "listKeyword";
	}
	
	@GetMapping(value="detail")
	public String detail(int empno, Model model) {
//		Emp emp = es.detail(emp);
		Emp emp = es.detail(empno);
//		Dao -> ed.detail(emp);
//		EmpDao ed = ed.detail(emp);
//		mapper --> tkEmpSelOne, empno
		System.out.println("EmpController Start detail...");
		
		model.addAttribute("emp", emp);
		return "detail";
	}
	
	@GetMapping(value="updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController Start updateForm...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		return "updateForm";
	}
	
	@PostMapping(value="update")
	public String update(Emp emp, Model model) {
		int uptCnt = es.update(emp);
//		int kkk = es.update(emp)
//		Dao -> ed.update(emp)
//		mapper -> tkEmpUpdate , emp
		
		System.out.println("es.update(emp) Count --> "+uptCnt);
		model.addAttribute("uptCnt", uptCnt);
		model.addAttribute("kk3", "Message Test");
//		return "redirect:list";
		return "forward:list";
	}

	@RequestMapping(value="writeForm")
	public String writeForm(Model model) {
//		 Emp emp = null;
		// 관리자 사번만 Get
		List<Emp> empList = es.listManager();
		// Service ---> List<Emp> listManager()
		// Dao ->   List<Emp> listManager()
		// mapper --> tkSelectManager  [emp 관리자만 Select   ]

		System.out.println("EmpController writeForm empList.size()->"+empList.size());
		model.addAttribute("empMngList", empList);	// emp Manager List
		// 부서(코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);	// dept
		return "writeForm";
	}
	
	@RequestMapping(value="write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController Start write...");
//		System.out.println("emp.getHiredate->"+emp.getHiredate());
//		Service, Dao, Mapper명[insertEmp]까지 -> insert
		int result = es.insert(emp);
		if(result >0) 
			return "redirect:list";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:wrtieForm";
		}
	}
	
	@GetMapping(value="confirm")
	public String comfirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			model.addAttribute("msg", "중복된 사번입니다.");
			return "forward:writeForm";
		} else {
			model.addAttribute("msg", "사용 가능한 사번입니다.");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value="delete")
	public String delete(int empno, Model model) {
		System.out.println("EmpController Start delete...");
		// Service   int delete(int empno)
		// Dao       int delete(int empno)
		// Mapper    session.delete("delete",empno);
		int result = es.delete(empno);
		return "redirect:list";
	}
	
}
