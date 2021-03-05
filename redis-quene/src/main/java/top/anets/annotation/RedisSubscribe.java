/**
 * 
 */
package top.anets.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPubSub;

/**
 * @author Administrator
 *
 */
public class RedisSubscribe extends JedisPubSub{
    private Object target;
    private Method method;
    
    /**
	 * 
	 */
	public RedisSubscribe(Object target,Method method) {
		this.target=target;
		this.method=method;
	}
	
	@Override
	public void onMessage(String channel, String message) {
		try {
			System.out.println("开始调用");
			method.invoke(target, channel);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("调用错误");
			e.printStackTrace();
		}
	}
}
