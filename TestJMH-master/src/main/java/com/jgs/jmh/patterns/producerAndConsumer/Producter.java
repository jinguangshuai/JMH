package com.jgs.jmh.patterns.producerAndConsumer;

import java.util.Random;

/**
 * @Auther：jgs
 * @Data：2024/7/9 - 07 - 09 - 20:13
 * @Description:com.jgs.jmh.patterns.producerAndConsumer
 * @version:1.0
 */
public class Producter extends Thread {
    private BufferResources bufferResources;
    Random random = new Random();

    //构造时需要指定缓冲区
    public Producter(BufferResources bufferResources) {
        this.bufferResources = bufferResources;
    }

    @Override
    public void run() {
        //生产
        this.bufferResources.product(random.nextInt());
    }
}
