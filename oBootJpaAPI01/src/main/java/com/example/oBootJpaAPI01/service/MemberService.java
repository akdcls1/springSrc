package com.example.oBootJpaAPI01.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oBootJpaAPI01.domain.Member;
import com.example.oBootJpaAPI01.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public Long join(Member member) {
		System.out.println("MemberService join member.getName()->"+member.getName());
		Long id = memberRepository.save(member);
		return id;
	}
}
