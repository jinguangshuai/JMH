package com.jgs.jmh.patterns.producerAndConsumer;

/**
 * @Auther：jgs
 * @Data：2024/7/9 - 07 - 09 - 20:13
 * @Description:com.jgs.jmh.patterns.producerAndConsumer
 * @version:1.0
 */
public class Consumer extends Thread {
    private BufferResources bufferResources;

    //构造时需要指定缓冲区
    public Consumer(BufferResources bufferResources) {
        this.bufferResources = bufferResources;
    }

    @Override
    public void run() {
        this.bufferResources.consume();
    }
}