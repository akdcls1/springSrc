package com.example.oBootJpa02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.oBootJpa02.domain.Member;
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
	
	@PostMapping(value = "/members/save")
	public String create(Member member) {
		logger.info("MemberController create start...");
		System.out.println("member.getTeamname()->"+member.getTeamname());
		System.out.println("member.getName()->"+member.getName());
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("memberList.get(0).getName()->"+memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam().getName()->"+memberList.get(0).getTeam().getName());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm")
	public String memberModify(Long id, Model model) {
		System.out.println("MemberController memberModify id->"+id);
		Member member = memberService.findByMember(id);
		
		System.out.println("member.get().getId()->"+member.getId());
		System.out.println("member.get().getName()->"+member.getName());
		System.out.println("member.get().getTeam().getName()->"+member.getTeam().getName());
		model.addAttribute("member", member);
		
		return "members/memberModify";
	}
	
	@GetMapping(value="/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController memberUpdate id->"+member.getId());
		System.out.println("MemberController memberUpdate member.getName->"+member.getName());
		System.out.println("MemberController memberUpdate member.getTeamname->"+member.getTeamname());
		memberService.memberUpdate(member);
		return "redirect:/members";
	}
}
