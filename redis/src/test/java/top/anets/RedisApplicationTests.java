package top.anets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.anets.redis.RedisConfig;
import top.anets.redis.RedisKey;
import top.anets.redis.RedisService;

@SpringBootTest
class RedisApplicationTests {
	@Value("${redis.ip}")
	private String ip;
	@Value("${redis.port}")
    private Integer port;
    
	
	/**
	 * 简单测试
	 */
	@Test
	void redisDemo() { 
		Jedis jedis = new Jedis(ip, port);
		jedis.set("username", "谭洋波");
		
		System.out.println(jedis.get("username"));
	}
	/**
	 * 对redis封装测试
	 */
	@Autowired
	private RedisService redisService ;
	@Test
	void testJedisPool() {
         redisService.set(RedisKey.USER.getPrefix()+8,"tyb", 20);
         
         
         System.out.println(redisService.get(RedisKey.USER.getPrefix()+8));
	}


}


