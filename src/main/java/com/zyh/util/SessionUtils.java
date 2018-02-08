package com.zyh.util;

import com.zyh.constants.base.Constants;
import com.zyh.domain.mainCode.UserLogin;

/**
 * 系统登录的重要信息保存或者处理到SESSION中 <br>
 * 现在我测试使用的方法是修改web.xml(tomcat.web.xml)文件的方式来用以延长session的持久时间
 * <br>
 * <session-config>
 *      <session-timeout>30</session-timeout>
 * </session-config>
 * <br>
 * tomcat 服务器中session信息默认存储有效时间为30min
 * <br>
 * <br>
 * session的有效期在web.xml中设置 有效时间的范围以分钟为单位最长时间24小时(1440) <br>
 * 解决方法: 1、欲使有效期为永久建议将数据写到Cookies中
 * 2、创建一个保持session的界面定时自动刷新自己沟通服务器实现session信息的有效时间刷新 (meta自动刷新嵌套的iframe的方法)
 * <html> <head> <meta http-equiv="Refresh"
 * content="900000;url=sessionKeeper.asp">
 * <!--每隔900秒刷新一下自己，为了和服务器通讯一下，保持session不会丢--> </head> </html>
 * 3、用javascript:window.setTimeout("functionname()",10000);
 * 第隔一段时间时间自动调用一个函数的方法，当然函数里还是要去连接一个空的文件
 *
 * <script id=Back language=javascript></script> <script language=javascript>
 * function keepsession(){
 * document.all["Back"].src="/blog/SessionKeeper.asp?RandStr="+Math.random();
 * //这里的RandStr=Math.random只是为了让每次back.src的值不同，防止同一地址刷新无效的情况
 * window.setTimeout("keepsession()",900000); //每隔900秒调用一下本身 } keepsession();
 * </script> 同一目录下建一个空内容的sessionKeeper.asp就文件就可以了
 *
 * @author 1101399
 * @CreateDate: 2017-11-22 下午2:07:33
 */
public class SessionUtils {

    /**
     * 删除SESSION中用户拥有的URI权限
     */
    public static void removeURTList() {
        WebContextUtils.getSession().removeAttribute(Constants.SESSION_URI_LIST);
    }

    /**
     * 将登陆用户对象保存至SESSION中
     */
    public static void setLoginUser(UserLogin userLogin) {
        WebContextUtils.getSession().setAttribute(Constants.SESSION_LOGIN_USER, userLogin);
    }

    /**
     * 获取当前会话的登陆用户对象
     */
    public static UserLogin getLoginUser() {
        return (UserLogin) WebContextUtils.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
    }

    /**
     * 用户登录的时候加载用户权限并放到SESSION
     *
     * TODO
     **/
    /*
     * @RequestMapping(value = "/chklogin", method = RequestMethod.POST) public
     * String chkLogin(HttpServletRequest request, HttpServletResponse response,
     * Model model) throws Exception{ if
     * (!CSRFManager.checkTokenMatch(request)){ return "redirect:/login?fail"; }
     * HttpSession session = request.getSession(); String checkcode = (String)
     * session.getAttribute(Constants.SESSION_KEY_xxx);//验证码校验 String
     * reqCheckcode = request.getParameter("checkcode"); if(reqCheckcode == null
     * || "".equals(reqCheckcode) || checkcode == null ||
     * !checkcode.equals(reqCheckcode)){ return "redirect:/login?checkcode"; }
     *
     * try{ ; } catch(Exception e){ ; }
     *
     * return ""; }
     */
}
