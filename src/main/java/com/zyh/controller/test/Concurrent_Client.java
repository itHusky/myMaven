package com.zyh.controller.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 客户端发送
 *
 * @author      1101399
 * @CreateDate  2018-2-23 下午4:36:39
 */
public class Concurrent_Client {
    public void test() {
        Executor excutor = Executors.newFixedThreadPool(10);
        while (true) {
            excutor.execute(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println("请输入字符串(结束符#):");
                }
            });
        }
    }
}
