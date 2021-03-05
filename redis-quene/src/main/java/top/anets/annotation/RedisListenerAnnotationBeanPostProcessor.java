/**
 * 
 */
package top.anets.annotation;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import top.anets.redis.RedisService;

/**
 * @author Administrator
 *
 */
@Component
public class RedisListenerAnnotationBeanPostProcessor implements BeanPostProcessor{
	@Autowired
	private RedisService redisService;
	
	 private ExecutorService executorService;
	 
	 RedisListenerAnnotationBeanPostProcessor(){
		 executorService = Executors.newCachedThreadPool();
	 }
	 
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}
	
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    	// TODO Auto-generated method stub
    	Class<? extends Object> aClass = bean.getClass();
    	Method[] methods = aClass.getMethods();
    	for (Method method : methods) {
			RedisListener annotation = method.getAnnotation(RedisListener.class);
			if(annotation!=null) {
				System.out.println("找到了对应类");
				String channel=StringUtils.isEmpty(annotation.channel())?"*":annotation.channel();
				
				System.out.println(channel);
				 executorService.execute(()->redisService.subscribe(new RedisSubscribe(bean, method), channel));
			}
		}
    	return bean;
    }
}
