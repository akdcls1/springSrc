package com.example.oBootJpaAPI01.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.oBootJpaAPI01.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Long save(Member member) {
		// 회원 저장
		em.persist(member);
		return member.getId();
	}

}
