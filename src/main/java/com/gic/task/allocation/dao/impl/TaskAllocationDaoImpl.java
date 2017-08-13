package com.gic.task.allocation.dao.impl;

import com.dexcoder.commons.pager.Pager;
import com.dexcoder.dal.build.Criteria;
import com.dexcoder.dal.spring.page.PageControl;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.dao.TaskAllocationDao;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 任务调度
 * Created by Administrator on 2017/8/11.
 */
@Component
public class TaskAllocationDaoImpl extends BaseDaoImpl<TaskAllocationEntity> implements TaskAllocationDao {
    private Logger logger=Logger.getLogger(this.getClass());
    public TaskAllocationEntity fingSingle() {
        Criteria criteria = Criteria.select(TaskAllocationEntity.class).where("taskStatus", new Object[]{GlobalInfoParams.TASK_STATUS_INIT}).desc("dealTime");
        return jdbcDao.querySingleResult(criteria);//获取单挑数据
    }

    public boolean changeStatus(String taskAllocationId,int status) {
        Criteria criteria = Criteria.update(TaskAllocationEntity.class).set("taskStatus", status).
                set("dealTime",new Date()).where("taskAllocationId", new Object[]{taskAllocationId});
//        getJdbcDao();
        int i = jdbcDao.update(criteria);
        return i>=0;
    }

    public boolean updateInitTotal(TaskAllocationEntity taskAllocationEntity) {
        Criteria criteria = Criteria.update(TaskAllocationEntity.class).set("taskStatus", taskAllocationEntity.getTaskStatus())
                .set("taskTotal", taskAllocationEntity.getTaskTotal())
                .set("dealTime", new Date()).where("taskAllocationId", new Object[]{taskAllocationEntity.getTaskAllocationId()});
        int i = jdbcDao.update(criteria);
        return i>0;
    }

    public boolean updateDeal(TaskAllocationEntity taskAllocationEntity) {
        Criteria criteria = Criteria.update(TaskAllocationEntity.class).set("[taskExecNum]", "[taskExecNum]+" + taskAllocationEntity.getTaskExecNum() + "")
                .set("[taskFailNum]", "[taskFailNum]+" + taskAllocationEntity.getTaskFailNum() + "")
                .where("taskAllocationId", new Object[]{taskAllocationEntity.getTaskAllocationId()});
        int i = jdbcDao.update(criteria);
        return i>0;
    }

    public List<TaskAllocationEntity> queryListByPage(ApiQueryListQo apiQueryListQo) {
        PageControl.performPage(apiQueryListQo);
        Criteria criteria = Criteria.select(TaskAllocationEntity.class);
        if (!StringUtils.isBlank(apiQueryListQo.getTaskAllocationId())){
            criteria.where("taskAllocationId",new Object[]{apiQueryListQo.getTaskAllocationId()});
        }
        if (apiQueryListQo.getTaskType()!=-1){//类型
            criteria.where("taskType",new Object[]{apiQueryListQo.getTaskType()});
        }
        if (apiQueryListQo.getTaskStatus()!=-1){//类型
            criteria.where("taskStatus",new Object[]{apiQueryListQo.getTaskStatus()});
        }
        criteria.desc("dealTime");
        jdbcDao.queryList(criteria);
        Pager pager = PageControl.getPager();
        return pager.getList(TaskAllocationEntity.class);
    }

    public TaskAllocationEntity findSingleByTaskAllocationId(String taskAllocationId) {
        Criteria criteria = Criteria.select(TaskAllocationEntity.class).where("taskAllocationId", new Object[]{taskAllocationId});
        return jdbcDao.querySingleResult(criteria);
    }
}