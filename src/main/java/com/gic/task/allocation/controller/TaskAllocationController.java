package com.gic.task.allocation.controller;

import com.dexcoder.commons.bean.BeanConverter;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.qo.ApiQueryListQo;
import com.gic.task.allocation.service.TaskAllocationService;
import com.gic.task.allocation.util.MemCachedUtil;
import com.gic.task.allocation.vo.TaskAllocationVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    public String removeTask(String taskAllocationId,ModelMap modelMap) {
        boolean b = taskAllocationService.changeStatus(taskAllocationId, GlobalInfoParams.TASK_STATUS_FAIL);//取消
        modelMap.addAttribute("retCode",b);
        return "json";
    }
    @RequestMapping("/queryTask")
    public String queryTask(ApiQueryListQo apiQueryListQo, ModelMap modelMap) {
        List<TaskAllocationEntity> taskAllocationEntities = taskAllocationService.queryListByPage(apiQueryListQo);

//        List<TaskAllocationVo> taskAllocationVos = BeanConverter.convert(TaskAllocationVo.class, taskAllocationEntities, new String[]{"createTime", "dealTime"});
        List<TaskAllocationVo> list=new ArrayList<TaskAllocationVo>();
        if (taskAllocationEntities!=null) {
            for (TaskAllocationEntity taskAllocationEntity : taskAllocationEntities) {
                TaskAllocationVo allocationVo = BeanConverter.convert(new TaskAllocationVo(), taskAllocationEntity, new String[]{"createTime", "dealTime"});
                allocationVo.setCreateTime(DateFormatUtils.format(taskAllocationEntity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                allocationVo.setDealTime(DateFormatUtils.format(taskAllocationEntity.getDealTime(), "yyyy-MM-dd HH:mm:ss"));
                long l = taskAllocationEntity.getDealTime().getTime() - taskAllocationEntity.getCreateTime().getTime();//speed
                allocationVo.setSpeed(Long.valueOf(l).toString());
                list.add(allocationVo);
            }
        }
        modelMap.addAttribute("list",list);
        return "json";
    }
    /**
     * 启动线程
     * @param modelMap
     * @return
     */
    @RequestMapping("/startThread")
    public String startThread(ModelMap modelMap) {
        taskAllocationService.startThreads();
        modelMap.addAttribute("retCode",1);
        return "json";
    }

    /**
     * 停止执行
     * @param modelMap
     * @return
     */
    @RequestMapping("/stopProcess")
    public String stopProcess(ModelMap modelMap){
        int retCode=1;
        try {
            TaskAllocationMemcache.setStopFlag(GlobalInfoParams.IS_STOP);
        }catch (Exception e){
            retCode=0;
        }
        modelMap.addAttribute("retCode",retCode);
        return "json";
    }

    /**
     * 应用状态
     * @return
     */
    @RequestMapping("/processStatus")
    public String processStatus(ModelMap modelMap){
        int stopFlag = TaskAllocationMemcache.getStopFlag();
        modelMap.addAttribute("status",stopFlag);
        return "json";
    }
}
