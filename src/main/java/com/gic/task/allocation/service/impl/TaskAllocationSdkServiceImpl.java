package com.gic.task.allocation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dexcoder.commons.bean.BeanConverter;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.common.pool.TaskAllocationCallbackThread;
import com.gic.task.allocation.common.pool.TaskAllocationThread;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.InitTaskQo;
import com.gic.task.allocation.qo.TaskCallbackQo;
import com.gic.task.allocation.service.TaskAllocationSdkService;
import com.gic.task.allocation.service.TaskAllocationService;
import com.gic.task.allocation.util.CommonUtil;
import com.gic.task.allocation.util.MemCachedUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/12.
 */
@Service
public class TaskAllocationSdkServiceImpl implements TaskAllocationSdkService {
    private Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;//线程池

    @Autowired
    private TaskAllocationService taskAllocationService;

    public void initTask(String params) {
        InitTaskQo initTaskQo = JSONObject.parseObject(params, InitTaskQo.class);//转换对象
        TaskAllocationEntity taskAllocationEntity = BeanConverter.convert(new TaskAllocationEntity(), initTaskQo, new String[]{"params"});
        taskAllocationEntity.setTaskStatus(GlobalInfoParams.TASK_STATUS_INIT);//任务初始化
        taskAllocationEntity.setTaskAllocationId(CommonUtil.getUUIDRandom());//任务id
        taskAllocationEntity.setCreateTime(new Date());//创建时间
        taskAllocationEntity.setDealTime(new Date());//执行时间
        boolean b = taskAllocationService.initTask(taskAllocationEntity, initTaskQo.getParams());
        if (b) {//执行线程池
            TaskAllocationThread taskAllocationThread=new TaskAllocationThread();
            taskAllocationThread.setTaskAllocationService(taskAllocationService);
            threadPoolTaskExecutor.execute(taskAllocationThread);//执行线程池
        }else{
            logger.info(taskAllocationEntity.getOperationUserId()+"创建"+taskAllocationEntity.getTaskMqKey()+"失败");
        }
    }

    public void resultCallback(String params) {
        logger.info("任务回调"+params);
//        TaskAllocationMemcache.getStatus().
        TaskCallbackQo taskCallbackQo = JSONObject.parseObject(params, TaskCallbackQo.class);
        int callbackType = taskCallbackQo.getCallbackType();//回调类型
        TaskAllocationCallbackThread taskAllocationCallbackThread = new TaskAllocationCallbackThread();
        taskAllocationCallbackThread.setTaskCallbackQo(taskCallbackQo);
        taskAllocationCallbackThread.setTaskAllocationService(taskAllocationService);

        if (callbackType==GlobalInfoParams.CALLNACK_TYPE_INIT) {
            threadPoolTaskExecutor.execute(taskAllocationCallbackThread);
        }else if (callbackType==GlobalInfoParams.CALLBACK_TYPE_DEAL) {
            threadPoolTaskExecutor.execute(taskAllocationCallbackThread);
        }else {
            logger.error(taskCallbackQo.getTaskAllocationId()+"处理回调类型错误:"+callbackType);
            return;
        }
//        System.out.println(params+"回调====================");
//        logger.info("结果回调"+params);
    }

    public boolean changeStatus(String taskAllocationId,int status) {
        logger.info("改变状态");
        return false;
    }
}
