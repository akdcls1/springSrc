package com.example.oBootJpaAPI01.repository;

import java.util.List;

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

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		// member3. ifPresent(object);
		if(member3 != null) {
			// 회원 저장
			member3.setName(member.getName());	// 저장
			member3.setSal(member.getSal());	// 저장
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist..");
		}
		return result;
	}

	@Override
	public Member findByMbmer(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return member;
	}

}
