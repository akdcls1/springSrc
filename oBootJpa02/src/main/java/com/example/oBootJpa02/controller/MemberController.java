package com.example.oBootJpa02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.oBootJpa02.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("MemberController home Start...");
		return "members/index";
	}
	
	@GetMapping(value="/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
		logger.info("MemberController /members/new logger start...");
		return "members/createMemberForm";
	}
	
	
}
