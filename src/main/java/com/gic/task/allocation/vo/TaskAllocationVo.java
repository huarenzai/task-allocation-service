package com.gic.task.allocation.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/16.
 */
public class TaskAllocationVo implements Serializable {
    private String taskAllocationId;
    private int taskType;//任务类型
    private String operationUserId;//任务执行人id
    private String enterpriseId;//企业id
    private String taskMqKey;//任务队列key
    private int taskFailNum;//单挑任务失败条数
    private int taskExecNum;//任务已经执行条数
    private int taskTotal;//任务总数
    private String taskSignKey;//任务标识
    private int taskStatus;//任务状态  任务状态：0-初始化，1-等待分配中，2-分配处理中，3-任务处理中，4-任务完成，5-任务取消，6，任务异常
    private String createTime;//创建时间
    private String dealTime;//处理时间
    private String speed;//执行速度
    private String reason;
//    private int taskRank;//任务优先级


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTaskAllocationId() {
        return taskAllocationId;
    }

    public void setTaskAllocationId(String taskAllocationId) {
        this.taskAllocationId = taskAllocationId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTaskMqKey() {
        return taskMqKey;
    }

    public void setTaskMqKey(String taskMqKey) {
        this.taskMqKey = taskMqKey;
    }

    public int getTaskFailNum() {
        return taskFailNum;
    }

    public void setTaskFailNum(int taskFailNum) {
        this.taskFailNum = taskFailNum;
    }

    public int getTaskExecNum() {
        return taskExecNum;
    }

    public void setTaskExecNum(int taskExecNum) {
        this.taskExecNum = taskExecNum;
    }

    public int getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(int taskTotal) {
        this.taskTotal = taskTotal;
    }

    public String getTaskSignKey() {
        return taskSignKey;
    }

    public void setTaskSignKey(String taskSignKey) {
        this.taskSignKey = taskSignKey;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
