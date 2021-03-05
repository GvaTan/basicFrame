package com.zhys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author HeyS1
 * @date 2016/12/1
 * @description threadPool订单线程池, 处理订单
 * scheduler 调度线程池 用于处理订单线程池由于超出线程范围和队列容量而不能处理的订单
 */
@Component
public class ThreadPoolManager {
    private static Logger log = LoggerFactory.getLogger(ThreadPoolManager.class);
    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 30;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 36;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;



    // 订单线程池
//   加final的好处
//    -1、final关键字提高了性能。JVM和Java应用都会缓存final变量。
//    -2、final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销。
//    -3、使用final关键字，JVM会对方法、变量及类进行优化。
    public  final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(WORK_QUEUE_SIZE));
    
    








}
