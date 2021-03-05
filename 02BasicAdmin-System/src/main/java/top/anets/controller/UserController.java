/**
 * 
 */
package top.anets.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.anets.annotation.RequireLog;
import top.anets.vo.Result;

/**
 * @author Administrator
 *
 */
@RequestMapping("/admin")
@RestController
public class UserController {
	@RequireLog(operation="登陆操作")
	@RequestMapping("login")
    public Result login(String username,String password) {
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
    	return new Result(true,"Welcome", null);
    }
}
