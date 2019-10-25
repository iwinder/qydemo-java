package com.windcoder.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisLockService {
	@Autowired
	private RedisTemplate redisTemplate;

	public Boolean tryLock(String key,String value, long time, TimeUnit unit){
		// 2.1版本启用
		return  redisTemplate.opsForValue().setIfAbsent(key, value, time, unit);
	}

	public void unLock(String key,String value){
		String currentValue = (String) redisTemplate.opsForValue().get(key);
		if(StringUtils.isNotBlank(currentValue) && currentValue.equals(value) ){
			redisTemplate.opsForValue().getOperations().delete(key);//删除key
		}
	}
}
