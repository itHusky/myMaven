package com.zyh.service.base;

import java.util.List;

import com.zyh.domain.base.Dictionary;

/**
 *  数据字典管理接口层
 *
 * @author 1101399
 * @CreateDate: 2017-11-24 下午4:31:28
 */
public interface IDictionaryService {

	public List<Dictionary> findByGroup(String group);

	/**
	 * 查找数据字典，并按键值从小到大排序
	 *
	 * @param group
	 *            组名（类别）
	 * @return
	 */
	public List<Dictionary> findByGroupAndASCByValue(String group);

	public String getName(String group, String value);

	public Integer getValue(String group, String name);
}
