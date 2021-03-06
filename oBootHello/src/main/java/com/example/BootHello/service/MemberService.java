package com.example.BootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BootHello.dto.Member1;
import com.example.BootHello.repository.MemberRepository;

@Service
public class MemberService {
//	MemberRepository memberRepository = new MemoryMemberRepository(); 전통적. 아래와같음
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public Long join(Member1 member1) {
		memberRepository.save(member1);
		return member1.getId();
	}

	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("memList.size()->"+memList.size());
		return memList;
	}
}
