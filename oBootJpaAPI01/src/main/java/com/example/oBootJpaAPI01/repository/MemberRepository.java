package com.example.oBootJpaAPI01.repository;

import com.example.oBootJpaAPI01.domain.Member;

public interface MemberRepository {
	Long save(Member member);
}
