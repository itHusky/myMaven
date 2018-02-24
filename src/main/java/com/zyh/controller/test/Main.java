package com.zyh.controller.test;

public class Main {
    public static void main(String[] args) {
        Concurrent mTh1 = new Concurrent("A");
        Concurrent mTh2 = new Concurrent("B");
        mTh1.start();
        mTh2.start();
    }
}
