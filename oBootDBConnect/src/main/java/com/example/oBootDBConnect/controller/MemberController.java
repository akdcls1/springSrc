package com.example.oBootDBConnect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.oBootDBConnect.dto.Member1;
import com.example.oBootDBConnect.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
//	MemberService memberService = new MemberService(); 전통적인 방식 윗줄과 같은 뜻
	
	@GetMapping("/")
	public String home() {
		System.out.println("MemberController start...");
		return "home";
	}
	
	@GetMapping(value="/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
		return "members/createMemberForm";
	}
	
	@PostMapping(value="/members/new")
	public String create(Member1 form) {	//html에서 선언된 이름이 dto에 있는 이름과 동일하면 알아서 받아들인다.
//		public String create(String name){
		Member1 member = new Member1();
		member.setName(form.getName());
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping(value="/members/memberList")
	public String memberList(Model model) {
		logger.info("memberList start...");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		logger.info("memberLists.size()->{}", memberLists.size());
		
		return "members/memberList";
	}
}
