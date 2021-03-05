/**
 * 
 */
package top.anets.annotation;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class TargetObj {
	@RedisListener(channel = "test")
    public void fun(String message) {
    	System.out.println("接收到消息"+message);
    }
}
