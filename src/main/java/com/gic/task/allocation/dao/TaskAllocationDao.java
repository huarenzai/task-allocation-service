package com.gic.task.allocation.dao;

import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface TaskAllocationDao extends BaseDao<TaskAllocationEntity> {
    /**
     * 获取最新一条需要执行的任务
     * @return
     */
    public TaskAllocationEntity fingSingle();

    /**
     * 改变状态
     * @param status
     * @return
     */
    public boolean changeStatus(String taskAllocationId,int status);

    /**
     * 更新初始化总数
     * @param taskAllocationEntity
     * @return
     */
    public boolean updateInitTotal(TaskAllocationEntity taskAllocationEntity);

    /**
     * 更新处理数据
     * @param taskAllocationEntity
     * @return
     */
    public boolean updateDeal(TaskAllocationEntity taskAllocationEntity);


    /**
     * 批量查询
     * @param apiQueryListQo
     * @return
     */
    public List<TaskAllocationEntity> queryListByPage(ApiQueryListQo apiQueryListQo);

    /**
     * 查询
     * @param taskAllocationId
     * @return
     */
    public TaskAllocationEntity findSingleByTaskAllocationId(String taskAllocationId);

    /**
     * 获取总数
     * @param apiQueryListQo
     * @return
     */
    public int count(ApiQueryListQo apiQueryListQo);

//    public int

//    public void create()
}
