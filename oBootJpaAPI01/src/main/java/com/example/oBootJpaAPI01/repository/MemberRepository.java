package com.example.oBootJpaAPI01.repository;

import java.util.List;

import com.example.oBootJpaAPI01.domain.Member;

public interface MemberRepository {
	Long 			save(Member member);
	List<Member> 	findAll();
	int 			updateByMember(Member member);
	Member 			findByMbmer(Long memberId);
}
