package com.zyh.controller.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 客户端发送
 *
 * @author 1101399
 * @CreateDate 2018-2-23 下午4:36:39
 */
public class Concurrent_Client {

    private static final int PORT = 10086;
    private static final String HOST = "192.168.12.244";

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

    public static void main(String[] args) {
        Socket s = null;
        OutputStream out = null;
        InputStream in = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        byte[] b = new byte[1024];
        // try(){ 当前jar版本不支持
        try {
            s = new Socket(HOST, PORT);
            out = s.getOutputStream();
            dos = new DataOutputStream(out);
            dos.write("MMP".getBytes());
            in = s.getInputStream();
            dis = new DataInputStream(in);
            dis.read(b);
            String serverToClient = new String(b);
            System.out.println("BACK:" + serverToClient);
        } catch (UnknownHostException e) {
            System.out.println("Host 未知...");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                dos.close();
                dis.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
