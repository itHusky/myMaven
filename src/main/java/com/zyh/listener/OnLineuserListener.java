package com.zyh.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zyh.constants.base.Constants;
import com.zyh.domain.mainCode.UserLogin;

/**
 * 在线用户监视器
 *
 * @author 1101399
 * @CreateDate: 2017-12-4 下午5:29:39
 */
public class OnLineuserListener implements HttpSessionListener, HttpSessionAttributeListener{

	private final String ONLINE_USER_LIST_NAME = "onLineUserList";
	private ServletContext application = null;
	private List<UserLogin> onLineUserList = null;

	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if (event.getValue() instanceof UserLogin) {
			UserLogin userLogin = (UserLogin) event.getValue();
			onLineUserList = (List<UserLogin>) application.getAttribute(ONLINE_USER_LIST_NAME);

			Iterator<UserLogin> iterator = onLineUserList.iterator();
			while (iterator.hasNext()) {
				UserLogin onLineUser = iterator.next();
				if (userLogin.getUserId() == onLineUser.getUserId()) {
					iterator.remove();
				}
			}
			onLineUserList.add(userLogin);
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		UserLogin userLogin = (UserLogin) event.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
		onLineUserList = (List<UserLogin>) application.getAttribute(ONLINE_USER_LIST_NAME);
		onLineUserList.remove(userLogin);
	}

	public void contextDestroyed(ServletContextEvent event){
		this.application.removeAttribute(ONLINE_USER_LIST_NAME);
	}

	public void contextInitialized(ServletContextEvent event){
		this.application = event.getServletContext();
		this.application.setAttribute(ONLINE_USER_LIST_NAME, new ArrayList<UserLogin>());
	}

}
