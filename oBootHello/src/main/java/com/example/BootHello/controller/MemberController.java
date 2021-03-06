package com.example.BootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BootHello.dto.Member1;
import com.example.BootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
//	MemberService memberService = new MemberService(); 전통적인 방식 윗줄과 같은 뜻
	
	@GetMapping(value="/members/inputForm")
	public String inputForm() {
		System.out.println("MemberController /members/inputForm start...");
		return "members/inputMemberForm";
	}
	
	@PostMapping(value="/members/save")
	public String save(Member1 member1) {	//html에서 선언된 이름이 dto에 있는 이름과 동일하면 알아서 받아들인다.
		System.out.println("MemberController /members/save start...");
		Long id = memberService.join(member1);
		System.out.println("MemberController /members/save id->"+id);
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
