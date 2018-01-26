package com.zyh.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zyh.dao.base.DictionaryMapper;
import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.base.Dictionary;
import com.zyh.service.base.IDictionaryService;

/**
 * 数据字典接口实现层
 *
 * @author 1101399
 * @CreateDate: 2017-11-24 下午4:32:12
 */
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary,Integer> implements IDictionaryService {

	private DictionaryMapper dicMapper;

	@Override
	@Resource(name = "dictionaryMapper")
	public void setMybatisMapper(MybatisMapper<Dictionary, Integer> entityMapper){
		this.mybatisMapper = entityMapper;
		this.dicMapper = (DictionaryMapper) entityMapper;
	}

	@Override
	public List<Dictionary> findByGroup(String group) {
		return dicMapper.findByGroup(group);
	}

	@Override
	public List<Dictionary> findByGroupAndASCByValue(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(String group, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getValue(String group, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
