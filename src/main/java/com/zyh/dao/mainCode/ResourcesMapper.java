package com.zyh.dao.mainCode;

import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.Resources;

/**
 * Dao层： ResourcesMapper->URL资源列表Service层对应的数据操作接口层
 *
 * @author      1101399
 * @CreateDate  2018-1-26 下午5:13:15
 */
@Repository("mainCode.ResourcesMapper")
public interface ResourcesMapper extends MybatisMapper<Resources, Integer>{

}
