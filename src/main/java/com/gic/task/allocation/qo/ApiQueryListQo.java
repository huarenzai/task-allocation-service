package com.gic.task.allocation.qo;

import com.dexcoder.commons.pager.Pageable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/13.
 */
public class ApiQueryListQo extends Pageable {
    private String taskAllocationId;
    private int taskType=-1;
    private int taskStatus=-1;


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

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }
}
