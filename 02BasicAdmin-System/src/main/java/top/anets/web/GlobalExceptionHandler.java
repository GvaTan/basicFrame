/**
 * 
 */
package top.anets.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import top.anets.vo.Result;

/**
 * @author Administrator
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
      @ExceptionHandler(RuntimeException.class)
      public Result doHandlerRuntimeException(RuntimeException e) {
    	  e.printStackTrace();
    	  return new Result(false,e.getMessage(), null);
      }
      
      @ExceptionHandler(ShiroException.class)
  	public Result doHandleShiroException(
  			ShiroException e) {
  		Result r=new Result();
  		r.setSuccess(false);
  		if(e instanceof UnknownAccountException) {
  			r.setMsg("账户不存在");
  		}else if(e instanceof LockedAccountException) {
  			r.setMsg("账户已被禁用");
  		}else if(e instanceof IncorrectCredentialsException) {
  			r.setMsg("密码不正确");
  		}else if(e instanceof AuthorizationException) {
  			r.setMsg("没有此操作权限");
  		}else {
  			r.setMsg("系统维护中");
  		}
  		e.printStackTrace();
  		return r;
  	}
}
