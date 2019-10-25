package com.windcoder.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisLockService {
	@Autowired
	private RedisTemplate redisTemplate;

	public Boolean tryLock(String key,String value, long time, TimeUnit unit){
		// 2.1版本启用
		return  redisTemplate.opsForValue().setIfAbsent(key, value, time, unit);
	}

	public void unLock(String key,String value){
		try {
			String currentValue = (String) redisTemplate.opsForValue().get(key);
			if(StringUtils.isNotBlank(currentValue) && currentValue.equals(value) ){
				redisTemplate.opsForValue().getOperations().delete(key);//删除key
			}
		} catch (Exception e) {
			log.error("解锁异常：{}", e);
		}

	}
}
