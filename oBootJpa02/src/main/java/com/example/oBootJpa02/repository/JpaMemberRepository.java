package com.example.oBootJpa02.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.oBootJpa02.domain.Member;
import com.example.oBootJpa02.domain.Team;

@Repository
public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		// 회원 저장
		member.setTeam(team);		// 단방향 연관관계 설정, 참조 저장
		em.persist(member);
		return member;
	}

}
