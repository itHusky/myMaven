package com.zyh.domain.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 根据数据源上下文进行判断，选择 方便进行通过注解进行数据源切换
 *
 * @author 1101399
 * @CreateDate 2018-6-19 下午3:59:44
 */
public class DataSourceContextHolder {

    /**
     * 控制台日志打印
     */
    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    /**
     * 线程本地环境
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    private static final ThreadLocal<DataSourceType> contextTypeHolder = new ThreadLocal<DataSourceType>();

    /**
     * 设置数据源类型：直接式
     *
     * @param dbType
     */
    public static void setDbType(String dbType) {
        Assert.notNull(dbType, "DataSourceType cannot be null");
        log.info("setDbType打印线程信息：",Thread.currentThread().getName());
        contextHolder.set(dbType);
    }

    /**
     * 设置数据源类型：枚举式
     *
     * @param dbType
     */
    public static void setDataSourceType(DataSourceType dbType) {
        Assert.notNull(dbType, "DataSourceType cannot be null");
        log.info("setDataSourceType测试{}",dbType);
        log.info("setDataSourceType测试{}",getDataSourceType());
        log.info("setDataSourceType测试{}",getDbType());

        /**
         * 获取不到当前线程对应的数据链接信息，显示为空，下方的打印显示也是为没有显示 TODO XXX
         */
        log.info("setDataSourceType打印线程信息：",Thread.currentThread().getName());
        log.info("XXX打印线程信息：",Thread.currentThread().isAlive());
        contextTypeHolder.set(dbType);
    }

    /**
     * 获取数据源类型：直接式
     *
     * @return
     */
    public static String getDbType() {
        log.info("getDbType打印线程信息：",Thread.currentThread().getName());
        return contextHolder.get();
    }

    /**
     * 获取数据源类型：枚举式
     *
     * @return
     */
    public static DataSourceType getDataSourceType() {
        log.info("getDataSourceType打印线程信息：",Thread.currentThread().getName());
        return contextTypeHolder.get();
    }

    /**
     * 清楚数据类型
     */
    // 这个方法必不可少 否则切换数据库的时候有缓存现在
    public static void clearDbType() {
        contextHolder.remove();
    }

    /**
     * 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextTypeHolder.remove();
    }

}
