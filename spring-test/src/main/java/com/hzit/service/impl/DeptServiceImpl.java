package com.hzit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzit.bean.Dept;
import com.hzit.dao.DeptDao;
import com.hzit.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptDao dao;

	@Override
	public int addDept(Dept dept) {
		int insert = dao.insert(dept);
		return insert;
	}

	@Override
	public Dept findDeptById(int deptno) {
		Dept dept = dao.findDeptById(deptno);
		return dept;
	}

}
