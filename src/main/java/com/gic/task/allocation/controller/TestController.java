package com.gic.task.allocation.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/10.
 */
@Controller
public class TestController {
    private Log logger= LogFactory.getLog(TestController.class);
    @RequestMapping("/test")
    public void test(){
        logger.info("test");
        System.out.println("test");
    }
}
