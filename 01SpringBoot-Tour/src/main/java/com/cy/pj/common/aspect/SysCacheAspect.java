package com.cy.pj.common.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10)
@Aspect
@Component
public class SysCacheAspect {
	 // private ConcurrentHashMap<Object,Object> cacheMap=new ConcurrentHashMap<>();
	  @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
	  public void doCache() {}
	  @Around("doCache()")
	  public Object around(ProceedingJoinPoint jp)throws Throwable{
		  System.out.println("Get data from cache");
		 // Object obj=cacheMap.get(key);
		  //if(obj!=null)return obj;
		 Object obj=jp.proceed();
		 // cacheMap.put(key, obj);
		  System.out.println("Put data to cache");
		  return obj;
	  }
}
