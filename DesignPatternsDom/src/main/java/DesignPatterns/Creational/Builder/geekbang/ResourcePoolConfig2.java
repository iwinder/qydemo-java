package DesignPatterns.Creational.Builder.geekbang;

import org.apache.commons.lang3.StringUtils;

/**
 * set()函数赋值
 */
public class ResourcePoolConfig2 {
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

    public ResourcePoolConfig2(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;
    }

    public void setMaxTotal(int maxTotal) {
        if (maxTotal <=0 ) {
            throw new IllegalArgumentException("maxTotal should be positive.");
        }
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        if (maxIdle <=0 ) {
            throw new IllegalArgumentException("maxIdle should be negative.");
        }
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        if (minIdle <=0 ) {
            throw new IllegalArgumentException("maxIdle should be negative.");
        }
        this.minIdle = minIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }



    public int getMaxIdle() {
        return maxIdle;
    }



    public int getMinIdle() {
        return minIdle;
    }


}
