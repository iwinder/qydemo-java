package com.windcoder.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisLockService {
	@Autowired
	private RedisTemplate redisTemplate;
	public static final String UNLOCK_LUA =
			"if redis.call(\"get\",KEYS[1]) == ARGV[1] \n" +
			"then \n" +
			"  return redis.call(\"del\",KEYS[1]) \n"+
			"else \n" +
			"return 0 \n" +
			"end";


	public Boolean tryLock(String key,String value, long time, TimeUnit unit){
		// 2.1版本启用
		return  redisTemplate.opsForValue().setIfAbsent(key, value, time, unit);
	}

	public Boolean tryLockByNative(String key,String value, long time, TimeUnit unit){
		// 2.1版本启用
		return (Boolean) redisTemplate.execute( (RedisCallback<Boolean>) redisConnection -> {
			return redisConnection.set(key.getBytes(Charset.forName("UTF-8")),
					value.getBytes(Charset.forName("UTF-8")), Expiration.from(time,unit),
					RedisStringCommands.SetOption.SET_IF_ABSENT);
		});
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

	public Boolean unLockByNative(String key, String value){
		// 2.1版本启用
		return (Boolean) redisTemplate.execute( (RedisCallback<Boolean>) redisConnection -> {
			return redisConnection.eval(UNLOCK_LUA.getBytes(),
					ReturnType.BOOLEAN ,1,
					key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")));
		});
	}

	public Object getOne(String key){
		return redisTemplate.opsForValue().get(key);
	}
}
