package com.zhys.timmer;

import com.zhys.redis.RedisUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

@Component
@Log
public class ScheduledTask {
    @Autowired
    private RedisUtils redis;




    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");










}
