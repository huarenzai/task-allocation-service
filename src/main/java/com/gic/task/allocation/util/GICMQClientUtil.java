package com.gic.task.allocation.util;

import com.gic.mq.sdk.GicMQClient;
import com.gic.mq.sdk.exception.RouterException;
import org.apache.log4j.Logger;

public class GICMQClientUtil {

	private final static Logger logger = Logger
			.getLogger(GICMQClientUtil.class);
	private static GicMQClient gicMQClient = null;

	public static GicMQClient getClientInstance() {
		if (null == gicMQClient) {
			synchronized (GICMQClientUtil.class) {
				if (null == gicMQClient) {
					try {
						gicMQClient = new GicMQClient();
					} catch (RouterException e) {
						logger.error("创建 GicMQClient失败", e);
						gicMQClient = null;
					}
				}
			}
		}
		return gicMQClient;
	}

}
