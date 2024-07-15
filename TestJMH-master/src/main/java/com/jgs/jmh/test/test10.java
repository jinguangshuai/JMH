package com.jgs.jmh.test;

import redis.clients.jedis.Jedis;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * @Auther：jgs
 * @Data：2024/7/15 - 07 - 15 - 21:55
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test10 {

    private final Jedis jedis;
    private final String lockKey;
    private final long expireTime;
    private final long waitTime;
    private final long sleepTime;

    // 锁的唯一标识，用于释放锁时校验
    private String requestId;


    public test10(Jedis jedis, String lockKey, long expireTime, long waitTime, long sleepTime) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        this.waitTime = waitTime;
        this.sleepTime = sleepTime;
    }


    // 尝试获取锁
    public boolean lock() {
        long end = System.currentTimeMillis() + waitTime;
        while (System.currentTimeMillis() < end) {
            String result = jedis.set(lockKey, requestId, "NX", "PX", expireTime);
            if ("OK".equals(result)) {
                return true;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    // 释放锁
    public void unlock() {
        // 使用Lua脚本保证释放锁操作的原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
    }

    // 初始化requestId
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        test10 lock = new test10(jedis, "testLock", 10000, 10000, 100);
        lock.setRequestId(UUID.randomUUID().toString());

        if (lock.lock()) {
            try {
                // 执行你的业务逻辑
                System.out.println("Lock acquired. Executing critical section.");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println("Lock released.");
            }
        } else {
            System.out.println("Failed to acquire lock.");
        }

        jedis.close();
    }
}
