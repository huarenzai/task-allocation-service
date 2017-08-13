package com.gic.task.allocation.common.init;

import com.gic.mq.sdk.MQConfig;
import com.gic.task.allocation.common.GlobalInfoParams;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Properties;


public class CustomizedPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        HashMap<String, String> map = new HashMap<String, String>();

        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            map.put(keyStr, value);
        }
        GlobalInfoParams.ctxPropertiesMap=map;
        MQConfig.initCMQ(map.get("cmq.secretId"), map.get("cmq.secretKey"));
        MQConfig.initZooKeeper(map.get("mq.zookeeper.host"), map.get("mq.zookeeper.port"));
    }

}