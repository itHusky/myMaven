package com.zyh.controller.file;

import java.io.IOException;
import java.io.InputStream;
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
@Controller
@RequestMapping("/downorup")
public class DownOrUploadController extends BaseController {

    // 控制台打印语句
    private final Logger log = LoggerFactory.getLogger(DownOrUploadController.class);

    private final String serverPath = "e:/";

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
    public String download(@RequestParam(name = "file", required = true) Integer fileid,
            ModelMap model, Integer... fil) {
        //
        // XXX 这个不应该设置为非必须参数-下载文件不传递ID值怎么下载？！
        // 方法一、required = true 必须参数 默认
        // 方法一、required = false 非必须参数
        // 方法二、传递参数 还可以直接使用 Integer fileid标示着不属于必须参数，在使用时小心当前参数为空的情况
        // 方法三、传递一个不定长参数 Integer ... fil
        // 方法三、①该不定长参数必须放在所有参数最后面；②该不定长参数传递到后台的是一个数组，使用时需要遍历数组
        // 方法三、Integer x = fil[0];不定长参数是java的一个独特写法
        // Python 中也有不定长参数
        /*
         Python 不定长参数一、      加了星号（*）的变量名会存放所有未命名的变量参数，不能存放字典dict，否则报错
         def multiple(arg, *args):
             print "arg: ", arg
             #打印不定长参数
             for value in args:
                 print "other args:", value
         if __name__ == '__main__':
             multiple(1,'a',True)
        Python 不定长参数一、          加了星号（**）的变量名会存放所有未命名的变量参数，可以存放字典dict
        def multiple2(**args):
            #打印不定长参数
             for key in args:
                 print key + ":" + bytes(args[key])
        if __name__ == '__main__':
             multiple2(name='Amy', age=12, single=True)
         */
        Integer x = fil[0];

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

        // log.debug("直接参数传递|"+file);
        // log.debug("直接参数传递|"+fileName);

        /**
         * // 方式废弃 // getInputStream() & getReader() 请求只能调用一次 if
         * (request.getReader() != null) { String str = null; BufferedReader is
         * = request.getReader(); while((str = is.readLine()) != null){ //
         * 空行不能显示出来 log.info("Reader:"+str); }
         *
         * do{ str = is.readLine(); // 每一行丢失首字母 log.info("数据输出:"+str);
         * }while(is.read() != -1); }
         */

        log.info("测试\n" + request.getContentType());
        log.info("测试\n" + request.getContentType().replaceAll(".+boundary=", "--"));

        double originalFilename = request.getContentLength();
        log.info("|" + originalFilename + "|");
        log.info(request.getMethod());
        if (request.getInputStream() != null) {
            InputStream is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;) {
                // Content-disposition是 MIME 协议的扩展 解析它
                System.out.println(new String(b, 0, n));
                sb.append(new String(b, 0, n));
            }
            log.info("测试|" + sb);
            String[] xx = sb.toString().split("Content-Type");
            log.info("测试分割|" + xx[1]);

            /**
             * 解析字符串得到上传数据并将之保存
             */
            /**
             * String[] vx2 = sb.toString().split(" "); int x2 = vx2.length;
             * log.info("|"+x2+"|"); int i = 0; while (i<x2) {
             * log.info("第"+i+"条数据："+vx2[i]); i++; if(i == 20){ break; } }
             * log.info("|第二次冲击|解析方式换成win操作系统的换行符之后 可以使用"); // 解析方式错误
             * 还是有问题：分割条件的换行符会和文件中的换行符发生BUG String[] xxTODO = new String[10];
             * String[] vx3 = sb.toString().split("\r\n\r\n"); int x3 =
             * vx3.length; int y = 0; log.info("记录条数|"+x3); for(i = 0
             * ;i<x3;i++){ log.info("第"+i+"条数据："+vx3[i]); String[] tmp =
             * vx3[i].split(request.getContentType().replaceAll(".+boundary=",
             * "--")); xxTODO[y] = tmp[0]; y++; } for(i = 0 ;i <
             * xxTODO.length;i++){ log.error("穿道受液|"+xxTODO[i]); }
             *
             * log.info("|第三次冲击|"); String[] vx4 =
             * sb.toString().split("Content-Disposition: form-data; name=\"");
             * int x4 = vx4.length; log.info("记录条数|"+x4); for(i = 0 ;i<x4;i++){
             * log.info("第"+i+"条数据："+vx4[i]); } String[] t0 =
             * vx4[x4-1].toString().split("\"; filename=\"");
             * log.info("[1]"+t0[0]); String[] t1 =
             * t0[1].toString().split("\""); log.info("[2]"+t1[0]); String[] t3
             * = t1[1].toString().split("Content-Type: ");
             * log.info("[3]"+t3[1]); // 解析方式错误 String[] t4 =
             * t3[1].toString().split("\r\n"); int xt4 = t4.length;
             * log.info("记录条数|"+xt4); for(i = 0 ;i<xt4;i++){
             * log.info("第"+i+"条数据："+t4[i]); }
             */

            /**
             * Packet for query is too large (44725236 > 4194304). You can
             * change this value on the server by setting the
             * max_allowed_packet' variable.; nested exception is
             * com.mysql.jdbc.PacketTooBigException: Packet for query is too
             * large (44725236 > 4194304). You can change this value on the
             * server by setting the max_allowed_packet' variable.
             *
             * max_allowed_packet 数据库默认值是 4 M 推荐通过修改数据库配置文件 max_allowed_packet =
             * 40M 的方式，后重启数据库方可生效 命令行查看 show variables like
             * 'max_allowed_packet'; 命令行修改 set global max_allowed_packet =
             * 40*1024*1024; 否则的话会出现查询新增修改的数据大于允许最大的数据包尺寸的问题。|如上报错
             * 我猜测这就是为什么文件存储不推荐使用数据库存储的原因了，你不确定文件上传的 大小，而设置太大的话又会影响到效率等一系列问题
             */
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
             * 中文编码上传异常
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

            log.error("日期：" + dateString);
            file.setFileName(dateString + "_" + name);
            file.setDisplayName(name.split("\\.")[0]);
            file.setExtension(name.split("\\.")[1]);
            file.setContentType(data.substring(0, 25));
            file.setFileData(data);
            file.setFileSize(Integer.valueOf(size));
            file.setCreateTime(new Date());
            txtFileService.insert(file);
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
        return new JsonResult<String>(true,"OK");
    }

}
