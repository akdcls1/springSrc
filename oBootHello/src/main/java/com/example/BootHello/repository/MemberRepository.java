package com.example.BootHello.repository;

import java.util.List;

import com.example.BootHello.dto.Member1;

public interface MemberRepository {
	Member1 save(Member1 member1);

	List<Member1> findAll();
}
