package com.gic.task.allocation.controller;

import com.alibaba.fastjson.JSONObject;
import com.dexcoder.dal.JdbcDao;
import com.gic.mq.sdk.GicMQClient;
import com.gic.mq.sdk.MQConfig;
import com.gic.task.allocation.common.TaskAllocationMemcache;
import com.gic.task.allocation.entity.TaskAllocationEntity;
import com.gic.task.allocation.entity.TestEntity;
import com.gic.task.allocation.qo.InitTaskQo;
import com.gic.task.allocation.service.TaskAllocationSdkService;
import com.gic.task.allocation.service.TaskAllocationService;
import com.gic.task.allocation.util.CommonUtil;
import com.gic.task.allocation.util.GICMQClientUtil;
import com.gic.task.allocation.util.MemCachedUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Created by Administrator on 2017/8/10.
 */
@Controller
public class TestController {
    private Log logger= LogFactory.getLog(TestController.class);

    @Autowired
    private TaskAllocationService taskAllocationService;

    @Autowired
    private TaskAllocationSdkService taskAllocationSdkService;
    @Autowired
    private JdbcDao jdbcDao;
    @RequestMapping("/test")
    public void test(){
//        TestEntity testEntity = new TestEntity();
////        jdbcDao.in
////        testEntity.se("aaa");
//        Long insert = jdbcDao.insert(testEntity);
//        System.out.println(insert);
////        InitTaskQo initTaskQo = new InitTaskQo();
////        initTaskQo.setOperationUserId("test1");
////        TaskAllocationEntity taskAllocationEntity = new TaskAllocationEntity();
//////        taskAllocationEntity.setEnterpriseId("enterprise1");
////////        taskAllocationEntity.setOperationUserId("user1");
////////        taskAllocationEntity.setTaskMqKey("mqkey1");
////        taskAllocationEntity.setTaskAllocationId(CommonUtil.getUUIDRandom());
////        Long insert = jdbcDao.insert(taskAllocationEntity);
////        logger.info(insert);
////        System.out.println("成功");
////        taskAllocationEntity.setCreateTime();
////        initTaskQo.
////        taskAllocationService.initTask();
////        logger.info("test");
////        System.out.println("test");
        InitTaskQo initTaskQo = new InitTaskQo();
        initTaskQo.setOperationUserId("clerkId");
        initTaskQo.setEnterpriseId("enterpriseId");
        initTaskQo.setTaskMqKey("testMq");
        initTaskQo.setTaskType(0);
        initTaskQo.setTaskSignKey("allocationSignKey");
        logger.info("=======");
        TaskAllocationMemcache.setStatus("aaa",1);
        int aaa = TaskAllocationMemcache.getStatus("aaa");

//        taskAllocationSdkService.initTask(JSONObject.toJSONString(initTaskQo));
//        logger.info(MQConfig.secretId);
//        GicMQClient clientInstance = GICMQClientUtil.getClientInstance();
//        try {
//            clientInstance.sendMessage("taskAllocationCallback","ccccc");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
