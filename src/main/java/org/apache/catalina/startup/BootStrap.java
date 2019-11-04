package org.apache.catalina.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hello world!
 * @author Feng
 */
public class BootStrap {

    /**
     * Slf4j 日志门面类，底层使用桥接器连接 Log4j2
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BootStrap.class);

    public static void main( String[] args ) throws IOException {
        LOGGER.error("错误");
    }
}
