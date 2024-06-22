package com.jgs.jmh.test;

import java.util.concurrent.TimeUnit;

/**
 * @Auther：jgs
 * @Data：2024/6/11 - 06 - 11 - 11:19
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class T01_HelloVolatile {
    volatile boolean running = true; //对比一下有无volatile的情况下，整个程序运行结果的区别
    void m() {
        System.out.println("m start");
        while(running) {
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();

        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
