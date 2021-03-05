package com.cy.pj.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysTimeAspect {
	/**
	 * 切入点
	 */
	@Pointcut("bean(*ServiceImpl)")
	public void doTime(){}
    /**
     * 目标方法之前
     * @param jp
     */
	@Before("doTime()")
	public void doBefore(JoinPoint jp){
		System.out.println("time doBefore()");
	}
	/**
	 * 目标方法执行之后
	 */
	@After("doTime()")
	public void doAfter(){//finally{}
		System.out.println("time doAfter()");
	}
	/**核心业务正常结束时执行
	 * 说明：假如有after，先执行after,再执行returning*/
	@AfterReturning("doTime()")
	public void doAfterReturning(){
		System.out.println("time doAfterReturning");
	}
	/**核心业务出现异常时执行
                 说明：假如有after，先执行after,再执行Throwing*/
	@AfterThrowing(pointcut="doTime()",throwing = "e")
	public void doAfterThrowing(JoinPoint jp,Exception e){
		MethodSignature ms=(MethodSignature)jp.getSignature();
		Method targetMethod=ms.getMethod();
		System.out.println("targetMethod="+targetMethod);
		System.out.println("e.message="+e.getMessage());
		System.out.println("time doAfterThrowing");
	}
	//ProceedingJoinPoint 一个特殊的连接点,只应用环绕通知
	@Around("doTime()")
	public Object doAround(ProceedingJoinPoint jp)
			throws Throwable{
		System.out.println("doAround.before");
		Object obj=jp.proceed();//检查类中是否有@Before通知,然后检查是否有下一个切面,没有则执行目标方法
		System.out.println("doAround.after");
		return obj;//检查类中是否有@After通知,有则执行after
	}
}




