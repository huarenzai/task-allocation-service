package com.gic.task.allocation.dao.impl;

import com.dexcoder.dal.JdbcDao;
import com.gic.task.allocation.dao.BaseDao;
import com.gic.task.allocation.util.BeanFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import sun.org.mozilla.javascript.internal.Synchronizer;

import java.util.List;

/**
 * 基础类
 * Created by Administrator on 2017/8/12.
 */
public class BaseDaoImpl<T> implements BaseDao<T>
{
//    @Autowired
//    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected JdbcDao jdbcDao;

    protected  JdbcDao getJdbcDao(){
//        if (jdbcDao==null) {
//            synchronized (this.getClass()) {
//                if (jdbcDao==null)
//                    jdbcDao=(JdbcDao) BeanFactoryUtil.getBean("jdbcDao");
//            }
//        }
        return (JdbcDao) BeanFactoryUtil.getBean("jdbcDao");
    }
    public Long insert(T entity) {
        return getJdbcDao().insert(entity);
    }

    public boolean delete() {
        return false;
    }

    public List<T> queryList(T entity) {
        return null;
    }

    public T findSingle(T entity) {
        return null;
    }
}
