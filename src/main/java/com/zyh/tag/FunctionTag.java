package com.zyh.tag;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.zyh.constants.base.Constants;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.util.SessionUtils;

/**
 * 权限控制相关
 *
 * 资料：http://dongguoh.iteye.com/blog/100782
 *
 * 实现对父类BodyTagSupport函数的重载已达到符合权限允许访问，否则禁止访问的效果
 *
 * @author 1101399
 * @CreateDate: 2017-11-22 下午3:41:49
 */
public class FunctionTag extends BodyTagSupport {

    /**
     * (序列化)验证版本一致性
     */
    private static final long serialVersionUID = 1L;

    private String uri = null;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 判断用户登录的权限
     *
     * doStartTag()方法是遇到标签开始时会呼叫的方法， 其合法的返回值是EVAL_BODY_INCLUDE与SKIP_BODY,
     * 前者表示将显示标签间的文字，后者表示不显示标签间的文字；
     * 前者显示标签中的内容，后者则跳过不显示标签内容：这样实现了跳过不显示没有权限访问的内容、联合数据库部分的权限啊管理实现对权限的管控
     */
    @Override
    public int doStartTag() throws JspTagException {
        UserLogin userLogin = SessionUtils.getLoginUser();
        // URL 权限验证
        if (isAllow(uri, userLogin)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * doEndTag()方法是在遇到标签结束时呼叫的方法， 其合法的返回值是EVAL_PAGE与 SKIP_PAGE，
     * 前者表示处理完标签后继续执行以下的JSP网页， 后者是表示不处理接下来的JSP网页
     */
    @Override
    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;// 表示处理完标签后继续执行以下的JSP网页
    }

    /**
     * 判断用户是否有权限访问指定的uri
     *
     * @param uri
     *            要访问的URI
     * @param userLogin
     *            访问的用户
     * @return false OR true
     */
    // 用于抑制编译器产生警告信息
    @SuppressWarnings("unchecked")
    private boolean isAllow(String uri, UserLogin userLogin) {

        boolean success = false;

        HttpSession session = pageContext.getSession();
        List<String> URIList = (List<String>) session.getAttribute(Constants.SESSION_URI_LIST);
        if (URIList == null || URIList.isEmpty()) {
            success = false;
        } else {
            for (int i = 0; i < URIList.size(); i++) {
                String regexURL = URIList.get(i);

                // 使用正则表达式进行匹配（数据库中已设置的url本身就是个正则表达式）
                Pattern pattern = Pattern.compile(regexURL);
                Matcher matcher = pattern.matcher(uri);
                if (matcher.matches()) {
                    success = true;
                    break;
                }
            }
        }

        return success;
    }
}
