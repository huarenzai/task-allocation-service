package com.gic.task.allocation.controller;

import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import com.gic.task.allocation.service.TaskAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */
@Controller
public class TaskAllocationController {
    @Autowired
    private TaskAllocationService taskAllocationService;
    /**
     * 取消队列
     */
    @RequestMapping("/remove")
    public String removeTask(String taskAllocationId) {
        boolean b = taskAllocationService.changeStatus(taskAllocationId, GlobalInfoParams.TASK_STATUS_FAIL);//取消
        return "";
    }
    @RequestMapping("/queryTask")
    public String queryTask(ApiQueryListQo apiQueryListQo, ModelMap modelMap) {
        List<TaskAllocationEntity> taskAllocationEntities = taskAllocationService.queryListByPage(apiQueryListQo);
        System.out.println(taskAllocationEntities);
        return "";
    }
}
