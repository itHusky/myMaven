package com.zyh.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
        return null;
    }

}
