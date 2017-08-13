package com.gic.task.allocation.entity;

import com.dexcoder.dal.annotation.Table;

/**
 * Created by Administrator on 2017/8/12.
 */
@Table(name = "test")
public class TestEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
