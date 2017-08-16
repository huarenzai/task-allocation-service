package com.gic.task.allocation.service.impl;

import com.gic.task.allocation.dao.TaskFlowDao;
import com.gic.task.allocation.entity.TaskFlowEntity;
import com.gic.task.allocation.qo.ApiQueryDetailListQo;
import com.gic.task.allocation.service.TaskFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
public class TaskFlowServiceImpl extends BaseServiceImpl<TaskFlowEntity> implements TaskFlowService {
    @Autowired
    private TaskFlowDao taskFlowDao;

    public List<TaskFlowEntity> queryListByPage(ApiQueryDetailListQo apiQueryDetailListQo) {
        return taskFlowDao.queryListByPage(apiQueryDetailListQo);
    }
}
