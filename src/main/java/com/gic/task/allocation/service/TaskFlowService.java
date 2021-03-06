package com.gic.task.allocation.service;

import com.gic.task.allocation.entity.TaskFlowEntity;
import com.gic.task.allocation.qo.ApiQueryDetailListQo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface TaskFlowService extends BaseService<TaskFlowEntity> {
    /**
     * 查询
     * @param apiQueryDetailListQo
     * @return
     */
    public List<TaskFlowEntity> queryListByPage(ApiQueryDetailListQo apiQueryDetailListQo);
}
