package com.zyh.domain.base;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 为AOP切面编程服务 为数据库动态切换配置 通过Spring配置AOP编程 Spring配置自动拦截相关操作 配合注解
 *
 * @author 1101399
 * @CreateDate 2018-6-20 上午9:47:43
 */
public class DataSourceAspect {

    /**
     * 配置控制台日志打印
     */
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * AOP切点对应的相关编程
     *
     * Aspect：关注点的模块化。类似于类声明，包含PointCut和对应的Advice。在Spring
     * AOP中被定义为接口@Aspect，作用于TYPE（类、接口、方法、enum）
     *
     * JoinPoint：程序执行过程中明确的点，如方法的调用或特定的异常被抛出。
     * 常用的是getArgs()用来获取参数，getTarget()获得目标对象。
     *
     * 相关资料：https://www.cnblogs.com/sjlian/p/7325602.html
     *
     * @param point
     */
    @Before("com.zyh.domain.base.DataSource")
    public void before(JoinPoint point) {
        LOG.debug("我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲我有一句MMP不值当讲不当讲");
        LOG.info("拦截了每一个dao层方法，检测是否存在着相应的注解，存在则进行相应的注解操作");
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod()
                .getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                // 访问mapper中的注解
                DataSource data = m.getAnnotation(DataSource.class);// 获得注解对象
                switch (data.value()) {
                case MASTER:
                    DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
                    LOG.info("using dataSource:{}", DataSourceType.MASTER);
                    break;
                case SLAVE:// 次数输出不了相应的部分
                    LOG.info("TEST_SLAVE0:",DataSourceType.values());
                    LOG.info("TEST_SLAVE1:",DataSourceType.SLAVE);
                    LOG.info("测试是否实现数据库");
                    LOG.info("TEST_SLAVE2:",DataSourceType.SLAVE.toString());
                    DataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE);
//                    DataSourceContextHolder.setDataSourceType(data.value());
                    LOG.info("TEST_SLAVE3:",DataSourceContextHolder.getDataSourceType());
                    LOG.info("TEST_SLAVE4:",DataSourceContextHolder.getDataSourceType().toString());
                    LOG.info("using dataSource:{}", DataSourceType.SLAVE);
                    break;
                default:
                    LOG.info("----------------------------");
                    break;
                }
            } else {
                LOG.info("为空或者使用的注解值不是枚举！");
            }
        } catch (Exception e) {
            LOG.error("dataSource annotation error:{}", e.getMessage());
            // 若出现异常，手动设为主库
            DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
        } finally {
            LOG.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
