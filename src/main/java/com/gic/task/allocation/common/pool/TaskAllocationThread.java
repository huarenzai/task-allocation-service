package com.gic.task.allocation.common.pool;

import com.alibaba.fastjson.JSONObject;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.AllocationTaskQo;
import com.gic.task.allocation.service.TaskAllocationService;
import com.gic.task.allocation.util.GICMQClientUtil;
import org.apache.log4j.Logger;

/**
 * 任务分配线程
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationThread implements Runnable {
    private Logger logger= Logger.getLogger(this.getClass());
//    private TaskAllocationService taskAllocationService = (TaskAllocationService)BeanFactoryUtil.getBean("taskAllocationServiceImpl");
    private TaskAllocationService taskAllocationService;
    public TaskAllocationService getTaskAllocationService() {
        return taskAllocationService;
    }

    public void setTaskAllocationService(TaskAllocationService taskAllocationService) {
        this.taskAllocationService = taskAllocationService;
    }

    public void run() {
        int stopFlag = TaskAllocationMemcache.getStopFlag();
        if (stopFlag==GlobalInfoParams.IS_STOP) {
            logger.info("已经停止运行");
            return;
        }
        TaskAllocationEntity single = taskAllocationService.findSingle();
        if (null!=single&&single.getTaskMqKey()!=null) {
            String params = TaskAllocationMemcache.getParams(single.getTaskAllocationId());
            try {
                AllocationTaskQo allocationTaskQo = new AllocationTaskQo();//协议
                allocationTaskQo.setParams(params);
                allocationTaskQo.setTaskAllocationId(single.getTaskAllocationId());
                //发送队列
                GICMQClientUtil.getClientInstance().sendMessage(single.getTaskMqKey(), JSONObject.toJSONString(allocationTaskQo));
                //测试
//                TestTaskService testTaskService = (TestTaskService) BeanFactoryUtil.getBean("testTaskService");
//                testTaskService.run(JSONObject.toJSONString(allocationTaskQo));
                //end

                //更新状态
                taskAllocationService.changeStatus(single.getTaskAllocationId(), GlobalInfoParams.TASK_STATUS_ALLOCATION_DEAL);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(single.getTaskAllocationId()+"分配失败!"+single.getTaskSignKey());
                //发送异常
                taskAllocationService.changeStatus(single.getTaskAllocationId(),GlobalInfoParams.TASK_STATUS_EXCEPTION,e.getMessage());
            }
        }
    }
}
