package DesignPatterns.Creational.Singleton.geekbang;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 集群唯一
 *
 * [设计模式之美](https://windcoder.com/go/JKDesignPattern)中仅提供了伪代码，这里根据伪代码尝试做了一个基于Redis的简单实现，代码本身没做测试，请勿直接粘贴复制到生产环境。
 *
 * Redisson 操作redis实现单例对象的序列化存储与反序列化读取。
 * Redisson 实现Redis分布式锁，用于取出前的加锁，与操作后的释放锁。
 *
 *  关于为何配置自定义序列化与反序列化的问题，可以参考[redisson如何序列化](https://www.php.cn/redis/436670.html)。
 */
public class IdGenerator7 implements Serializable {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator7 instance;
  

    static RedissonClient redisson = getRedissonClient();
    //  Redisson实现的Redis分布式锁锁
    static RLock  lock = redisson.getLock("anyLock");


    private IdGenerator7() {}

    public synchronized static IdGenerator7 getInstance()   {
        if (instance == null) {
            lock.lock();
            RBucket<IdGenerator7> bucket = redisson.getBucket("IdGenerator");
//            if (!bucket.isExists()) {

            //如果key存在，就设置key的值为新值value
            //如果key不存在，就设置key的值为value
            // https://www.pianshen.com/article/5465125503/
            bucket.set(new IdGenerator7());
//            }
            instance = bucket.get();
        }
        return instance;
    }
    public synchronized void freeInstance() {
        RBucket<IdGenerator7> bucket = redisson.getBucket("IdGenerator");
        bucket.set(new IdGenerator7());
        instance = null; //释放对象
        lock.unlock();

    }
    public long getId() {
        return id.incrementAndGet();
    }

    /**
     * 读取Redisson关于Redis的相关配置，生成Redisson对象。
     *
     * 这里仅为了方便实现与展示集群唯一的示例。
     * @return
     */
    private static RedissonClient getRedissonClient() {
        Config config = null;
        try {
            config = Config.fromYAML(new File("classpath:redisson.yaml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Redisson.create(config);
    }
}
