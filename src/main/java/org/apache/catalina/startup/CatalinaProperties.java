package org.apache.catalina.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Feng
 * @date 2019/10/29
 *
 * 该类是一个读取 home/conf/catalina.properties 的工具类，并将该文件下的属性存在 System.properties 中
 */
public class CatalinaProperties {
    /**
     * 日志打印器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalinaProperties.class);

    /**
     * JAVA 自带的 Properties 类
     */
    private static Properties properties = null;

    static {
        loadProperties();
    }

    /**
     * 调用 BootStrap 类的方法，获取 home 目录，并且创建 Properties 加载 home/conf/catalina.properties 文件中的信息
     */
    private static void loadProperties() {
        InputStream is = null;
        try {
            // 此时就获取了运行目录下的 home 文件夹
            File homeFile = new File(BootStrap.getCatalinaBase());
            File conf = new File(homeFile, "conf");
            File catalinaProps = new File(conf, "catalina.properties");
            is = new FileInputStream(catalinaProps);
        } catch (Throwable t) {
            handleThrowable(t);
        }

        if (null != is) {
            properties = new Properties();
            try {
                properties.load(is);
            } catch (Throwable t) {
                handleThrowable(t);
                LOGGER.warn("获取 catalina 配置文件出错 {0}", t);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.warn("关闭 catalina 配置文件读取流出错 {0}", e);
                }
            }
        }
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = properties.getProperty(name);
            if (null != value) {
                System.setProperty(name, value);
            }
        }
        // 为什么不直接调用 System.setProperties()？因为配置文件可能会有不健全的键值对
    }

    /**
     * 根据 Key 获取键值对的值
     * @param name 键
     * @return 值
     */
    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

    /**
     * 处理特殊错误的函数，Throwable 是所有 Error 和 Exception 的父类
     * 这里如果该错误是线程死亡错误和虚拟机错误，就向下转型，并抛出
     * 为什么参数是 Throwable，应该是只能捕获到 Exception吧
     * @param t 抛出的错误
     */
    private static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
        // 其他的错误就算了吧
    }
}
