package com.zyh.service.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.service.base.IBaseService;
import com.zyh.util.CommonUtil;

/**
 * 通用管理
 *
 * @author 1100558
 * @date 2013-12-3 下午02:20:23
 * @param <E>
 * @param <PK>
 */
public abstract class BaseServiceImpl<E, PK extends Serializable> implements IBaseService<E, PK> {

	protected MybatisMapper<E, PK> mybatisMapper;

	public abstract void setMybatisMapper(MybatisMapper<E, PK> entityMapper);

	@Override
	public int getTotal() {
		return mybatisMapper.getTotal();
	}

	@Override
	public int getTotalByEntity(E entity) {
		return mybatisMapper.getTotalByEntity(CommonUtil.describe(entity));
	}

	@Override
	public int getTotalByEntity(Map<String, Object> paramMap) {
		return mybatisMapper.getTotalByEntity(paramMap);
	}

	@Override
	public E findById(PK id) {
		return mybatisMapper.findById(id);
	}

	@Override
	public List<E> findAll() {
		return mybatisMapper.findAll();
	}

	@Override
	public int insert(E entity) {
		return mybatisMapper.insert(entity);
	}

	@Override
	public int update(E entity) {
		return mybatisMapper.update(entity);
	}

	@Override
	public int delete(E entity) {
		return mybatisMapper.delete(entity);
	}

	@Override
	public int deleteById(PK id) {
		return mybatisMapper.deleteById(id);
	}

	@Override
	public int deleteAll() {
		return mybatisMapper.deleteAll();
	}
}
