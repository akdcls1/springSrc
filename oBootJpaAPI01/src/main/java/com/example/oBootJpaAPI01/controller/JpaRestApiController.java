package com.example.oBootJpaAPI01.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.oBootJpaAPI01.domain.Member;
import com.example.oBootJpaAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Controller + ResponseBody(*****)
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
													  .map(m -> new MemberRtnDto(m.getName(), m.getSal()))
													  .collect(Collectors.toList());
		return new Result(memberCollect.size(),  memberCollect);
	}
	
	// 1. Entity보안
	// 2. 유연성 --> Entity가 API에 의존적 X, 원하는 Data 생성 , 전달
	// T는 인스턴스를 생성할 때 구체적인 타입으로 변경
	@Data
	@AllArgsConstructor
	class Result<T>{
		private int totCount;
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private String name;
		private Long sal;
	}
	
	/*
	 * 수정 API
	 * PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용하는 것이 맞다.
	 * URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 */
	@PutMapping("/api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
											   @RequestBody @Valid UpdateMemberRequest uMember) {
		memberService.update(id, uMember.getName(),uMember.getSal());
		Member findMember = memberService.findByMember(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
	}
	
	@Data
	static class UpdateMemberRequest{
		private String 	name;
		private Long 	sal;
	}
	
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse{
		private Long	id;
		private String	name;
		private Long	sal;
	}
}
