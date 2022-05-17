package com.example.oBootJpa01.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.oBootJpa01.domain.Member;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public Long join(Member member) {
		System.out.println("MemberService join member.getId()->"+member.getId());
		memberRepository.save(member);
		return member.getId();
	}
	
}
