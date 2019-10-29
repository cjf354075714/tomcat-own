package org.apache.catalina.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Feng
 * @date 2019/10/29
 *
 * 类加载工厂，还包含一些路径等的相关表达式
 */
public final class ClassLoaderFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderFactory.class);

    /**
     * 根据压缩文件目录和未压缩文件目录创建一个 classloader
     * 中间通过 URL 类，来创建 URLClassloader
     * @param unpacked 未压缩文件目录数组
     * @param packed 压缩文件目录数组
     * @param parent 父加载器
     * @return 加载器
     * @throws Exception 异常
     */
    public static ClassLoader createClassLoader(File[] unpacked, File[] packed, final ClassLoader parent) throws Exception {
        return null;
    }


    /**
     * 存放 JAR 文件的仓库枚举类
     */
    public enum RepositoryType {
        /**
         * DIR 表示一个文件夹
         */
        DIR,

        /**
         *
         */
        GLOB,

        /**
         * 表示一个 jar 文件
         */
        JAR,

        /**
         * 表示一个文件的地址
         */
        URL
    }

    /**
     * 类加载器的静态内部类，是 public 的，可见一个类可以有多个内部类
     */
    public static class Repository {
        private final String location;
        private final RepositoryType type;

        public Repository(String location, RepositoryType type) {
            this.location = location;
            this.type = type;
        }

        public String getLocation() {
            return location;
        }

        public RepositoryType getType() {
            return type;
        }
    }
}
