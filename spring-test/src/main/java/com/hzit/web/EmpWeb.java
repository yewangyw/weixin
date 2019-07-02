package com.hzit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzit.service.IEmpService;

@Controller
public class EmpWeb {

	@Autowired
	IEmpService empService;

}
