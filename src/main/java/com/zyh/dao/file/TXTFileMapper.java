package com.zyh.dao.file;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.base.DataSource;
import com.zyh.domain.base.DataSourceType;
import com.zyh.domain.file.TXTFile;


/**
 * TXT文件的数据库接口
 *
 * @author      1101399
 * @CreateDate  2018-4-13 上午8:44:50
 */
@Repository(value="file.TXTFileMapper")
public interface TXTFileMapper extends MybatisMapper<TXTFile,Integer>{

    TXTFile findByName(@Param("name") String name);

    @DataSource(DataSourceType.SLAVE)
    TXTFile findDataById(@Param("id") Integer id);

}
