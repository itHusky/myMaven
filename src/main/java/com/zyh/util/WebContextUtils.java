package com.zyh.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 这个类的作用暂时还不知道！！！ 这个应该是SESSION存储管理
 *
 * @author 1101399
 * @CreateDate: 2017-11-22 上午9:50:01
 */
public class WebContextUtils {

    /**
     * 要使用RequestContextHolder，必须在Web.xml中配置<br>
     * <listener-class>org.springframework.web.context.request.
     * RequestContextListener</listener-class>
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 返回一个会话如果请求没有的话则创建一个会话
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        /**

        session.setMaxInactiveInterval(0);
                        设置的是当前会话的失效时间，不是整个web的时间，单位为以秒计算。如果设置的值为零或负数，则表示会话将永远不会超时。

        */
        return session;
    }

    /**
     * 返回浏览器的cookie数据
     * public static Cookie[] getCookie(){
     *  Cookie[] cookie = getRequest().getCookies();
     *  return cookie;
     * }
     */

    /**
     * 返回此会话所属的ServletContext。
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return getSession().getServletContext();
    }

    /**
     * 获取网站根目录"zyh.root"的实际路径
     *
     * <pre>
     * 例如：D:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\FAProject\
     * </pre>
     *
     * System.getProperty()方法可以获取的值：http://blog.csdn.net/kongqz/article/details/
     * 3987198
     *
     * @return
     */
    public static String getWebRootPath() {
        return System.getProperty("zyh.root");
    }

}
