package com.example.oBootJpa02.repository;

import java.util.List;

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
		System.out.println("team.setName After...");
		em.persist(team);
		System.out.println("em.persist(team) After...");
		// 회원 저장
		member.setTeam(team);		// 단방향 연관관계 설정, 참조 저장
		System.out.println("member.setTeam(team) After...");
		em.persist(member);
		System.out.println("em.persist(member) After...");
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("JpaMemberRepository findAll Before...");
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAll After...");
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		//						Entity			PK
		Member member = em.find(Member.class, memberId);
		return member;
	}

	// 연관관계 고려 Update
	@Override
	public int updateByMember(Member member) {
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember member.getId()->"+member.getId());
		Member member3 = em.find(Member.class, member.getId());
		// Exist --> Update
		if(member3 != null) {
			System.out.println("JpaMemberRepository updateByMember member.getTeamid()->"+member.getTeamid());
			// Team Exist --> Update
			Team team = em.find(Team.class, member.getTeamid());
			if(team != null) {
				System.out.println("JpaMemberRepository updataeByMember member.getTeamname()->"+member.getTeamname());
				team.setName(member.getTeamname());
				em.persist(team);
			}
			// 회원 저장
			System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
			member3.setTeam(team);				// 단방향 연관관계 설정, 참조저장
			member3.setName(member.getName());	// 단방향 연관관계 설정, 참조저장
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember persist 이후 member.getName()->"+member.getName());
			result = 1;
		}else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}
		return result;
	}

}
