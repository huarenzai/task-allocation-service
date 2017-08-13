package com.gic.task.allocation.util;

import java.util.UUID;

/**
 * Created by Administrator on 2017/8/12.
 */
public class CommonUtil {
    public static String getUUIDRandom(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
}
