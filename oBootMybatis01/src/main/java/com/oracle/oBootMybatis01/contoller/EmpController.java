package com.oracle.oBootMybatis01.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		Paging pg = new Paging(total, currentPage);
		System.out.println("EmpController total->"+total);
		
		model.addAttribute("total", total);
		
		return "list";
	}

}
