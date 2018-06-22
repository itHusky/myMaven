package com.zyh.domain.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;

import javax.activation.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 * 动态数据源
 *
 * @author 1101399
 * @CreateDate 2018-6-19 下午3:28:09
 */
public class DynamicDataSource extends AbstractRoutingDataSource implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    private Map<String, DataSource> masterDataSources;// 主数据库
    private Map<String, DataSource> slaveDataSources;// 从数据库
    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

    public Map<String, DataSource> getMasterDataSources() {
        return masterDataSources;
    }

    public void setMasterDataSources(Map<String, DataSource> masterDataSources) {
        this.masterDataSources = masterDataSources;
    }

    public Map<String, DataSource> getSlaveDataSources() {
        return slaveDataSources;
    }

    public void setSlaveDataSources(Map<String, DataSource> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    public DataSourceLookup getDataSourceLookup() {
        return dataSourceLookup;
    }

    @Override
    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        this.dataSourceLookup = dataSourceLookup;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return ((Wrapper) determineTargetDataSource()).unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isInstance(this) || ((Wrapper) determineTargetDataSource())
                .isWrapperFor(iface));
    }

    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return lookupKey;
    }

    /**
     * 返回数据源key值
     */
    @SuppressWarnings("unused")
    @Override
    protected Object determineCurrentLookupKey() {
        if(false){
            return DataSourceContextHolder.getDbType();
        }else{
            return DataSourceContextHolder.getDataSourceType();
        }
    }

}
