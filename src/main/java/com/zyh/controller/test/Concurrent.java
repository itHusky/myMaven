package com.zyh.controller.test;

/**
 * 服务器并发学习 - 线程
 *
 * @author 1101399
 * @CreateDate 2018-2-23 上午8:55:06
 */
public class Concurrent extends Thread {

    private final String name;

    public Concurrent(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i > 100; i++) {
            System.out.println(name + "运行：" + i);
            try {
                sleep((int) Math.random() * 100);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

/*    public class Main {

        public void main(String[] args) {
            Concurrent mTh1 = new Concurrent("A");
            Concurrent mTh2 = new Concurrent("B");
            mTh1.start();
            mTh2.start();
        }
    }*/
}
