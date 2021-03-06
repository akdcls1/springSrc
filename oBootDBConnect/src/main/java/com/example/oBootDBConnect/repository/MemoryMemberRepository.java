package com.example.oBootDBConnect.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.oBootDBConnect.dto.Member1;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<>();
	private static long sequence = 0L;
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		//	저장 : put	1	member1.getName --> 김유신
		store.put(member1.getId(), member1);
		Member1 member3 = new Member1();
		// 조회 : get		1	
		member3 = store.get(member1.getId());
		System.out.println("MemoryMemberRepository member3.getName()->"+member3.getName());
		return member1;
	}
	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		// store의 value(Member1)
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll slistMember.size()->"+listMember.size());
		return listMember;
	}

}
