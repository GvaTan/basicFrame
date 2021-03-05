package com.zhys.controller;

import com.zhys.redis.RedisUtils;
import com.zhys.result.PlatformResult;
import com.zhys.result.ResultCode;
import com.zhys.utils.KeyUtils;
import com.zhys.utils.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/authorize")
@ApiIgnore
@CrossOrigin
public class ZuulController {

	@Autowired
	RedisUtils redisUtils;

	@Value("${zhys.appId}")
	private String appId;


    @GetMapping("/token")
	@ResponseBody
	public PlatformResult info(@RequestParam("appId") String appId, @RequestParam("appSecret") String appSecret){

    	if(!StringUtils.isEmpty(appId)&&!StringUtils.isEmpty(appSecret)&&appId.equals(appId)&&appSecret.equals(MD5.GetMD5Code(appId+"zhys"))){
    		//若appId或appSecret中有一个为空或者不为固定值则认为不合法

			//生成 token 并保存在 Redis 中
			String token = KeyUtils.genUniqueKey();
			//将token存储在 Redis 中。键是 TOKEN_用户id, 值是token
			boolean b = redisUtils.set("TOKEN_"+appId,token,7200L);
			if(!b){
				return PlatformResult.failure(ResultCode.SYSTEM_INNER_ERROR,ResultCode.SYSTEM_INNER_ERROR.message());
			}
			return PlatformResult.success(token);

		}else{
    		//其他情况都为不合法
			return PlatformResult.failure(ResultCode.PERMISSION_NO_ACCESS,ResultCode.PERMISSION_NO_ACCESS.message());
		}
	}
	

    
}