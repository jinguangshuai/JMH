package com.jgs.jmh.patterns.producerAndConsumer;

import java.util.LinkedList;

/**
 * @Auther：jgs
 * @Data：2024/7/9 - 07 - 09 - 20:12
 * @Description:com.jgs.jmh.patterns.producerAndConsumer
 * @version:1.0
 */
public class BufferResources {
    private int maxSize = 10;
    //这里用list作为缓冲区，也可以替换为队列
    private LinkedList list = new LinkedList<Integer>();

    public synchronized void consume() {
        while (list.size() == 0) {
            System.out.println(Thread.currentThread().getName() + " 当前缓冲区为空，等待生产中...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费数据，从First开始消费，模拟队列
        Integer value = (Integer) list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " 消费成功：" + value.toString() + " 当前缓冲区size = " + list.size());
        //唤醒所有处于wait状态的线程（包括生产者和消费者）
        notifyAll();
    }

    public synchronized void product(Integer value){
        while (list.size() == maxSize) {
            System.out.println(Thread.currentThread().getName() + " 当前缓冲区满了，等待消费中...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //生产数据
        list.add(value);
        System.out.println(Thread.currentThread().getName() + " 生产成功：" + value.toString() + " 当前缓冲区size = " + list.size());
        //唤醒所有处于wait状态的线程（包括生产者和消费者）
        notifyAll();
    }
}
