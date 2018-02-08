package com.zyh.system;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.domain.mainCode.PermissionsResource;
import com.zyh.domain.mainCode.RolePermission;
import com.zyh.domain.mainCode.Roles;
import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.mainCode.IPermissionsResourceService;
import com.zyh.service.mainCode.IRolePermissionService;
import com.zyh.service.mainCode.IUserService;
import com.zyh.util.HtmlUtils;
import com.zyh.util.JsonUtils;
import com.zyh.util.SessionUtils;
import com.zyh.vo.base.JsonResult;

/**
 * Spring MVC 拦截器 首先要在Spring MVC的配置文件xml中 添加配置拦截器
 *
 * <mvc:interceptors> <!-- 日志拦截器 --> <mvc:interceptor> <mvc:mapping path="/**"
 * /> <mvc:exclude-mapping path="/static/**" />
 *
 * 有两种方式一种是HandlerInterceptor接口 还有一种是WebRequestInterceptor接口
 * 但是我们今天使用的是HandlerInterceptor接口
 *
 * 还有一种是HandlerInterceptorAdapter ？？？ 区别是什么 ？？？ 尚未可知
 * 这种通过抽象类继承的方式使用的方式使用范例在com.hfepc.interceptor.LoginInterceptor 和
 * com.hfepc.interceptor.PermissionInterceptor
 *
 * 区别是： 1、HandlerInterceptor 是接口 通过实现接口完成相应的功能 import
 * org.springframework.web.servlet.HandlerInterceptor;
 *
 * 2、HandlerInterceptorAdapter 是抽象类 通过实现子类完成相应的功能 import
 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 *
 * 关注一个事物的本质的时候，用抽象类； 关注一个操作的时候，用接口
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 上午9:45:25
 */
public class AllInterceptor implements HandlerInterceptor {

    static Logger log = LoggerFactory.getLogger(AllInterceptor.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    private IPermissionsResourceService permissionsResourceService;

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
     * SpringMVC中的Interceptor拦截器是链式的，可以同时存在多个Interceptor，
     * 然后SpringMVC会根据声明的前后顺序一个接一个的执行，
     * 而且所有的Interceptor中的preHandle方法都会在Controller方法调用之前调用。
     * SpringMVC的这种Interceptor链式结构也是可以进行中断的，
     * 这种中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        log.info("拦截器拦截成功");
        boolean success = false;

        // LLL:http://192.168.12.244:9080/myMaven/resources/widget/bootstrap-3.3.7-dist/js/bootstrap.js
        // XXX
        // 原因是因为resources资源信息类过滤的原因
        // 解决方案：添加<mvc:exclude-mapping path="/resources/**" /><!-- 跳过验证此URL -->
        // resources资源也会是一种较为特殊的请求(特殊在于内容而非方式)

        String uri = request.getRequestURI(); // 输出：/kanban/login
        String contextPath = request.getContextPath(); // 输出：/kanban
        uri = uri.substring(contextPath.length() + 1); // 输出：login

        /*
         * // 例如：/kanban/login
         * String requestURL = request.getRequestURI(); //输出：/kanban/login
         * String contextPath = request.getContextPath(); //输出：/kanban
         * requestURL = requestURL.substring(contextPath.length() + 1); // 输出：login
         */
        /**
         * 判断用户登录信息是否存在 - 否的话返回登录界面 - 是的话继续进行下一步操作
         */
        UserLogin userLogin = SessionUtils.getLoginUser();
        if (null == userLogin) {
            log.debug("未登录&登录超时！");
            // 界面返回login界面
            String message = "未登录&登录超时！";
            // 判断一下是否有URL传入有点话设法使登录之后直接调转道相应的URL位置中去
            PrintWriter out = response.getWriter();
            if (HtmlUtils.isAjaxRequest(request)) {
                response.setContentType("application/json;charset=UTF-8");
                out.print(JsonUtils.toString(new JsonResult<String>(false, "登录超时（或未登录），请重新登录！")));
            } else {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                        + request.getServerPort() + path + "/";
                log.error("test:" + uri);
                String urlx = basePath + "unlogin.jsp?redirectUrl="
                        + URLEncoder.encode(uri, "UTF-8");
                out.println("<script type=\"text/javascript\">");
                // 这块火狐浏览器的兼容性有问题 - 错了不单单是兼容问题，推测代码有BUG 谷歌也是这样 <!-- 请求过滤问题 -->
                out.println("window.open('" + urlx + "','_top')");
                out.println("</script>");
            }
            out.flush();

            // 据说添加close之后会报错，但是我不信(添加了暂时没出错)
            // out.close();
            return false;
        }

        // 判断过滤部分 - 通过URL判断实现对非法访问的拦截
        // 权限过滤

        User user = userService.findByUserLogin(userLogin);
        Roles temRoles = user.getRoles();// 用户角色信息
        RolePermission temRoleper = rolePermissionService.findByRolesId(temRoles.getRoleId());
        List<PermissionsResource> temPerResourceList = permissionsResourceService
                .findByPermissionId(temRoleper.getPermissions().getPermissionId());

        // 超级权限直接跳过验证 - 设想
        /*
         * if(角色 == 超级管理员){ return true; }
         */
        Integer idRole = user.getRoles().getRoleId();
        if (idRole == 1) {
            return true;
        }

        // 添加用户拥有的URL资源
        List<String> URIList = new ArrayList<String>();
        for (int i = 0; i < temPerResourceList.size(); i++) {
            PermissionsResource func = temPerResourceList.get(i);
            URIList.add(func.getResources().getResources());
        }

        // HttpSession session = pageContext.getSession();
        // List<String> URIList = (List<String>)
        // session.getAttribute(Constants.SESSION_URI_LIST);

        if (URIList == null || URIList.isEmpty()) {
            success = false;
        } else {
            for (int i = 0; i < URIList.size(); i++) {
                String regexURL = URIList.get(i);
                /*
                 * if (regexURL.equals(uri)) { success = true; break; }
                 */
                /*
                 * // 使用正则表达式进行匹配（数据库中已设置的url本身就是个正则表达式） Pattern pattern =
                 * Pattern.compile(regexURL); Matcher matcher =
                 * pattern.matcher(uri); if (matcher.matches()) { success =
                 * true; break; }
                 */
                if (uri.startsWith(regexURL)) {// 保留此匹配方法是为了兼容以前的URI资源
                    success = true;
                    break;
                } else {
                    // 正则匹配的方式方便系统管理员权限进行匹配
                    // 使用正则表达式进行匹配（数据库中已设置的url本身就是个正则表达式）
                    Pattern pattern = Pattern.compile(regexURL);
                    Matcher matcher = pattern.matcher(uri);
                    if (matcher.matches()) {
                        success = true;
                        break;
                    }
                }
            }
        }

        if (success)
            return true;
        else if (HtmlUtils.isAjaxRequest(request)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            JsonResult result = new JsonResult();
            result.setStatus(false);
            result.setMessage("对不起，您没有访问操作的权限！\n请求地址：" + uri);
            String json = JsonUtils.toString(result);
            out.print(json);
        } else {
            // 若是普通请求，则跳转至权限不足提示的也没
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + path + "/";
            /**
             * 使用指定的重定向位置URL向客户端发送临时重定向响应，并清除缓冲区。缓冲区将被这个方法设置的数据替换。
             * 调用此方法将状态码设置为SC_FOUND 302（找到）。这个方法可以接受相对的URL;
             * servlet容器在把响应发送给客户端之前
             * ，必须将相对URL转换为绝对URL。如果该位置是相对的而没有前导'/'，则容器将其解释为相对于当前请求URI
             * 。如果该位置与前导“/”相对，则容器将其解释为相对于servlet容器根。
             *
             * 如果响应已被提交，则此方法将引发IllegalStateException。在使用这个方法之后，应该认为响应是被提交的，
             * 不应该被写入。
             */
            response.sendRedirect(basePath + "noRight.jsp?uri=" + uri);
        }
        return false;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 后， 也就是在Controller的方法调用之后执行，
     * 但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
     * 这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
     * 这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
     * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor或者是调用action，
     * 然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        log.info("拦截器拦截成功------------- postHandle：DispatcherServlet是前端控制器设计模式的实现，提供Spring Web MVC的集中访问点，而且负责职责的分派，而且与Spring IoC容器无缝集成，从而可以获得Spring的所有好处");
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 这个方法的主要作用是用于清理资源的，
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub
        log.info("拦截器拦截成功++++++++++++++ afterCompletion：清理资源");
    }
}
