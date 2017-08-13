package com.gic.task.allocation.service;

/**
 * Created by Administrator on 2017/8/12.
 */
public interface TaskAllocationSdkService {
    /**
     * 初始化
     * @param params
     */
    public void initTask(String params);

    /**
     * 结果回调 dubbo
     * @param params
     */
    public void resultCallback(String params);

    /**
     * 改变回调
     * @param status
     * @return
     */
    public boolean changeStatus(String taskAllocationId,int status);
}
