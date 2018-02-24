package com.zyh.controller.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Concurrent_Server {

    // 服务器IP
    public static final String SERVICE_IP = "192.168.12.244";

    // 服务器端口
    // public static final int port = 9080;
    public static final int SERVICE_PORT = 10086;

    // 请求结束字符串
    public static final char END_CHAR = '#';

    /**
     * 启动服务器
     */
    public void startServer(String serverIP, int serverPort) {

        // 创建服务器地址对象
        InetAddress serverAddr;
        try {
            serverAddr = InetAddress.getByName(serverIP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }

        // java 提供了ServerSocket 作为服务器
        // 这里使用了java的自动关闭的语法，很好用？？？
        //  java1.7特性，叫做try-with-resource，实现了AutoCloseable接口的实例可以放在
        //  try(...)中在离开try块时将自动调用close()方法。该方法调用可以看做在finally块中，
        //  所以资源的释放一定会执行，不过能不能成功释放还是得看close方法是否正常返回。
        try (ServerSocket serverSocket = new ServerSocket(SERVICE_PORT,5,serverAddr)){

            // 100个线程的线程池 如果放到里面的话就每次循环创建100个大小的线程池
            Executor excutor = Executors.newFixedThreadPool(100);
            while (true) {
                // StringBuilder recvStrBuilder = new StringBuilder();
                final StringBuilder recvStrBuilder = new StringBuilder();

                try{
                    // 有客户端向服务器发起TCP连接时，accpet会返回一个Socket
                    // 该Socket的对端就是客户端的Socket
                    // 具体过程请参考TCP三次握手的过程
                    // Socket connection = serverSocket.accept();
                    final Socket connection = serverSocket.accept();

                    // 利用线程池启动线程
                    /*********/
                    excutor.execute(new Runnable() {

                        @Override
                        public void run() {
                            // 局部引用防止connection被系统回收
                            Socket conn = connection;
                            try{
                                InputStream in = conn.getInputStream();

                                // 读取结束字符标志为读取循环结束标志
                                for(int c = in.read();c != END_CHAR; c = in.read()){
                                    recvStrBuilder.append((char)c);
                                }
                                recvStrBuilder.append('#');

                                String recvStr = recvStrBuilder.toString();

                                //向客户端写出处理后的字符串
                                OutputStream out = conn.getOutputStream();
                                out.write(recvStr.toUpperCase().getBytes());

                            }catch(IOException e){
                                e.printStackTrace();
                            }finally{
                                try{
                                    if(conn != null){
                                    conn.close();
                                    }
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
                    /*********/
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Concurrent_Server server = new Concurrent_Server();
        server.startServer(SERVICE_IP, SERVICE_PORT);
    }
}
