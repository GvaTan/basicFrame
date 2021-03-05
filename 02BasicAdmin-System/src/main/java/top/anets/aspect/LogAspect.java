/**
 * 
 */
package top.anets.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import top.anets.annotation.RequireLog;
import top.anets.entity.Log;
import top.anets.service.LogService;

/**
 * @author Administrator
 *
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
	@Pointcut("@annotation(top.anets.annotation.RequireLog)")
     public void logPointcut() {};
     @Around("logPointcut()")
     public Object around(ProceedingJoinPoint jp) throws Throwable {
    	 long t1=System.currentTimeMillis();
     	Object result=jp.proceed();//调用下一个切面或目标方法
     	long t2=System.currentTimeMillis();
     	//将用户行为信息存储到数据库中.
     	saveLog(jp, (t2-t1));
     	return result;
    	
     }
	/**
	* 
	*@param jp
	*@param time
	 * @throws JsonProcessingException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	*/
     @Autowired
     private LogService logService;
	private void saveLog(ProceedingJoinPoint jp, long time) throws JsonProcessingException, NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		//1.获取用户行为日志(谁(ip+用户名)在什么时间访问了什么方法,传递什么参数,执行时长是多少等)
		 //2.1获取目标方法
		 //2.1.1获取方法签名(记录的是目标方法信息)
		 MethodSignature ms=(MethodSignature)jp.getSignature();
		// ms.getDeclaringType();有可能是接口,也有可能是实现类类型,取决于代理的方式
		 //2.1.2获取目标类型的字节码对象
		 Class<?> targetClass=jp.getTarget().getClass();
		 //2.1.3获取目标方法对象
		 Method targetMethod=
		 targetClass.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
		 //2.1.4获取目标方法对象上的注解
		 RequireLog rLog=targetMethod.getAnnotation(RequireLog.class);
		 //2.1.5获取操作名
		 String operation=rLog.operation();
		 //2.1.5获取方法全名
		 String dType=targetClass.getName();
		 String methodName=ms.getName();
		 String targetClassMethod=dType+"."+methodName;
		 //2.2获取方法参数
		// String params=Arrays.toString(jp.getArgs());
		 String params=//值为jason格式字符串
		 new ObjectMapper().writeValueAsString(jp.getArgs());
		 //2.对信息进行封装(SysLog)
		 Log log=new Log();
//		 log.setIp(IPUtils.getIpAddr());
//		 log.setUsername(ShiroUtil.getUsername());
		 
		 log.setIp(null);
		 log.setUsername(null);
		 log.setOperation(operation);
		 log.setMethod(targetClassMethod);//targetClass.targetMethod
		 log.setParams(params);
		 log.setTime(time);
		 log.setCreatedTime(new Date());
		 //3.将信息写入到数据库.
		 new Thread() {
			 public void run() {
				 logService.saveLog(log);
			 };
		 }.start();
		
	}
     
}
