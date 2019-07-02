package com.hzit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzit.bean.Dept;
import com.hzit.bean.Emp;
import com.hzit.dao.EmpDao;
import com.hzit.service.IEmpService;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private EmpDao dao;

	@Override
	public int addEmp(Emp emp) {
		return dao.insert(emp);
	}

	@Override
	public Emp findEmpById(int empno) {
		return dao.findEmptById(empno);
	}

}
