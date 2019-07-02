package com.hzit.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.hzit.bean.Dept;
import com.hzit.service.IDeptService;

/**
 * web层 或者 Controller控制层
 * 
 * @author Administrator
 *
 */
@Controller
public class DeptWeb {

	@Autowired
	IDeptService deptService;

}
