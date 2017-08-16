package com.gic.task.allocation.dao.impl;

import com.dexcoder.commons.pager.Pager;
import com.dexcoder.dal.build.Criteria;
import com.dexcoder.dal.spring.page.PageControl;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.dao.TaskAllocationDao;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import com.gic.task.allocation.util.CommonUtil;
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
        String uuidRandom = CommonUtil.getUUIDRandom();
        String sql="update gic_task_allocation set own_sign=? where task_status=? and own_sign IS NULL order by deal_time desc limit 1";
        int i = jdbcDao.updateForSql(sql,new Object[]{uuidRandom,GlobalInfoParams.TASK_STATUS_INIT});
        Criteria criteria=Criteria.select(TaskAllocationEntity.class);
        if (i>0) {
            criteria.where("own_sign",new String[]{uuidRandom});
        }else{
            return null;
        }
        return jdbcDao.querySingleResult(criteria);//获取单挑数据
    }

    public boolean changeStatus(String taskAllocationId,int status,String reason) {
        Criteria criteria = Criteria.update(TaskAllocationEntity.class).set("taskStatus", status).
                set("dealTime",new Date()).set("reason",reason)
                .where("taskAllocationId", new Object[]{taskAllocationId});
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
        criteria.where("1","=",new Object[]{"1"});
        if (StringUtils.isNotBlank(apiQueryListQo.getTaskAllocationId())){
            criteria.and("taskAllocationId",new Object[]{apiQueryListQo.getTaskAllocationId()});
        }
        if (apiQueryListQo.getTaskType()!=-1){//类型
            criteria.and("taskType",new Object[]{apiQueryListQo.getTaskType()});
        }
        if (apiQueryListQo.getTaskStatus()!=-1){//类型
            criteria.and("taskStatus",new Object[]{apiQueryListQo.getTaskStatus()});
        }
        if (StringUtils.isNotBlank(apiQueryListQo.getEnterpriseId())){
            criteria.and("enterpriseId",new Object[]{apiQueryListQo.getEnterpriseId()});
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

    public int count(ApiQueryListQo apiQueryListQo) {
        Criteria criteria = Criteria.select(TaskAllocationEntity.class);
        criteria.where("1",new Object[]{"1"});
        if (StringUtils.isNotBlank(apiQueryListQo.getTaskAllocationId())){
            criteria.and("taskAllocationId",new Object[]{apiQueryListQo.getTaskAllocationId()});
        }
        if (apiQueryListQo.getTaskType()!=-1){//类型
            criteria.and("taskType",new Object[]{apiQueryListQo.getTaskType()});
        }
        if (apiQueryListQo.getTaskStatus()!=-1){//类型
            criteria.and("taskStatus",new Object[]{apiQueryListQo.getTaskStatus()});
        }
        if (StringUtils.isNotBlank(apiQueryListQo.getEnterpriseId())){
            criteria.and("enterpriseId",new Object[]{apiQueryListQo.getEnterpriseId()});
        }

        int i = jdbcDao.queryCount(criteria);
        return i;
    }
}
