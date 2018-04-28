package com.zyh.controller.test;


/**
 * java蜘蛛controller(爬虫技术)
 *
 * 该程序功能：将填写部分的URL所指向的界面的可用URL地址全部扒下来存储到E盘URL.txt文件中
 *
 * https://www.cnblogs.com/sanmubird/p/7857474.html
 * https://www.cnblogs.com/qianzf/p/6796588.html
 * http://python.jobbole.com/85653/
 *
 * @author      1101399
 * @CreateDate  2018-2-11 下午2:33:49
 */
/*public class SpiderController {

    public static void main(String[] args){
        URL url = null;
        URLConnection urlConn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//      String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        //http://xueshu.baidu.com/
        //http://www.mzitu.com/
        //http://i.meizitu.net/thumbs/2018/04/129067_09b57_236.jpg
        String regex = "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";//url匹配规则
        Pattern p = Pattern.compile(regex);
        try{
            url = new URL("http://www.mzitu.com/");
            urlConn = url.openConnection();
            pw = new PrintWriter(new FileWriter("E:/URL.txt"),true);///将爬取到的链接放到E盘的URL文件中
            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String buf = null;
            while((buf = br.readLine()) != null){
                Matcher buf_m = p.matcher(buf);
                while(buf_m.find()){
                    // 扒取到的数据存储输出到文件流中
                    pw.println(buf_m.group());
//                    System.out.println(buf_m.group());
                }
                System.out.println("爬取成功(*￣︶￣)(¯﹃¯)");
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
*/