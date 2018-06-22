package com.zyh.controller.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.controller.base.BaseController;
import com.zyh.domain.file.TXTFile;
import com.zyh.service.file.ITXTFileService;
import com.zyh.service.mainCode.IUserService;
import com.zyh.vo.base.JsonResult;

/**
 * 文件的上传、下载与查询
 *
 * 前台 企图 使用baidu WebFE团队开发的WebUploader HTML插件 想自己写相关上传控件
 *
 * 后台 文件上传与下载的相关复杂处理 数据库 保存后台传递的数据和为后台提供数据 使用MySQL数据库
 *
 * @author 1101399
 * @CreateDate 2018-5-2 上午9:34:01
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/downorup")
public class DownOrUploadController extends BaseController {

    // 控制台打印语句
    private final Logger log = LoggerFactory.getLogger(DownOrUploadController.class);

    @Resource
    private ITXTFileService txtFileService;
    @Resource
    private IUserService use;

    /**
     * 文件列表
     */
    @RequestMapping("/list")
    public String list(ModelMap model) {
        /**
         *
         * 查询出数据库数据表的信息 并为其他如上传之类的操作提供按钮加载界面
         *
         **/

        /**
         * 添加findAll 除了数据部分
         */
        log.debug("new");
        TXTFile file = txtFileService.findByName();
        List<TXTFile> fileList = txtFileService.findAll();
        model.put("name", file.getExtension());
        model.put("list", fileList);
        return "downorup/list";
    }

    /**
     * 文件列表的展示
     */
    @RequestMapping("/show")
    public String show(@RequestParam("id") Integer id, ModelMap model) {
        if (id == null) {
            return "downorup/list";
        }
        TXTFile file = txtFileService.findDataById(id);
        model.put("file", file);
        return "downorup/show";
    }

    /**
     * 下载文件
     */
    /*
     * value 绑定的URL地址 method 设置URL响应的传递方式 headers 设置URL响应的相应内容类型
     */
    // @RequestMapping("/download")
    @RequestMapping(value = "/download", method = RequestMethod.POST, headers = "content-type = text / *")
    public String download(@RequestParam(name = "id", required = true) Integer fileid,
            ModelMap model, Integer... fil) {
        System.out.println("tes11t");
        //
        // 这个不应该设置为非必须参数-下载文件不传递ID值怎么下载？！
        // 方法一、required = true 必须参数 默认
        // 方法一、required = false 非必须参数
        // 方法二、传递参数 还可以直接使用 Integer fileid标示着不属于必须参数，在使用时小心当前参数为空的情况
        // 方法三、传递一个不定长参数 Integer ... fil
        // 方法三、①该不定长参数必须放在所有参数最后面；②该不定长参数传递到后台的是一个数组，使用时需要遍历数组
        // 方法三、Integer x = fil[0];不定长参数是java的一个独特写法
        // Python 中也有不定长参数
        /*
         * Python 不定长参数一、 加了星号（*）的变量名会存放所有未命名的变量参数，不能存放字典dict，否则报错 def
         * multiple(arg, *args): print "arg: ", arg #打印不定长参数 for value in args:
         * print "other args:", value if __name__ == '__main__':
         * multiple(1,'a',True) Python 不定长参数一、
         * 加了星号（**）的变量名会存放所有未命名的变量参数，可以存放字典dict def multiple2(**args): #打印不定长参数
         * for key in args: print key + ":" + bytes(args[key]) if __name__ ==
         * '__main__': multiple2(name='Amy', age=12, single=True)
         */
        /**
         * 下载文件大小显示 下载文件路径选择 下载文件进度显示 下载文件界面遮罩
         */
        // 1、判断传递过来的id不允许为空
        // 2、根据传递id查询调用数据库查询获得文件详细信息
        // 3、选定位置新建文件写入内容|完成下载
        if (fileid == null) {
            return "";
        }
        TXTFile file = txtFileService.findDataById(fileid);
        // 1、首先判断记录中有数据
        // 2、判断文件后缀名存在
        // 3、判断文件显示名称存在
        if (file.getFileData() == null) {
            return "";
        }
        if (file.getExtension() == null) {
            return "";
        }
        if (file.getDisplayName() == null) {
            // 将创建的时间取年月日
            // 文件名称：年月日+流水ID
        }
        // 考虑是将数据按照特定协议传递到前台还是采用其他方法 或者采用其他办法3

        return "downorup/list";
    }

    /**
     * 下载 1、首选webuploader插件完成下载步骤 2、同时不放弃其他下载文件方式
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<String> download(@RequestParam(name = "id") Integer fileid) {
        System.out.println("test");// GET|POST 方法 测试通过，使用GET方法传递显示
        TXTFile file = txtFileService.findDataById(fileid);
        if (file != null) {
            return new JsonResult<String>(true, file.getFileData());
        } else {
            return new JsonResult<String>(false, "文件为空！");
        }
    }

    // @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<TXTFile> download_post(@RequestParam(name = "id") Integer fileid) {
        System.out.println("test_post");// GET|POST 方法 测试通过，使用GET方法传递显示
        TXTFile file = txtFileService.findDataById(fileid);
        if (file != null) {
            // 传递文件类到前台
            return new JsonResult<TXTFile>(true, "下载成功", file);
        } else {
            return new JsonResult<TXTFile>(false, "文件为空！");
        }
    }

    /* @RequestMapping(value = "/download", method = RequestMethod.POST) */
    @RequestMapping(value = "/downloads", method = RequestMethod.GET)
    public void test(@RequestParam(name = "id") Integer fileid, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        TXTFile file = txtFileService.findDataById(fileid);
        String test = file.getFileData();

        InputStream in = new ByteArrayInputStream(test.getBytes());// String
                                                                   // 写入输入流
        String fileName = file.getFileName()/* + "." + file.getExtension() */;

        response.setContentLength(new Long(file.getFileSize()).intValue());
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String agent = request.getHeader("USER-AGENT");// 浏览器类型 版本号等
        if (null != agent && -1 != agent.indexOf("Mozilla")) {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[4096];
        int length;
        while ((length = in.read(b)) > 0) {
            os.write(b, 0, length);
        }
        os.close();
        in.close();
    }

    /**
     * 上传文件
     *
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResult<String> upload(
    // @RequestParam(name = "file", required = false) CommonsMultipartFile file,
    // @RequestParam InputStream file,
    // @RequestParam("file") MultipartFile fileName,
    // @RequestParam(value="id") String id,
    // @RequestParam(value="name") String fileName,
    // @RequestParam(value="type") String filetype,
    // @RequestParam(value="lastModifiedDate") Date date,
    // @RequestParam(value="size") int size,
    // @RequestParam(value="file") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        /**
         * 上传文件大小限制 上传文件格式记录 上传文件大小记录 上传文件压缩考虑 上传文件界面遮罩
         *
         * 不同操作系统有不同的换行符 /r Mac /n Unix/Linux /r/n Windows
         *
         */
        TXTFile file = new TXTFile();
        String data = null;
        String name = null;
        String size = null;
        TestThread testThread = new TestThread();
        TestTwoThread testTwoThread = new TestTwoThread();

        log.info("测试\n" + request.getContentType());
        double originalFilename = request.getContentLength();
        log.info("|" + originalFilename + "|");
        log.info(request.getMethod());
        // 数据传递方式
        if (request.getInputStream() != null) {
            InputStream is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;) {
                // Content-disposition是 MIME 协议的扩展 解析它
                /**
                 * XXX 协议输出
                 */
                // 开辟线程用新开辟的线程进行打印输出
                testThread.setB(b);
                testThread.setN(n);
                Thread thread = new Thread(testThread);
                thread.start();
                System.out.println("线程id:" + thread.getId());
                // System.out.println(new String(b, 0, n));
                sb.append(new String(b, 0, n));
            }
            // 最接近成功的解析方法
            // 只要逐条解析就OK了
            // 可以探究更加简单直接的方式
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String boundary = request.getContentType().replaceAll(".+boundary=", "--");
            String[] vx6 = sb.toString().split(boundary);
            int x6 = vx6.length;
            log.info("记录条数|" + x6);
            for (int i = 0; i < x6; i++) {
                // log.error("换    行|"+vx6[i].replaceAll("\n", "+"));
                // log.error("回车键|"+vx6[i].replaceAll("\r", "-"));
                // log.error("空格键|"+vx6[i].replaceAll(" ", "*"));
                log.info("第" + i + "条数据：" + vx6[i]);
                if (i == 0) {

                }
                if (i == 1) {

                }
                if (i == 2) {
                    String tempstart = "Content-Disposition: form-data; name=\"name\"\r\n\r\n";
                    String tempend = "\r\n";
                    String[] temone = vx6[i].split(tempstart);
                    String[] temtwo = temone[1].split(tempend);
                    log.error("改造|" + temone[1]);
                    log.error("改造|" + temtwo[0]);
                    name = temtwo[0];
                }
                if (i == 3) {

                }
                if (i == 4) {

                }
                if (i == 5) {
                    String tempstart = "Content-Disposition: form-data; name=\"size\"\r\n\r\n";
                    String tempend = "\r\n";
                    String[] temone = vx6[i].split(tempstart);
                    String[] temtwo = temone[1].split(tempend);
                    log.error("改造|" + temone[1]);
                    log.error("改造|" + temtwo[0]);
                    size = temtwo[0];
                }
                if (i == 6) {
                    // TODO 新增 数据获取部分
                }
                if (i == 7) {

                }
            }
            String test = vx6[x6 - 2];
            String[] tt = test.split("Content-Type: text/plain\r\n\r\n");
            int tt6 = tt.length;
            log.info("记录条数|" + tt6);
            for (int i = 0; i < tt6; i++) {
                // log.error("换    行|"+tt[i].replaceAll("\n", "+"));
                // log.error("回车键|"+tt[i].replaceAll("\r", "-"));
                // log.error("空格键|"+tt[i].replaceAll(" ", "*"));
                log.error("第" + i + "条数据：" + tt[i]);
                if (i == 1) {
                    data = tt[i];
                }
            }
            /**
             * 中文编码上传异常 TODO
             */
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            // TXTFile file = new TXTFile();
            String[] wq = name.split("\\.");
            log.debug("MMP|" + name);
            log.debug("MMP|" + wq.length);
            for (int i = 0; i < wq.length; i++) {
                log.debug("MMP|" + wq[i]);
            }

            // 只处理属于TXT文件的相关上传
            if (!name.split("\\.")[1].equals("txt") || !name.split("\\.")[1].equals("TXT")) {
                // return new JsonResult<String>();
            }

            // 日期字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateString = sdf.format(new Date());

            file.setFileName(dateString + "_" + name);
            file.setDisplayName(name.split("\\.")[0]);
            file.setExtension(name.split("\\.")[1]);

            /**
             * 如果长度大于25个字符则截取前25个字符，否则将所有信息输出
             */
            String curType;
            try {
                curType = data.substring(0, 25);
            } catch (Exception e) {
                curType = data;
            }
            file.setContentType(curType);

            // file.setFileData(new String(data.getBytes("UTF-8"),"UTF-8"));
            // file.setFileData(new String(data.getBytes(),"GBK"));
            file.setFileData(data);
            file.setFileSize(Integer.valueOf(size));
            file.setCreateTime(new Date());

            testTwoThread.setFile(file);
            new Thread(testTwoThread).start();
            // TODO

            /* txtFileService.insert(file); */
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }

        /*
         * response.getWriter().append("Served at: ").append(request.getContextPath
         * ()); System.out.println("进入后台..."); //
         * 1.创建DiskFileItemFactory对象，配置缓存用 DiskFileItemFactory
         * diskFileItemFactory = new DiskFileItemFactory(); // 2. 创建
         * ServletFileUpload对象 ServletFileUpload servletFileUpload = new
         * ServletFileUpload(diskFileItemFactory); // 3. 设置文件名称编码
         * servletFileUpload.setHeaderEncoding("utf-8"); // 4. 开始解析文件 try {
         * List<FileItem> items = servletFileUpload.parseRequest(request); if
         * (items.isEmpty()) { log.info("items 为 空"); } for (FileItem fileItem :
         * items) {
         *
         * log.info("info|"+fileItem.isFormField()); if (fileItem.isFormField())
         * { // >> 普通数据 String info = fileItem.getString("utf-8");
         * System.out.println("info:" + info); } else { // >> 文件 // 1. 获取文件名称
         * String name = fileItem.getName(); // 2. 获取文件的实际内容 InputStream is =
         * fileItem.getInputStream(); // 3. 保存文件
         * FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" +
         * name)); } } } catch (Exception e) { e.printStackTrace(); }
         *
         * log.info("YYYYYYYYYYYYYYYYYY"); String x = request.getContentType();
         * String i = request.getContextPath(); ServletInputStream z =
         * request.getInputStream(); String z1 = request.getParameter("size");
         * String s = response.getCharacterEncoding(); Collection<String> t =
         * response.getHeaderNames(); log.info("testx|"+x);
         * log.info("testi|"+i); log.info("testz|"+z); log.info("test@|"+z1);
         * log.info("tests|"+s); log.info("testt|"+t);
         * log.info("testt|"+response.getWriter());
         */

        // 成功抵达
        return new JsonResult<String>(true, "OK");
    }

    /**
     * 类中类 内部类
     *
     * 将控制台打印语句的操作放置到线程中去
     *
     * 使用线程前数据上传响应时间：27600ms 使用线程后数据上传响应时间：30360ms
     *
     * @author 1101399
     * @CreateDate 2018-6-7 下午3:48:52
     */
    private class TestThread implements Runnable {

        private int n;
        private byte[] b;

        public void setN(int n) {
            this.n = n;
        }

        public void setB(byte[] b) {
            this.b = b;
        }

        // public synchronized void run(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println(new String(b, 0, n));
            System.out.println();
        }

    }

    /**
     * 将耗时的数据库插入操作放入线程中去大大的加快界面的响应速度 开启线程操作本身也会具有一定的时间响应消耗：所以需要进行响应的权衡
     * 这么理解略显偏颇(理解有问题)<br>
     *
     * 开启线程可以简单的；与git分支结构等相类比 开启线程之后原来的线程继续走下去，新的线程执行指定耗时操作
     * 就是可能存在这种情况：原线程执行完毕，新线程仍在继续执行。 在这个过程中新线程出现异常情况，但是原线程已经执行完毕。
     * 这种情况推荐使用线程之间的通信&等待&睡眠...<br>
     *
     * 使用线程前数据上传响应时间：27600ms<br>
     * 使用线程后数据上传响应时间：18230ms<br>
     *
     * @author 1101399
     * @CreateDate 2018-6-7 下午4:14:01
     */
    private class TestTwoThread implements Runnable {

        private TXTFile file;

        public void setFile(TXTFile file) {
            this.file = file;
        }

        @Override
        public void run(){
            // TODO 没有办法监听这个线程是否执行无误    - 可能执行失败也可能执行成功，如果要进行监听的话可以引入线程间的通信来处理当前步骤
            try{
                txtFileService.insert(file);
            }catch(Exception e){
                // 异常情况处理机制
                e.printStackTrace();
            }
        }
    }

    private class TestCommRunOne implements Runnable{
        // synchronized 线程间通信用关键字 可以修饰字段数据也可以修饰方法
        private final TestComm comm;
        public TestCommRunOne(TestComm comm){
            this.comm = comm;
        }
        @Override
        public void run() {
//            System.out.println("TestCommRunOne");
            synchronized(this){
                for(int i = 0;i < 5; i++){
                    comm.methodA("TestCommRunOne",i);
                }
            }
        }
    }
    private class TestCommRunTwo implements Runnable{
        private final TestComm comm;
        public TestCommRunTwo(TestComm comm){
            this.comm = comm;
        }
        @Override
        public void run(){
//            System.out.println("TestCommRunTwo");
            synchronized(this){
                for(int i = 0;i < 5; i++){
                    comm.methodB("TestCommRunTwo",i);
                }
            }
//            TestComm.xx xx = null;
//            xx.xxx();
        }
    }

    private class TestComm{
        // 共享内存   式的通信。多个线程需要访问同一个共享变量，谁拿到了锁（获得了访问权限），谁就可以执行
        // 当一个访问时，就会自动加锁
        synchronized public void methodA(String name,int i){
            System.out.println("输出共享式线程通信！方法：methodA 线程名称："+name+" 编号："+i);
        }
        synchronized public void methodB(String name,int i){
            System.out.println("输出共享式线程通信！方法：methodB 线程名称："+name+" 编号："+i);
        }
        // synchronized (this){//修饰代码块}
        // 也可以作加锁的操作，当前操作没有操作完毕就会阻塞其他的操作
        // 谁抢到锁操作就会有权限执行当前锁的所控制的代码
        class xx{
            public void xxx(){
                synchronized(xx.class){
                    // synchronized修饰一个类 作用暂不明确
                    // 修饰一个类、修饰一个方法、修饰一段代码、修饰静态方法
                }
            }
        }
    }

    /**
        synchronized的四种用法
        https://blog.csdn.net/sinat_32588261/article/details/72880159
     */

    /**
     * 线程并发相关测试软件
     */
    @RequestMapping("/testThreads")
    public void testThreads(){
        try{
            TestComm comm = new TestComm();
            // 提示报错没有参数
            TestCommRunOne t1 = new TestCommRunOne(comm);
            TestCommRunTwo t2 = new TestCommRunTwo(comm);
            Thread th1 = new Thread(t1);
            Thread th2 = new Thread(t2);
            th1.start();

            // 等待一个进程结束：用以等待前一个进程结束后方才开始进行下一步操作，同步确保下一步骤之前的操作是已经结束了的。
            th1.join();
            // 等待唤醒线程
            // th1.wait(); 等待
            // th1.notify(); 唤醒

            th2.start();
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}
