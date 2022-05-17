package com.example.oBootJpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.oBootJpa01.domain.Member;

//@Repository
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

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m",Member.class)
									.getResultList();
		return memberList;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name+'%';
		System.out.println("JpaMemberRepository findByNames pname -> "+pname);
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
									.setParameter("name", pname)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}

}
