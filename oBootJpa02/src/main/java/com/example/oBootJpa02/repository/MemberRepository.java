package com.example.oBootJpa02.repository;

import java.util.List;

import com.example.oBootJpa02.domain.Member;

public interface MemberRepository {
	Member 			save(Member member);
	List<Member> 	findAll();
	Member 			findByMember(Long memberId);
	int 			updateByMember(Member member);
}
