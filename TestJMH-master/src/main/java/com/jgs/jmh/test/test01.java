package com.jgs.jmh.test;

/**
 * @Auther：jgs
 * @Data：2024/6/11 - 06 - 11 - 10:29
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test01 {

    public class MyRun implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"---"+i);
            }
        }
    }

    public static void main(String[] args) {
        MyRun mr = new test01().new MyRun();
        Thread td = new Thread(mr);
        td.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"--"+i);
            if(i==3){
                try {
                    td.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
