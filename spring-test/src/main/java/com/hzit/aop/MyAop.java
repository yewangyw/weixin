package com.hzit.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.hzit.bean.Dept;
import com.hzit.bean.Emp;

@Component // 必须添加到spring容器
@Aspect
public class MyAop {

	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.hzit.service.*.add*(..))")
	public void anMenthod() {

	}

	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.hzit.service.*.find*(..))")
	public void anMenthod02() {

	}

	/**
	 * 定义增强处理(前置|后置|最终|异常|环绕)
	 */

	/**
	 * point：连接点信息
	 * 
	 * 创建后置增强
	 */
	@After("anMenthod()")
	public void after(JoinPoint point) {

		// 添加的数据
		Object object2 = point.getArgs()[0]; // 获取所有的参数列表 0:第一个参数

		// 默认所有信息
		String name = object2.toString();

		// 假设值需要name
		if (object2 instanceof Emp) {
			Emp emp = (Emp) object2;
			name = emp.getEname();
		}

		if (object2 instanceof Dept) {
			Dept dept = (Dept) object2;
			name = dept.getDname();
		}

		// Object object = point.getTarget(); //目标对象

		// System.out.println(point.getSignature());
		// System.out.println(point.getTarget().getClass().getName());
		// System.out.println("最终通知");

		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(format + "添加了 记录..." + name);
	}

}
