package com.gic.task.allocation.service.impl;

import com.gic.task.allocation.dao.BaseDao;
import com.gic.task.allocation.dao.impl.BaseDaoImpl;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseDao<T> baseDao=new BaseDaoImpl<T>();

    public BaseServiceImpl(){
        
    }

    public Long insert(T entity) {
        return baseDao.insert(entity);
    }

    public boolean delete() {
        return baseDao.delete();
    }

    public List<T> queryList(T entity) {
        return baseDao.queryList(entity);
    }

    public T findSingle(T entity) {
        return baseDao.findSingle(entity);
    }
}
