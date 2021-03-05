/**
 * 
 */
package top.anets;

import java.io.Serializable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.anets.redis.RedisService;
import top.anets.utils.SerializeUtil;

/**
 * @author Administrator
 *
 */
@SpringBootTest
public class QueneTest {
    @Autowired
    private RedisService redisService;
    
    private static int i=0;
    @Test
    public void produce() {
        System.out.println("生产者启动");
//      消费者去  代开票队列 读取 任务
        while(true) {
       	 try {
       		System.out.println("生产一个任务");
       		
       		
       		Boolean result = redisService.lpush("wait", ("产品"+(i++)+"").getBytes());
       		
       		if(result) {
       			System.out.println("生产任务完毕");
       		}else {
       			System.out.println("发生错误");
       		}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("线程被阻断");
				e.printStackTrace();
			}
        }
    }
    
    @Test
    public void consumer() {
         System.out.println("消费者启动");
//       消费者去  代开票队列 读取 任务
         while(true) {
        	 try {
        		System.out.println("从队列取一个任务");
        		List<byte[]> list = redisService.brpop("wait",0);
        		if(list==null) {
        			System.out.println("返回值为空");
        		}else {
        			String unSerialize = (String) SerializeUtil.unSerializeObjOrString(list.get(0));
        			System.out.println(unSerialize);
        			System.out.println((String) SerializeUtil.unSerializeObjOrString(list.get(1)));
        		}
        		System.out.println("从队列取出任务完毕");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("线程被阻断");
				e.printStackTrace();
			}
         }
    }
}
