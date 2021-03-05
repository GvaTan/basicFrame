package com.cy.pj.common.web;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cy.pj.common.exception.ServiceException;
/***
 * Spring MVC 中的拦截器对象(可以在后端控制器执行前后进行请求和响应的拦截处理)
 */
public class TimeHandlerInterceptor implements HandlerInterceptor {
	    /**
	     * 此方法在后端控制器方法执行之前执行
	     */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        		throws Exception {
            System.out.println("==preHandle===");
            //1.获取当前日历对象
            Calendar c=Calendar.getInstance();
            //2.设置允许方法的开始时间
            c.set(Calendar.HOUR_OF_DAY,8);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            long start=c.getTimeInMillis();
            //3.设置终止访问的时间
            c.set(Calendar.HOUR_OF_DAY,22);
            long end=c.getTimeInMillis();
            long cTime=System.currentTimeMillis();
            if(cTime<start||cTime>end)
            	throw new ServiceException("请在规定时间访问:9~18");
        	return true;//代表请求是否拦截
        }
  
   
}



