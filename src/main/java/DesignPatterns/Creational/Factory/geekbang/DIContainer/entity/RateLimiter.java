package DesignPatterns.Creational.Factory.geekbang.DIContainer.entity;

import lombok.Data;

/**
 * 站位替换com.xzg.RateLimiter
 */
@Data
public class RateLimiter {
    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public void test() {
        System.out.println("Hello World!");
    }
}
