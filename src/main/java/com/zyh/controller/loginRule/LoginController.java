package com.zyh.controller.loginRule;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyh.domain.mainCode.PermissionsResource;
import com.zyh.domain.mainCode.RolePermission;
import com.zyh.domain.mainCode.Roles;
import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.exception.BusinessException;
import com.zyh.service.mainCode.IPermissionsResourceService;
import com.zyh.service.mainCode.IRolePermissionService;
import com.zyh.service.mainCode.IUserLoginService;
import com.zyh.service.mainCode.IUserService;
import com.zyh.util.SessionUtils;

/**
 * 登录相关规则管理
 *
 * @author 1101399
 * @CreateDate 2018-1-17 上午10:26:32
 */
@Controller
public class LoginController {

    // String type
    public final static String LOGIN = "登入";// 登入
    public final static String LOGOUT = "登出";// 登出

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserLoginService logLoginService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    private IPermissionsResourceService permissionsResourceService;

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login_get(String redirectUrl, ModelMap model) {
        logger.debug("GET方法");
        String message = "[打印一条消息] - [login界面get方法展示]";
        model.put("message", message);

        return "login";
    }

    /**
     * 注销退出
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session) {

        UserLogin userLogin = SessionUtils.getLoginUser();
        if (userLogin != null) {
            String ip = request.getRemoteAddr();
            writeLog(LOGOUT, userLogin, ip);
        }
        // session清除
        session.invalidate();

        return "redirect:login";
    }

    /**
     * Java常见HTTP请求方法RequestMethod
     *
     * POST
     * 由于上面GET的缺点，POST正好弥补了这些问题。POST方法把数据都存放在body里面，这样即突破了长度的限制；又保证用户无法直接看到。
     * 在使用表单时，比较常用
     *
     * HEAD HEAD请求只会返回首部的信息，不会返回相应体。通常用于测试数据是否存在、当做心跳检测等等。
     *
     * PUT 与GET相反，用于改变某些内容。
     *
     * DELETE 删除某些资源
     *
     * TRACE 可以理解成，我们为了看看一条请求在到达服务前数据发生了什么变化。可以使用这个命令，它会在最后一站返回原始信息，
     * 这样就可以观察到中间是否修改过请求。(经常会用于跨站攻击，所以有一定的安全隐患)
     *
     * OPTIONS 询问服务器支持的方法。
     *
     * PATCH 这个方法不太常见，是servlet
     * 3.0提供的方法，主要用于更新部分字段。与PUT方法相比，PUT提交的相当于全部数据的更新，类似于update
     * ；而PATCH则相当于更新部分字段，如果数据不存在则新建，有点类似于neworupdate。
     *
     * <strong>Url是一样的，但是由于请求方法不同，他们会根据请求方法使用相应的控制器方法处理请求</strong>
     */

    /**
     * 登录验证
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login_post(@RequestParam String userNo, @RequestParam String userPassword,
            String redirectUrl, HttpServletRequest request, ModelMap model) {
        logger.debug("POST方法");

        logger.debug("TEST:" + userNo + " TEST:" + userPassword);

        // 验证用户权限
        boolean userLoginOK = logLoginService.loginSystem(userNo, userPassword);
        if (userLoginOK) {

            // 登录成功处理
            loginSuccess(userNo, userPassword, request, model);

            // 写入登录日志
            String loginIP = request.getRemoteAddr();
            logger.debug(loginIP);

            /**
             * springmvc中的@RequestMapping注解中的return "redirect:/";的用法。
             * 如果没有配置视图解析器、映射器的前后缀， 调用该地址，调用该return "redirect:/"; 方法，则重定向到
             * 根目录，并根据web.xml中配置的默认首页加载默认页面。 return "redirect:" + url; 重定向到URL中去
             */
            return "redirect:/file/list";
        } else {
            String message = "[错误信息] - [用户编码或用户密码输入错误！]";
            model.put("message", message);
            return "login";
            // return "redirect:login";
        }
    }

    /**
     * 登录成功处理
     *
     * @param userNo
     *            用户编码
     * @param userPassword
     *            用户密码
     * @param request
     *            客户端返回
     * @param model
     *            构建用于UI工具的模型数据
     * @return 将用户相关的一些必要的但是不是重要(影响安全)的数据保存起来
     */
    private void loginSuccess(String userNo, String userPassword, HttpServletRequest request,
            ModelMap model) {
        // TODO Auto-generated method stub

        UserLogin userLogin = logLoginService.findByNameAndPass(userNo, userPassword);

        // session：清除不用的session数据
        request.getSession().invalidate();
        // session：登录信息存储
        request.getSession().setAttribute("userLogin", userLogin);


        User user = userService.findByUserLogin(userLogin);

        // 并存储用户角色的权限信息以便通过自定义标签进行显示处理限制
        Roles userRoles = new Roles();
        if(user != null){
            userRoles = user.getRoles();//用户角色信息
        }else{
            throw new BusinessException("用户角色信息不存在！");
        }

     /*   Integer idRole = user.getRoles().getRoleId();
        if (idRole == 1){
            List<PermissionsResource> permissAndResource = permissionsResourceService.findAll();

            // 添加用户拥有的URL资源
            List<String> URIList = new ArrayList<String>();
            for(int i = 0;i < permissAndResource.size(); i++){
                PermissionsResource func = permissAndResource.get(i);
                URIList.add(func.getResources().getResources());
            }
            request.getSession().setAttribute("URIList", URIList);
        }else{*/
            /**
             *
             * 由于跨域了多个表查询数据     必须      依次验证相应的数据是否为空
             *
             * 一个角色对应一个权限（子级权限 ）
             * 一个子级权限对应多个URL资源
             *
             */
            RolePermission roleAndPermiss = rolePermissionService.findByRolesId(userRoles.getRoleId());
            List<PermissionsResource> permissAndResource = permissionsResourceService.findByPermissionId(roleAndPermiss.getPermissions().getPermissionId());

            // 添加用户拥有的URL资源
            // 将用户的权限列表中的URL依次添加到指定存放URL权限列表的List中去
            List<String> URIList = new ArrayList<String>();// 权限List
            for(int i = 0;i < permissAndResource.size(); i++){
                PermissionsResource func = permissAndResource.get(i);
                URIList.add(func.getResources().getResources());
            }
            request.getSession().setAttribute("URIList", URIList);// 将权限List保存如果进行权限对比的话讲本地权限信息读出即可
    }

    /**
     * 输出日志
     *
     * @param type
     *            类型
     * @param loginUser
     *            登录用户
     * @param loginIP
     *            登录用户IP
     * @param message
     *            日志信息
     * @see<strong>将日志输出到本地文件中去</strong>
     * @serialData<strong>日志文件命名方式："x-world"+year+month+day</strong>
     */
    public void writeLog(final String type, User loginUser, String loginIP, String message) {
        if (type == LOGIN) {
            /*
             * RandomAccessFile randomFile = new RandomAccessFile(fileName,
             * "rw"); // 文件长度，字节数 long fileLength = randomFile.length(); //
             * 将写文件指针移到文件尾。 randomFile.seek(fileLength);
             * randomFile.writeBytes(content); randomFile.close();
             */
        } else {

        }
    }

    /**
     * 输出日志
     *
     * @param type
     *            类型
     * @param loginUser
     *            登录用户
     * @param loginIP
     *            登录用户IP
     * @see<strong>将日志输出到本地文件中去</strong>
     * @serialData<strong>日志文件命名方式："x-world"+year+month+day</strong>
     */
    public void writeLog(final String type, UserLogin userLogin, String loginIP) {
        if (type == LOGIN) {

        } else {

        }
    }
}
