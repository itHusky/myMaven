package com.zyh.service.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.zyh.constants.base.Constants;
import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.base.PageModel;
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
public abstract  class BaseServiceImpl<E, PK extends Serializable> implements IBaseService<E, PK> {

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

	/**
	 * 分页函数
	 */
    @Override
    public PageModel<E> findByPage(E entity, Integer offset, Integer pageSize) {
        return findByPage(entity, offset, pageSize, null, null);
    }

    /**
     * 分页函数
     */
    @Override
    public PageModel<E> findByPage(E entity, Integer offset, Integer pageSize, String sort,
            String order) {
        return findByPage(CommonUtil.describe(entity), offset, pageSize, sort, order);
    }

    /**
     * 分页函数
     */
    @Override
    public PageModel<E> findByPage(Map<String, Object> paramMap, Integer offset, Integer pageSize,
            String sort, String order) {
        // XXX SQL防止注入[替换掉相关危险字符]
        sort = CommonUtil.TransactSQLInjection(sort);
        order = CommonUtil.TransactSQLInjection(order);

        if(StringUtils.hasText(order)){
            order = order.toUpperCase();// 字符转换为大写字母
            if (!("DESC".equals(order) || "ASC".equals(order))){
                throw new RuntimeException("Order by 排序方向的取值非法：" + order);
            }
        }

        offset = (offset == null ? 0 : offset);
        pageSize = (pageSize == null ? Constants.DEFAULT_PAGE_SIZE : pageSize);

        int total = getTotalByEntity(paramMap);
        paramMap.put("offset", offset);
        paramMap.put("rows", pageSize);
        paramMap.put("sort", sort);
        paramMap.put("order", order);
        List<E> rows = mybatisMapper.findByPage(paramMap);
        PageModel<E> page = new PageModel<E>();
        page.setTotal(total);
        page.setRows(rows);
        page.setPageSize(pageSize);
        page.setOffset(offset);
        page.setSort(sort);
        page.setOrder(order);
        return page;
    }
}
