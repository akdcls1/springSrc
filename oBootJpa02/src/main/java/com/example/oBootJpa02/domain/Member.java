package com.example.oBootJpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "member_seq_gen",					// 객체이름
				   sequenceName = "member_seq_generator",	// 매핑할 DB 시퀀스 이름
				   initialValue = 1,						// 시퀀스 시작 번호
				   allocationSize = 1)						// 증가치 번호
@Table(name = "member1")
public class Member {
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen"
					)
	@Column(name = "member_id", length = 4)
	private Long id;
	
	@Column(name = "user_name", length = 50)
	private String name;
	
	// FK	--> 다 대 1 원칙	여러명이 하나의 팀을 지정하고있다
	@ManyToOne
	@JoinColumn(name = "team_id")	// FK로 연관관계를 맺는다.
	private Team team;
	
	// Table Column X, 버퍼
	@Transient
	private Long teamid;
	@Transient
	private String teamname;
}
