package com.sports.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

// 读取配置文件
public class PropertiesUtil {
    // 声明logger
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;

    // 在Tomcat启动的时候就要读取到这个配置，所以将配置内容放到静态块中，在类加载的时候执行且仅执行一次
    static {
        // 定义变量，保存文件名
        String fileName = "application.yml";
        props = new Properties();
        try {
            // 读取配置文件
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常", e);
        }
    }

    // 通过properties文件中的key来获取value
    public static String getProperty(String key) {
        // 传入key，获取value
        String value = props.getProperty(key.trim());
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    // 重载上面的方法，如果通过key传过来的value是空的那么就将defaultValue 返回
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (value == null) {
            // 没有查到相应的value，将defaultValue返回
            value = defaultValue;
        }
        return value.trim();
    }


}