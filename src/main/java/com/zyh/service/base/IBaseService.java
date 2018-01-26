package com.zyh.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 通用管理
 *
 * @author 1101399
 * @CreateDate: 2017-11-29 上午8:37:10
 * @param <E>
 * @param <PK>
 */
public interface IBaseService<E, PK extends Serializable> {

	public int getTotal();

	public int getTotalByEntity(E entity);

	public int getTotalByEntity(Map<String, Object> paramMap);

	public E findById(PK id);

	public List<E> findAll();

	public int insert(E entity);

	public int update(E entity);

	public int delete(E entity);

	public int deleteById(PK id);

	public int deleteAll();

}
