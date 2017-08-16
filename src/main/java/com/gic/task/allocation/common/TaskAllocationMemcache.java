package com.gic.task.allocation.common;

import com.gic.task.allocation.util.MemCachedUtil;

/**
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationMemcache {
    /**
     * 设置parans 值
     * @param taskAllocationId
     * @param params
     */
    public static void setParams(String taskAllocationId,String params){
        String key=taskAllocationId+"_params";
        MemCachedUtil.setValue(key,params,60*1000*60*60);
    }
    public static String getParams(String taskAllocationId){
        String key=taskAllocationId+"_params";
        return (String) MemCachedUtil.getValue(key);
    }

    /**
     * 状态
     * @param taskAllocationId
     * @param status
     */
    public static void setStatus(String taskAllocationId,int status) {
        MemCachedUtil.setValue(taskAllocationId,status,60*1000*60*60);
    }
    public static int getStatus(String taskAllocationId) {
        return (Integer) MemCachedUtil.getValue(taskAllocationId);
    }

    /**
     * 设置
     * @param status
     */
    public static  void  setStopFlag(int status){
        String key=GlobalInfoParams.PROCESS_IS_STOP_FLAG;
        MemCachedUtil.setValue(key,status,60*1000*60*60);
    }
    public static  int getStopFlag() {
        Object value =  MemCachedUtil.getValue(GlobalInfoParams.PROCESS_IS_STOP_FLAG);
        if (null==value) {
            return 0;
        }
        return (Integer)value;
    }

//    public static
}
