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
 */
public class IdGenerator7 implements Serializable {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator7 instance;
  

    static RedissonClient redisson = getRedissonClient();
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
    
    
    private static RedissonClient getRedissonClient() {
        //
        Config config = null;
        try {
            config = Config.fromYAML(new File("classpath:redisson.yaml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Redisson.create(config);
    }
}
