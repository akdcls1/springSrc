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

import com.oracle.oBootMybatis01.dao.EmpDao;
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
		
		int total = es.total();	// Emp Count -> 42

		System.out.println("EmpController total->"+total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());	// 시작시 1
		emp.setEnd(pg.getEnd());		// 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>"+listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
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
		return "redirect:list";
	}

}
