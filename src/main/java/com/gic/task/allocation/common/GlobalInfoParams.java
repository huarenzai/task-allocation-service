package com.gic.task.allocation.common;

import java.util.Map;

/**
 * 参数
 * Created by Administrator on 2017/8/12.
 */
public class GlobalInfoParams {
    public static Map<String, String> ctxPropertiesMap;// 上下文属性Map

    public static  int TASK_STATUS_INIT=0;//初始化
    public static  int TASK_STATUS_ALLOCATING=1;//等待分配处理
    public static  int TASK_STATUS_ALLOCATION_DEAL=2;//队列发送 等待分配处理结果
    public static  int TASK_STATUS_DEAL=3;//分配完成 等处理
    public static  int TASK_STATUS_FAIL=4;//取消
    public static  int TASK_STATUS_SUCCESS=5;//成功
    public static  int TASK_STATUS_EXCEPTION=6;//异常

    public static int CALLNACK_TYPE_INIT=0;//等待分配处理
    public static int CALLBACK_TYPE_DEAL=1;//处理回调中
}
