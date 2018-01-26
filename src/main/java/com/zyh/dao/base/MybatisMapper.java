package com.zyh.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MybatisMapper<E, pk extends Serializable>{

	/**
	 * 获取总数
	 *
	 * @return
	 */
	public int getTotal();

	/**
	 * 获取包含这个Object的总数
	 *
	 * @param patamMap
	 * @return
	 */
	public int getTotalByEntity(Map<String, Object> patamMap);

	/**
	 * 通过id查找对应信息
	 *
	 * @param id
	 * @return
	 */
	public E findById(pk id);

	/**
	 * 查找全部信息
	 *
	 * @return
	 */
	public List<E> findAll();

	/**
	 * 按分页的方式查找
	 *
	 * @param patamMap
	 * @return
	 */
	public List<E> findByPage(Map<String, Object> patamMap);

	/**
	 * 插入一条记录
	 *
	 * @param entity
	 * @return
	 */
	public int insert(E entity);

	/**
	 * <pre>
	 * 当字段值不等于NULL时才更新。
	 * 		说明：当需要清空某个Integer型（非主键）原有数据，则无法使用该方法！
	 * </pre>
	 *
	 * @param entity
	 * @return
	 */
	public int update(E entity);

	/**
	 * 更新所有字段
	 *
	 * @param entity
	 * @return
	 */
	public int updateAll(E entity);

	/**
	 * 通过id删除一条信息
	 *
	 * @param id
	 * @return
	 */
	public int deleteById(pk id);

	/**
	 * 删除一条信息
	 *
	 * @param entity
	 * @return
	 */
	public int delete(E entity);

	/**
	 * 删除所有信息
	 *
	 * @return
	 */
	public int deleteAll();

}
