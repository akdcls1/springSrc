package com.example.oBootJpa01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oBootJpa01.domain.Member;
import com.example.oBootJpa01.repository.MemberRepository;

//@Service
//JPA ㅅ용시
@Transactional
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
