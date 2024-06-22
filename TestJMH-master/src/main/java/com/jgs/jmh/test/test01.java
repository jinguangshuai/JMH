package com.jgs.jmh.test;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/7 - 06 - 07 - 14:28
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test01 {
    public static class Test01 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"-----"+i);
                try {
                    Thread.sleep(1001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //这是一个main方法，是程序的入口
    public static void main(String[] args) {
        Test01 t1 = new Test01();
        Thread td = new Thread(t1);
        td.start();
        for (int i = 10; i > 0; i--) {
            System.out.println(Thread.currentThread().getName()+"--"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
