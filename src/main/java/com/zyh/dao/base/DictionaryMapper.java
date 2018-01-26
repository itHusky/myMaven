package com.zyh.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zyh.domain.base.Dictionary;

/**
 * 数据字典数据Mapper层
 *
 * @author 1101399
 * @CreateDate: 2017-11-24 下午4:33:02
 */
public interface DictionaryMapper {

	public List<Dictionary> findByGroup(String group);

	/*这条函数明显不合*/
//	public String getName(@Param("group") String group, @Param("value") String value);

	public Integer getValue(@Param("group") String group, @Param("name") String name);
}
