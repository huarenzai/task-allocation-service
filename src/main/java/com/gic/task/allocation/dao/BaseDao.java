package com.gic.task.allocation.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface BaseDao<T> {
    /**
     * 插入
     * @param entity
     * @return
     */
    public Long insert(T entity);

    /**
     * 删除
     * @return
     */
    public boolean delete();

    /**
     * 查询
     * @param entity
     * @return
     */
    public List<T> queryList(T entity);

    /**
     * 查询单条
     * @param entity
     * @return
     */
    public T findSingle(T entity);
}
