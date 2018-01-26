/*package com.zyh.system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

*//**
 * 用户权限管控
 *
 * @author 1101399
 * @CreateDate: 2018-1-3 下午3:25:13
 *//*
// TODO http://blog.csdn.net/u011277123/article/details/68940939
// TODO http://blog.csdn.net/Petershusheng/article/details/52396785
// TODO http://blog.csdn.net/zwx19921215/article/details/44467099
// TODO https://www.cnblogs.com/sharpest/p/5998089.html
// TODO https://www.cnblogs.com/yueyue-dream/p/5574327.html
// TODO http://blog.csdn.net/zx520sun/article/details/53019367
// TODO http://blog.csdn.net/qq_19244423/article/details/46547543

@Service
public class StartListener implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		WebApplicationContext webApplicationContext = ContextLoader
				.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext
				.getServletContext();
		List<Permission> PermissionList = roleDao.listPermission(null);// 获取所有的permission
		List<String> allUrlList = roleDao.listResourceString(null);// 获取所有的resource
		// 建立 resource与permission 对应关系
		Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
		for (String temS : allUrlList) {
			Set<String> atts = new HashSet<String>();
			atts.add("notset");// 增加这个主要是为了防止后台建立了URL但漏做Permission关联而被允许通过
			resourceMap.put(temS, atts);
		}

		for (Permission temPermission : PermissionList) {
			String temPermission_ca = temPermission.getPermission();
			List<String> urlList = roleDao.listResourceString(temPermission
					.getId());

			for (String temUrl : urlList) {
				Set<String> temSet = resourceMap.get(temUrl);
				temSet.add(temPermission_ca);
				resourceMap.put(temUrl, temSet);
			}
		}
		servletContext.setAttribute("resourceMap", resourceMap);
	}

}
*/