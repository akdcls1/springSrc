package com.example.oBootJpaAPI01.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.oBootJpaAPI01.domain.Member;
import com.example.oBootJpaAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Controller + ResponseBody
// 1. API
// 2. Ajax
@RestController
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	/*public JpaRestApiController(MemberService memberService) {
		this.memberService = memberService;
	}*/
	
	// 저장 API
	@PostMapping("/restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest requestMember) {
		System.out.println("JpaRestApiController /api/v2/memberSave request.getName()->"+requestMember.getName());
		Member member = new Member();
		member.setName(requestMember.getName());
		member.setSal(requestMember.getSal());
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	@Data
	static class CreateMemberRequest{
		@NotEmpty
		private String name;
		private Long sal;
	}
	
	// 내부 Class
	@Data
	class CreateMemberResponse{
		private Long id;

		public CreateMemberResponse(Long id) {
			this.id = id;
		}
		
	}
	
	// Bad API
	@GetMapping("/restApi/v1/members")
	public List<Member> membersV1(){
		System.out.println("JpaRestApiController /restApi/v1/members start...");
		return memberService.getListAllMember();
	}
	
	// Good API		--> 조별 project에 1개 이상 추가
	@GetMapping("/restApi/v2/members")
	public Result membersV2() {
		System.out.println("JpaRestApiController /restApi/v2/members start...");
		List<Member> findMembers = memberService.getListAllMember();

		/*
		//람다식을 사용하지 않았을 경우
		List<MemberRtnDto> memberCollect = null;
		for(Member member : findMembers) {
			System.out.println("member.getName()->"+member.getName());
			MemberRtnDto memberRtnDto = new MemberRtnDto(m.getName());
			memberCollect.add(memberRtnDto);
		}
		*/
		
		//  자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
		List<MemberRtnDto> memberCollect = findMembers.stream()
													  .map(m -> new MemberRtnDto(m.getName()))
													  .collect(Collectors.toList());
		return new Result(memberCollect);
	}
	
	@Data
	@AllArgsConstructor
	class Result<T>{
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private String name;
	}
}
