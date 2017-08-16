package com.gic.task.allocation.vo;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/16.
 */
public class TaskFlowVo implements Serializable {
    private String taskFlowId;
    private String taskAllocationId;
    private String reason;
    private String createTime;
    private int status;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
