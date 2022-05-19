package com.example.oBootJpaAPI01.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.oBootJpaAPI01.service.MemberService;
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
	
	// 내부 Class
	class CreateMemberResponse{
		private Long id;

		public CreateMemberResponse(Long id) {
			this.id = id;
		}
		
	}
}
