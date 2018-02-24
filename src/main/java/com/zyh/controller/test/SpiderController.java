package com.zyh.controller.test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java蜘蛛controller(爬虫技术)
 *
 * https://www.cnblogs.com/sanmubird/p/7857474.html
 * https://www.cnblogs.com/qianzf/p/6796588.html
 * http://python.jobbole.com/85653/
 *
 * @author      1101399
 * @CreateDate  2018-2-11 下午2:33:49
 */
public class SpiderController {

    public static void main(String[] args){
        URL url = null;
        URLConnection urlConn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//      String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        Pattern p = Pattern.compile(regex);
        try{
            url = new URL("https://www.baidu.com/");
            urlConn = url.openConnection();
            pw = new PrintWriter(new FileWriter("E:/URL.txt"),true);///将爬取到的链接放到E盘的URL文件中
            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String buf = null;
            while((buf = br.readLine()) != null){
                Matcher buf_m = p.matcher(buf);
                while(buf_m.find()){
                    pw.println(buf_m.group());
//                    System.out.println(buf_m.group());
                }
                System.out.println("爬取成功(*￣︶￣)");
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            pw.close();
            System.out.println("爬取完毕(•́へ•́╬)");
        }
    }

}
