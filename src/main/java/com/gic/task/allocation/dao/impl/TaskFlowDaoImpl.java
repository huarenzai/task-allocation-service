package com.gic.task.allocation.dao.impl;

import com.dexcoder.commons.pager.Pager;
import com.dexcoder.dal.build.Criteria;
import com.dexcoder.dal.spring.page.PageControl;
import com.gic.task.allocation.dao.BaseDao;
import com.gic.task.allocation.dao.TaskFlowDao;
import com.gic.task.allocation.entity.TaskFlowEntity;
import com.gic.task.allocation.qo.ApiQueryDetailListQo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Component
public class TaskFlowDaoImpl extends BaseDaoImpl<TaskFlowEntity> implements TaskFlowDao {

    public List<TaskFlowEntity> queryListByPage(ApiQueryDetailListQo apiQueryDetailListQo) {
        PageControl.performPage(apiQueryDetailListQo);
        Criteria criteria = Criteria.select(TaskFlowEntity.class);
        criteria.where("1",new Object[]{"1"});
        if (StringUtils.isNotBlank(apiQueryDetailListQo.getTaskAllocationId())) {
            criteria.and("taskAllocationId",new Object[]{apiQueryDetailListQo.getTaskAllocationId()});
        }
        if (apiQueryDetailListQo.getStatus()!=-1) {
            criteria.and("status",new Object[]{apiQueryDetailListQo.getStatus()});
        }
        criteria.desc("createTime");
        jdbcDao.queryList(criteria);
        Pager pager = PageControl.getPager();
        return pager.getList(TaskFlowEntity.class);
    }
}
