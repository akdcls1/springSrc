package com.example.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.oBootDBConnect.repository.JdbcMemberRepository;
import com.example.oBootDBConnect.repository.MemberRepository;
import com.example.oBootDBConnect.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;

	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();  Memory 사용
		return new JdbcMemberRepository(dataSource);
	}
}
