package com.gic.task.allocation.dao;

import com.gic.task.allocation.entity.TaskFlowEntity;
import com.gic.task.allocation.qo.ApiQueryDetailListQo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface TaskFlowDao extends BaseDao<TaskFlowEntity> {
    public List<TaskFlowEntity> queryListByPage(ApiQueryDetailListQo apiQueryDetailListQo);
}
