package com.gic.task.allocation.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2017/8/11.
 */
public class ThreadTask implements Runnable {
    private Log logger= LogFactory.getLog(ThreadTask.class);
    private String msg;
    protected int i;
    public ThreadTask(String msg,int i){
        this.msg=msg;
        this.i=i;
    }
    public void run() {
        logger.info("thread==============="+msg+"========"+i+"=======");
    }
}
