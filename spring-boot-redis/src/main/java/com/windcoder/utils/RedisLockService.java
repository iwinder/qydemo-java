package com.windcoder.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisLockService {
	@Autowired
	private RedisTemplate redisTemplate;

	public Boolean lock(String key,String value, long time, TimeUnit unit){
		// 2.1版本启用
		return  redisTemplate.opsForValue().setIfAbsent(key, value, time, unit);
	}
}
