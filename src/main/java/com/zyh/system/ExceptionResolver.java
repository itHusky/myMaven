package com.zyh.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.exception.BusinessException;
import com.zyh.exception.ValidateException;
import com.zyh.util.HtmlUtils;
import com.zyh.util.JsonUtils;
import com.zyh.vo.base.JsonResult;


/**
 * Spring MVC九大组件之 HandlerExceptionResolver
 *
 * 根据异常设置ModelAndView
 *
 * 全局异常处理
 *
 * @author 1101399
 * @CreateDate 2018-3-12 上午10:29:22
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    /**
     * 日志控制台输出
     */
    private static final Logger logger = Logger.getLogger(ExceptionResolver.class);

    /**
     *
     * Try to resolve the given exception that got thrown during handler
     * execution, returning a {@link ModelAndView} that represents a specific
     * error page if appropriate.
     * <p>
     * The returned {@code ModelAndView} may be
     * {@linkplain ModelAndView#isEmpty() empty} to indicate that the exception
     * has been resolved successfully but that no view should be rendered, for
     * instance by setting a status code.
     *
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler, or {@code null} if none chosen at the
     *            time of the exception (for example, if multipart resolution
     *            failed)
     * @param ex
     *            the exception that got thrown during handler execution
     * @return a corresponding {@code ModelAndView} to forward to, or
     *         {@code null} for default processing
     *
     * 尝试解决处理程序执行期间抛出的给定异常， 返回代表特定错误页面的{@link ModelAndView}（如果适用）。
     * <p>
     * 返回的ModelAndView可能是{@linkplain ModelAndView＃isEmpty（）empty}
     * 表示已成功解决异常，但没有视图 应该呈现，例如通过设置状态码。
     *
     * @param请求当前的HTTP请求
     * @param响应当前的HTTP响应
     * @param处理程序执行的处理程序，或者如果没有在处理程序中选择，则为{null 例外的时间（例如，如果多部分解析失败）
     *                                          处理程序执行期间抛出的异常
     * @返回一个相应的{@Code ModelAndView}转发给或{@code null} 用于默认处理
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        // TODO Auto-generated method stub

        String errMsg = null;

        // 整体对异常信息依次过滤判断，输出相应的异常信息
        if(ex instanceof BusinessException){
            // 业务异常对象
            errMsg = "业务异常-|-" + ex.getMessage();
        }else if (ex instanceof ValidateException){
            // 验证异常
            errMsg = "验证异常-|-" + ex.getMessage();
        }else if (ex instanceof NullPointerException){
            // 录入数据不完整，或查询结果为空！
            errMsg = "录入数据不完整，或查询结果为空！-|-" + ex.getMessage();
        }else if(ex instanceof FileNotFoundException){
            // 文件不存在！
            errMsg = "文件不存在！-|-" + ex.getMessage();
        }else if(ex instanceof IOException){
            // 文件读写或网络传输异常！
            errMsg = "文件读写或网络传输异常！-|-" + ex.getMessage();
        }else if(ex instanceof org.mybatis.spring.MyBatisSystemException){
            // 数据库查询错误！请反馈系统管理员！
            errMsg = "数据库查询错误！请反馈系统管理员！-|-" + ex.getMessage();
        }else{
            // 其他异常|系统异常
            errMsg = "系统异常-|-" + ex.getMessage();
        }

        log(request, response, handler, ex);

        if(HtmlUtils.isAjaxRequest(request)){
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out;
            try{
                out = response.getWriter();
                out.write(JsonUtils.toString(new JsonResult<Object>(false, errMsg)));
                out.flush();
                out.close();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
            return null;
        } else {
            response.reset();// 清除下载文件设置的返回头信息（如response.setContentType("image/jpeg")等，以便能跳转到错误提示界面）
//          import com.sun.xml.xsom.impl.scd.Iterators.Map; 报错 - 原因：不同的Map对应的方法不一样
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("errMasg", errMsg);
            return new ModelAndView("error", model);
        }
    }

    /**
     * 写入系统日志
     */
    private void log(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        logger.error("全局异常拦截：", ex);
    }

}
