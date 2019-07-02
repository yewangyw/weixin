package com.hzit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hzit.bean.Dept;

@Repository
public class DeptDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(Dept dept) {
		System.out.println("JDBC insert dept....");
		int update = jdbcTemplate.update("INSERT INTO dept(dname,loc) VALUES(?,?)", dept.getDname(), dept.getLoc());
		return update;
	}

	public Dept findDeptById(int deptno) {
		System.out.println("JDBC select dept....");
		String sql = "SELECT * FROM dept WHERE deptno=?";
		Dept dept = jdbcTemplate.queryForObject(sql, new Object[] { deptno }, new BeanPropertyRowMapper<>(Dept.class));
		return dept;
	}

	// 增删改

}
