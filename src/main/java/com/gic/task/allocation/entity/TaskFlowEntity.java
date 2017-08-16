package com.gic.task.allocation.entity;

import com.dexcoder.dal.annotation.Table;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/12.
 */
@Table(name = "gic_task_flow",pkColumn = "id")
public class TaskFlowEntity {
    private int id;
    private String taskFlowId;
    private String taskAllocationId;
    private String reason;
    private Date createTime;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaskFlowId() {
        return taskFlowId;
    }

    public void setTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String getTaskAllocationId() {
        return taskAllocationId;
    }

    public void setTaskAllocationId(String taskAllocationId) {
        this.taskAllocationId = taskAllocationId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
