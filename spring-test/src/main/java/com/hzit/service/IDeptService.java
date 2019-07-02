package com.hzit.service;

import com.hzit.bean.Dept;

public interface IDeptService {

	public int addDept(Dept dept);

	public Dept findDeptById(int deptno);

}
