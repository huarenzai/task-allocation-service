package com.gic.task.allocation.common.pool;

import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.TaskCallbackQo;
import com.gic.task.allocation.service.TaskAllocationService;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationCallbackThread implements Runnable {
    private Logger logger=Logger.getLogger(this.getClass());
    private TaskCallbackQo taskCallbackQo;
    private TaskAllocationService taskAllocationService;

    public TaskAllocationService getTaskAllocationService() {
        return taskAllocationService;
    }

    public void setTaskAllocationService(TaskAllocationService taskAllocationService) {
        this.taskAllocationService = taskAllocationService;
    }

    public TaskCallbackQo getTaskCallbackQo() {
        return taskCallbackQo;
    }

    public void setTaskCallbackQo(TaskCallbackQo taskCallbackQo) {
        this.taskCallbackQo = taskCallbackQo;
    }

    public void run() {
        int status = TaskAllocationMemcache.getStatus(taskCallbackQo.getTaskAllocationId());
        if (status==GlobalInfoParams.TASK_STATUS_FAIL||status==GlobalInfoParams.TASK_STATUS_EXCEPTION) {
            logger.info(taskCallbackQo.getTaskAllocationId()+"任务已经结束！---"+status);
        }
        if (taskCallbackQo.getCallbackType()== GlobalInfoParams.CALLNACK_TYPE_INIT) {//初始化
            if (taskCallbackQo.getIsSuccess()==0) {//初始化全部异常
                taskAllocationService.changeStatus(taskCallbackQo.getTaskAllocationId(),GlobalInfoParams.TASK_STATUS_EXCEPTION);//异常状态
            }else{//改变
                TaskAllocationEntity taskAllocationEntity = new TaskAllocationEntity();
                if (status==GlobalInfoParams.TASK_STATUS_FAIL||status==GlobalInfoParams.TASK_STATUS_EXCEPTION) {//失败或者异常时候
                    taskAllocationEntity.setTaskStatus(status);
                }else{
                    taskAllocationEntity.setTaskStatus(GlobalInfoParams.TASK_STATUS_DEAL);
                }
                taskAllocationEntity.setTaskAllocationId(taskCallbackQo.getTaskAllocationId());
                taskAllocationEntity.setTaskTotal(taskCallbackQo.getTaskTotal());
                boolean b = taskAllocationService.updateInitTotal(taskAllocationEntity);//任务异常
            }
        }else if (taskCallbackQo.getCallbackType()==GlobalInfoParams.CALLBACK_TYPE_DEAL) {//处理数据
            TaskAllocationEntity taskAllocationEntity=new TaskAllocationEntity();
            taskAllocationEntity.setTaskExecNum(taskCallbackQo.getTaskTotal());
            taskAllocationEntity.setTaskFailNum(taskCallbackQo.getTaskFailNum());
            taskAllocationEntity.setTaskAllocationId(taskCallbackQo.getTaskAllocationId());
            boolean b = taskAllocationService.updateDeal(taskAllocationEntity);
//            taskAllocationService.initTask();
//            taskAllocationService.
        }
    }
}
