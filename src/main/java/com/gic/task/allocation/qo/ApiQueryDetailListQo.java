package com.gic.task.allocation.qo;

import com.dexcoder.commons.pager.Pageable;

/**
 * Created by Administrator on 2017/8/16.
 */
public class ApiQueryDetailListQo extends Pageable {
    private String taskAllocationId;
    private int status=-1;

    public String getTaskAllocationId() {
        return taskAllocationId;
    }

    public void setTaskAllocationId(String taskAllocationId) {
        this.taskAllocationId = taskAllocationId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
