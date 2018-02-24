package com.zyh.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zyh.domain.base.PageModel;

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

    /**
     * 分页函数
     *
     * @param entity
     *            包含查询条件的对象
     * @param offset
     *            分页起始点
     * @param pageSize
     *            每页记录数
     * @return 某一页的数据集 @
     */
    public PageModel<E> findByPage(E entity, Integer offset, Integer pageSize);

    /**
     * 分页函数 - 可按指定字段进行排序
     *
     * @param entity
     *            包含查询条件的对象
     * @param offset
     *            分页起始点
     * @param pageSize
     *            每页记录数
     * @param sort
     *            排序字段（默认为对象属性名，在Mybatis XML映射文件（一般以sqlOrder命名）中转换成对应的表字段名）
     * @param order
     *            排序方向：取值为ASC或DESC
     * @return 某一页的数据集 @
     */
    public PageModel<E> findByPage(E entity, Integer offset, Integer pageSize, String sort,
            String order);

    /**
     * 分页函数 - 可按指定字段进行排序
     *
     * @param paramMap
     *            包含查询条件
     * @param offset
     *            分页起始点
     * @param pageSize
     *            每页记录数
     * @param sort
     *            排序字段（默认为对象属性名，在Mybatis XML映射文件（一般以sqlOrder命名）中转换成对应的表字段名）
     * @param order
     *            排序方向：取值为ASC或DESC
     * @return 某一页的数据集 @
     */
    public PageModel<E> findByPage(Map<String, Object> paramMap, Integer offset, Integer pageSize,
            String sort, String order);
}
