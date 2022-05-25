package com.oracle.oBootMybatis01.contoller;

import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

@Controller
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService es;
	@Autowired
	private JavaMailSender mailSender;

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
		
		return "list";
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
		/*if(emp.getKeyword() == null)
			return "list";
		else*/
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
	
	// Join 조회
	@GetMapping(value="listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		System.out.println("EmpController listEmpDept Start...");
		// Service, Dao -> listEmpDept
		// Mapper만 -> tkListEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
	}
	
	@RequestMapping(value="mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailSending...");
		String tomail = "akdcls1@naver.com";		// 받는사람 이메일
		System.out.println(tomail);
		String setfrom = "card3351@gmail.com";
		String title="mailTransport 입니다";	// 제목
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);			// 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);			// 받는사람 이메일
			messageHelper.setSubject(title);		// 메일 제목은 생략이 가능하다
			String tempPassword = (int)(Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다 : "+tempPassword);		// 메일 내용
			System.out.println("임시 비밀번호입니다 : "+tempPassword);
			DataSource dataSource = new FileDataSource("E:\\spring\\springSrc\\log\\8.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);		// 정상 전달
//			s.tempPw(u_id, tempPassword);		// db에 비밀번호를 임시비밀번호로 업데이트
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2);		// 메일 전달 실패
		}
		return "mailResult";
	}
}
