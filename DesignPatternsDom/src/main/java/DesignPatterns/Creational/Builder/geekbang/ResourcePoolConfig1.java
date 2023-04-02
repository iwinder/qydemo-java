package DesignPatterns.Creational.Builder.geekbang;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 构造器赋值方式
 */
@Data
public class ResourcePoolConfig1 {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    // 最大总资源数量
    private int maxTotal = DEFAULT_MAX_TOTAL;
    // 最大空闲资源数量
    private int maxIdle = DEFAULT_MAX_IDLE;
    // 最小空闲资源数量
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourcePoolConfig1(String name, Integer  maxTotal, Integer  maxIdle, Integer  minIdle) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;
        if (maxTotal != null ) {
            if (maxTotal <=0 ) {
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
        }

        if (maxIdle != null ) {
            if (maxIdle <=0 ) {
                throw new IllegalArgumentException("maxIdle should be negative.");
            }
            this.maxIdle = maxIdle;
        }

        if (minIdle != null ) {
            if (minIdle <=0 ) {
                throw new IllegalArgumentException("maxIdle should be negative.");
            }
            this.minIdle = minIdle;
        }

    }
}
