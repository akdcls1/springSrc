package com.example.oBootJpa01.repository;

import javax.persistence.EntityManager;

import com.example.oBootJpa01.domain.Member;

public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		System.out.println("JpaMemberRepository save start...");
		// 저장 Method
		em.persist(member);
		return member;
	}

}
