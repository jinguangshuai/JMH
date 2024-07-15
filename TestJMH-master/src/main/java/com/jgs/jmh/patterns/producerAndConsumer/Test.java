package com.jgs.jmh.patterns.producerAndConsumer;

/**
 * @Auther：jgs
 * @Data：2024/7/9 - 07 - 09 - 20:14
 * @Description:com.jgs.jmh.patterns.producerAndConsumer
 * @version:1.0
 */
public class Test {

    public static void main(String[] args) {
        BufferResources bufferResources = new BufferResources();
        //十个生产者线程
        for (int i = 0; i < 10; i++) {
            new Producter(bufferResources).start();
        }
        //十个消费者线程
        for (int i = 0; i < 10; i++) {
            new Consumer(bufferResources).start();
        }
    }
}
