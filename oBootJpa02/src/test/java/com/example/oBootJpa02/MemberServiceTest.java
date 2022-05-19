package com.example.oBootJpa02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.oBootJpa02.domain.Member;
import com.example.oBootJpa02.repository.MemberRepository;
import com.example.oBootJpa02.service.MemberService;

//@SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@BeforeEach
	public void before1() {
		System.out.println("Test BeforeEach Start...");
	}
	
	@Test
	@Rollback(value = true)
	public void memberSave() {
		// 1. 조건 --> 멤버, 팀 저장
		Member member = new Member();
		member.setTeamname("4조");
		member.setName("안성현");

		// 2. 수행
		Member member3 = memberService.join(member);
		
		// 3. 결과
		System.out.println("MemberServiceTest memberSave member3.getId()->"+member3.getId());
		System.out.println("MemberServiceTest memberSave member3.getName()->"+member3.getName());
		System.out.println("MemberServiceTest memberSave member3.getTeam().getTeam_id->"+member3.getTeam().getTeam_id());
	}
}
