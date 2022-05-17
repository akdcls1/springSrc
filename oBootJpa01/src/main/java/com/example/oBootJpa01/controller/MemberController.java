package com.example.oBootJpa01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.oBootJpa01.domain.Member;
import com.example.oBootJpa01.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value="/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
		return "members/createMemberForm";
	}
	
	@PostMapping(value="/members/save")
	public String create(Member member) {
		System.out.println("MemberController create start...");
		System.out.println("member.getId()->"+member.getId());
		System.out.println("member.getName()->"+member.getName());
		memberService.join(member);
		return "redirect:/";
	}
}
