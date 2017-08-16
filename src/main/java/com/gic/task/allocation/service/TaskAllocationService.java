package com.gic.task.allocation.service;

import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import com.gic.task.allocation.qo.InitTaskQo;
import com.gic.task.allocation.qo.TaskCallbackQo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface TaskAllocationService extends BaseService<TaskAllocationEntity> {
    /**
     * 初始化任务
     * @param taskAllocationEntity
     */
    public boolean initTask(TaskAllocationEntity taskAllocationEntity,String params);

    /**
     * 获取需要执行的任务
     * @return
     */
    public TaskAllocationEntity findSingle();

    /**
     * 改变状态
     * @param status
     * @return
     */
    public boolean changeStatus(String taskAllocationId,int status);

    /**
     * 改变状态
     * @param status
     * @return
     */
    public boolean changeStatus(String taskAllocationId,int status,String reason);

    /**
     * 更新初始化数据
     * @param taskAllocationEntity
     * @return
     */
    public boolean updateInitTotal(TaskAllocationEntity taskAllocationEntity);

    /**
     * 更新处理的数据
     * @param taskAllocationEntity
     * @return
     */
    public boolean updateDeal(TaskAllocationEntity taskAllocationEntity, TaskCallbackQo taskCallbackQo);

    /**
     * 查询
     * @param apiQueryListQo
     * @return
     */
    public List<TaskAllocationEntity> queryListByPage(ApiQueryListQo apiQueryListQo);

    /**
     * 获取单挑数据
     * @param taskAllocationId
     * @return
     */
    public TaskAllocationEntity findSingleByTaskAllocationId(String taskAllocationId);

    /**
     * 开始线程
     */
    public void startThreads();
}
