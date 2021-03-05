/**
 * 
 */
package top.anets.redis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

/**
 * @author Administrator
 *
 */
@Service
public class RedisService {
	@Autowired
	private JedisPool jedisPool;
	
    
	 /**
     * 存储redis队列 顺序存储-list类型
     * @param key 字节类型
     * @param value 字节类型
     */
    public Boolean lpush(String key,byte[] value){
    	Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            jedis.lpush(key.getBytes(),value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
        	System.out.println("dddd");
        	returnToPool(jedis);
        }
    }
    
    /**
     * 存储REDIS队列 反序存储
     * @param key 字节类型
     * @param value 字节类型
     */
    public void rpush(byte[] key,byte[] value){
    	Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            jedis.rpush(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	returnToPool(jedis);
        }
    }
    
    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回,就可以实现任务队列
     * @param srckey 原队列的key
     * @param dstkey 目标队列的key
     */
    public  byte[] rpoplpush(byte[] srckey,byte[] dstkey){
        byte[] value = null;
        Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            value= jedis.rpoplpush(srckey,dstkey);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	returnToPool(jedis);
        }
        return value;
    }
    
    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param srckey
     * @param dstkey
     * @param timout
     * @return
     */
    public  byte[] brpoplpush(String srckey,String dstkey,int timout){
        byte[] value = null;
        Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            value = jedis.brpoplpush(srckey.getBytes(),dstkey.getBytes(),timout);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	returnToPool(jedis);
        }
        return value;
    }
    
    
    /**
     * 移除队列中的最后一个元素并显示最后一个元素, 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 设置实现任务队列的键和过期时间
     * @param key
     * @param timeout
     */
    public  List<byte[]> brpop(String key, int timeout){
        List<byte[]> result = null;
        Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            result=jedis.brpop(0, key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	returnToPool(jedis);
        }
        return result;
    }
    
    
    /**
     * 移除队列中的最后一个元素并显示最后一个元素
     * @param key
     * @return
     */
    public  byte[] rpop(String key) {
        byte[] bytes = null;
        Jedis jedis = null;
        try {
        	jedis =  jedisPool.getResource();
            bytes = jedis.rpop(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	returnToPool(jedis);
        }
        return bytes;
    }
    /**
     * 获取List中的存的内容
     * @param key
     * @return
     */
    public List<String> lrange(String key,Integer start,Integer stop) {
        Jedis jedis = null;
        List<String> lrange=null;
        try {
        	jedis =  jedisPool.getResource();
           lrange = jedis.lrange(key, start, stop);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	returnToPool(jedis);
        }
        return lrange;
    }
	/**
	 * 字符串存操作
	 * 
	 *@param key
	 *@param value
	 *@param seconds  过期时间  <=0表示不过期
	 */
	public void set(String key,String value,int seconds) {
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			if(seconds <= 0) {
				jedis.set(key, value);
			}else {
				jedis.setex(key, seconds, value);
			}

		}finally {
			returnToPool(jedis);
		}
	}
     
    

	/**
	 * 取值
	 * 
	 *@param key
	 *@return
	 */
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			return jedis.get(key);

		}finally {
			returnToPool(jedis);
		}
	}


	public Boolean exists(String key) {
		Jedis jedis = null;
		try {
			Boolean result = jedis.exists(key);
			jedis.close();
			return result;
		}finally {
			returnToPool(jedis);
		}
	}
	/**
	 * 查看有效时间
	 * 
	 *@param key
	 *@return
	 */
	public Long ttl(String key) {
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			return jedis.ttl(key);
		}finally {
			returnToPool(jedis);
		}
	}
	/**
	 * 对已经存在的key设置有效时间
	 * 
	 *@param key
	 *@return
	 */
	public void expire(String key,int seconds) {
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			jedis.expire(key, seconds);
		}finally {
			returnToPool(jedis);
		}
	}

	private void returnToPool(Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}else {
			throw new RuntimeException("连接异常，未获取到连接");
		}
	}


	/**
	 * 点赞，给id 加分 +1
	 * 
	 *@param key
	 *@param id
	 */
	public void increase(String key,Long id) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.zincrby(key, 1.0,id+"");
			// 0代表第一个元素,-1代表最后一个元素，保留热度由低到高末尾10个房产，因为zset是排序的，
			// 所以说默认的是从低到高，我们要保留前十名，我们就要把第一和倒数 第11之间的值移除掉  填坑   ~~这个是个错误，因为如果这样的话，那就永远只有10条了。错误逻辑
			// 最主要是记住api的用法 这里就注释了~
			// jedis.zremrangeByRank(HOT_HOUSE_KEY, 0, -11);
		} finally {
			returnToPool(jedis);
		}

	}
	/**
	 * 根据分数升序排列，返回
	 * 
	 *@param key
	 *@param start 区间起始
	 *@param end 区间结束
	 *@return 返回值是id列表
	 */
	public Set<String> getAsc(String key,int start ,int end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//            Set<String> set = jedis.zrevrange(key, 0, -1);
			Set<String> set = jedis.zrange(key, start,end);
			return  set;

		} finally {
			returnToPool(jedis);
		}

	}
    
	
	
	/**
	 * 根据分数降序排列，返回
	 * 
	 *@param key
	 *@param start 区间起始
	 *@param end 区间结束
	 *@return 返回值是id列表
	 */
	public Set<String> getDesc(String key,int start ,int end) {
		Jedis jedis = null;
		try {jedis = jedisPool.getResource();
		//            Set<String> set = jedis.zrevrange(key, 0, -1);
		Set<String> set = jedis.zrevrange(key, start, end);
		return  set;

		} finally {
			returnToPool(jedis);
		}

	}

	/**
	 * 根据分数降序排列，返回
	 * 
	 *@param key
	 *@param start 区间起始
	 *@param end 区间结束
	 *@return 返回值是id列表
	 */
	public Set<Tuple> getDescWithScore(String key,int start ,int end) {
		Jedis jedis = null;
		try { jedis = jedisPool.getResource();
		//            Set<String> set = jedis.zrevrange(key, 0, -1);
		Set<Tuple> scores = jedis.zrangeWithScores(key, start, end);


		return  scores;

		} finally {
			returnToPool(jedis);
		}

	}

	/**
	 * 增操作
	 * 
	 *@param key
	 *@return
	 */
	public Long incr(String key) {
		Jedis jedis = null;
		try {  jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		return result;
		}  finally {
			returnToPool(jedis);
		}
	}


	public Long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long result = jedis.hset(key, field, value);
			return result;
		}  finally {
			returnToPool(jedis);
		} 


	}

	public String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String result = jedis.hget(key, field);
			return result;
		}  finally {
			returnToPool(jedis);
		} 

	}

	public Long hdel(String key, String... field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long result = jedis.hdel(key, field);
			return result;
		}  finally {
			returnToPool(jedis);
		} 
	}
    /**
     * 发布消息给管道
     */
    public void publish(String channel,String message) {
    	Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			 jedis.publish(channel, message);
		}  finally {
			returnToPool(jedis);
		} 
    }
	
    /**
     * 从管道订阅消息
    * 
    *@return
     */
    public void subscribe(JedisPubSub jedisPubSub,String... channels ) {
    	Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			 jedis.subscribe(jedisPubSub, channels);
		}  finally {
			returnToPool(jedis);
		} 
    }
	public synchronized Jedis getJedis() {
    	Jedis jedis = null;
	    if (jedisPool != null) {
          	try {
	                  if (jedis == null) {
	                     jedis = jedisPool.getResource();
	                   }
	        } catch (Exception e) {
	              e.printStackTrace();
	        }
	      }
	  return jedis;
	}

	


}
