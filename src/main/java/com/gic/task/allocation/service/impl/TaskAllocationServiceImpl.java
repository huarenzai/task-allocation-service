package com.gic.task.allocation.service.impl;

import com.dexcoder.commons.bean.BeanConverter;
import com.dexcoder.dal.spring.page.PageControl;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.common.pool.TaskAllocationThread;
import com.gic.task.allocation.dao.TaskAllocationDao;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import com.gic.task.allocation.qo.InitTaskQo;
import com.gic.task.allocation.service.TaskAllocationService;
import com.gic.task.allocation.util.MemCachedUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
@Service
public class TaskAllocationServiceImpl extends BaseServiceImpl<TaskAllocationEntity> implements TaskAllocationService {
    private Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;//线程池

    @Autowired
    private TaskAllocationDao taskAllocationDao;

//    public void initTask(InitTaskQo initTaskQo) {
//        TaskAllocationEntity taskAllocationEntity = BeanConverter.convert(new TaskAllocationEntity(), initTaskQo);
//        this.insert(taskAllocationEntity);
////        for (int i =0;i<100;i++) {
////            poolTaskExecutor.execute(new ThreadTask("msg",i));
////        }
//    }


    public boolean initTask(TaskAllocationEntity taskAllocationEntity,String params) {
        Long insert = this.insert(taskAllocationEntity);
        if (insert>0) {
            //设置缓存
            TaskAllocationMemcache.setParams(taskAllocationEntity.getTaskAllocationId(), params);
            //设置状态 正在等待分配中
            TaskAllocationMemcache.setStatus(taskAllocationEntity.getTaskAllocationId(), GlobalInfoParams.TASK_STATUS_INIT);
        }
        return insert>0;
    }

    public TaskAllocationEntity findSingle() {
        return taskAllocationDao.fingSingle();
    }

    public boolean changeStatus(String taskAllocationId,int status) {
        logger.info(taskAllocationId+"---改变状态w为："+status);
        boolean b = taskAllocationDao.changeStatus(taskAllocationId, status);
        if (b) {
            //设置状态
            TaskAllocationMemcache.setStatus(taskAllocationId,status);
        }
        return b;
    }

    public boolean updateInitTotal(TaskAllocationEntity taskAllocationEntity) {
        logger.info(taskAllocationEntity.getTaskAllocationId()+"----更新初始化总数--"+taskAllocationEntity.getTaskTotal());
        boolean b = taskAllocationDao.updateInitTotal(taskAllocationEntity);
        if (b) {
            //设置状态
            TaskAllocationMemcache.setStatus(taskAllocationEntity.getTaskAllocationId(),taskAllocationEntity.getTaskStatus());
            //
            TaskAllocationEntity allocationEntity = findSingleByTaskAllocationId(taskAllocationEntity.getTaskAllocationId());
            if (allocationEntity.getTaskExecNum()==allocationEntity.getTaskTotal()&&
                    (allocationEntity.getTaskStatus()!=GlobalInfoParams.TASK_STATUS_FAIL&&allocationEntity.getTaskStatus()!=GlobalInfoParams.TASK_STATUS_EXCEPTION)) {//成功
                changeStatus(taskAllocationEntity.getTaskAllocationId(),GlobalInfoParams.TASK_STATUS_SUCCESS);
            }
        }
        return b;
    }

    public boolean updateDeal(TaskAllocationEntity taskAllocationEntity) {
        logger.info(taskAllocationEntity.getTaskAllocationId()+"-----更新处理的数据---"+taskAllocationEntity.getTaskExecNum());
        boolean b = taskAllocationDao.updateDeal(taskAllocationEntity);
        if (!b) {
            logger.info(taskAllocationEntity.getTaskAllocationId()+"----updateDeal+任务保存异常!");
        }
        TaskAllocationEntity allocationEntity = findSingleByTaskAllocationId(taskAllocationEntity.getTaskAllocationId());
        if (allocationEntity.getTaskExecNum()==allocationEntity.getTaskTotal()&&
                (allocationEntity.getTaskStatus()!=GlobalInfoParams.TASK_STATUS_FAIL&&allocationEntity.getTaskStatus()!=GlobalInfoParams.TASK_STATUS_EXCEPTION)) {//成功
            changeStatus(taskAllocationEntity.getTaskAllocationId(),GlobalInfoParams.TASK_STATUS_SUCCESS);
        }
        return b;
    }

    public List<TaskAllocationEntity> queryListByPage(ApiQueryListQo apiQueryListQo) {
        return taskAllocationDao.queryListByPage(apiQueryListQo);
    }

    public TaskAllocationEntity findSingleByTaskAllocationId(String taskAllocationId){
        return taskAllocationDao.findSingleByTaskAllocationId(taskAllocationId);
    }

    public void startThreads() {
        ApiQueryListQo apiQueryListQo=new ApiQueryListQo();
        apiQueryListQo.setTaskStatus(GlobalInfoParams.TASK_STATUS_INIT);
        int count = taskAllocationDao.count(apiQueryListQo);
        TaskAllocationMemcache.setStopFlag(GlobalInfoParams.IS_START);//设置启动
        while (count-->0) {
            TaskAllocationThread taskAllocationThread = new TaskAllocationThread();
            taskAllocationThread.setTaskAllocationService(this);
            poolTaskExecutor.execute(taskAllocationThread);
        }
    }
}
