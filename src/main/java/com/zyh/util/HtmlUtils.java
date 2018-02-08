package com.zyh.util;

import javax.servlet.http.HttpServletRequest;

/**
 * HTML相关操作：spring框架中有同名的包
 *
 * @author 1101399
 * @CreateDate 2018-1-31 上午8:10:56
 */
public class HtmlUtils {

    /**
     * 获取Web的basePath
     *
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + path + "/";
        return basePath;
    }

    /**
     * 判断用户请求是否为Ajax类型
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }

}
