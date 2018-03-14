package com.zyh.domain.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 定时调度相关quartz
 * Quartz与Spring的整合-Quartz中的job如何自动注入spring容器托管的对象
 * SpringBeanJobFactory 框架整合将Quartz动态注入到Spring bean中
 *
 * 参考资料
 * http://dark-wind-master.iteye.com/blog/1899899
 *
 * 生成SpringBeanJobFactory的子类，达到重写自定义SpringBeanJobFactory的作用。
 * 用于对Job注入ApplicationContext以及autowire私有变量等
 *
 * @author 1101399
 * @CreateDate: 2017-11-21 下午4:57:05
 */
@Component
public class MyspringBeanJobFactory extends org.springframework.scheduling.quartz.SpringBeanJobFactory{

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	/**
	 * 在这里我们覆盖了父类的createJobInstance方法(用super)，对其创建出来的对象再进行Autowire
	 */
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception{
		Object jobInstance = super.createJobInstance(bundle);
		beanFactory.autowireBean(jobInstance);
		return jobInstance;
	}

}
