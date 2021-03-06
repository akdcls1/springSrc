package com.example.oBootJpaAPI01.service;

import java.util.List;

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
	// 전체 회원 조회
	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}

	public void update(Long id, String name, Long sal) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setSal(sal);
		System.out.println("MemberService memberUpdate member.getName()->"+member.getName());
		System.out.println("MemberService memberUpdate member.getSal()->"+member.getSal());
		memberRepository.updateByMember(member);
		return;
	}

	public Member findByMember(Long memberId) {
		Member member = memberRepository.findByMbmer(memberId);
		System.out.println("MemberService findByMember member.get().getId()->"+member.getId());
		System.out.println("MemberService findByMember member.get().getName()->"+member.getName());
		return member;
	}
}
