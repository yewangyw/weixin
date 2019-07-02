package com.hzit.web;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzit.bean.Dept;
import com.hzit.bean.Emp;
import com.hzit.service.IDeptService;
import com.hzit.service.IEmpService;

public class Testxx {

	private IDeptService deptService;

	private IEmpService empService;

	@Before
	public void init() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DeptWeb deptWeb = context.getBean(DeptWeb.class);
		deptService = deptWeb.deptService;

		EmpWeb empWeb = context.getBean(EmpWeb.class);
		empService = empWeb.empService;
	}

	@Test
	public void test01() {
		Dept dept = deptService.findDeptById(1126);
		System.out.println(dept);

		Emp emp = empService.findEmpById(1);
		System.out.println(emp);

	}

	@Test
	public void test02() {
		int row = deptService.addDept(new Dept(0, "人力资源中心", "深圳"));
		// System.out.println(row);
		Emp emp = new Emp(null, "蚯蚓", "吃虫子", 1, new Date(), 111111.0, 1126);
		int emp2 = empService.addEmp(emp);
//		System.out.println(emp2);

	}

}
