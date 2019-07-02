package com.hzit.service;

import com.hzit.bean.Dept;
import com.hzit.bean.Emp;

public interface IEmpService {

	public int addEmp(Emp emp);

	public Emp findEmpById(int empno);

}
