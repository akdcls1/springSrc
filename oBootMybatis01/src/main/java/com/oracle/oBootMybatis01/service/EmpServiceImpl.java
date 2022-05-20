package com.oracle.oBootMybatis01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.EmpDao;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private	EmpDao	ed;

	@Override
	public int total() {
		System.out.println("EmpServiceImpl STart total...");
		int totCnt = ed.total();
		System.out.println("EmpSErviceImpl total totCnt->"+totCnt);
		return totCnt;
	}

}
