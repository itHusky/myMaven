package com.zyh.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.domain.mainCode.UserLogin;
import com.zyh.util.SessionUtils;

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
        // TODO Auto-generated method stub
        log.info("拦截器拦截成功");
        boolean success = false;

        /**
         * 判断用户登录信息是否存在 - 否的话返回登录界面 - 是的话继续进行下一步操作
         */
        UserLogin userLogin = SessionUtils.getLoginUser();
        if (null == userLogin) {
            // 界面返回login界面
        }

        /**
         * 判断过滤部分 - 通过URL判断实现对非法访问的拦截
         **/

        if (success)
            return false;
        else
            return true;
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
