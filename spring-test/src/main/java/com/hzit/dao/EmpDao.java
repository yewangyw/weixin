package com.hzit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hzit.bean.Dept;
import com.hzit.bean.Emp;

@Repository
public class EmpDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(Emp emp) {
		System.out.println("JDBC insert emp....");
		String sql = "INSERT INTO emp(ename,job,mgr,hireDate,sal,deptno) VALUES(?,?,?,?,?,?)";
		int update = jdbcTemplate.update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHireDate(),
				emp.getSal(), emp.getDeptno());
		return update;
	}

	public Emp findEmptById(int empno) {
		System.out.println("JDBC select emp....");
		String sql = "SELECT * FROM emp WHERE empno=?";
		Emp emp = jdbcTemplate.queryForObject(sql, new Object[] { empno }, new BeanPropertyRowMapper<>(Emp.class));
		return emp;
	}

}
