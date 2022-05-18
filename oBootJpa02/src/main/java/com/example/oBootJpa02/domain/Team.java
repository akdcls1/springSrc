package com.example.oBootJpa02.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {
	@Id
	private Long team_id;
	private String name;
	
}
