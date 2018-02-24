package com.zyh.domain.base;

import java.util.List;

/**
 * 分页对象
 *
 * @author 1101399
 * @CreateDate 2018-2-12 上午9:50:45
 */
public class PageModel<T> {

    private int offset;
    private int pageSize;
    private int total;
    private String sort;// 排序字段名称
    private String order;// 排序方向：ASC、DESC
    private List<T> rows;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
