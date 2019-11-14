package javax.el;

import sun.misc.Cache;
import sun.misc.Regexp;

import java.io.File;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Feng
 * @date 2019/11/06
 */
public abstract class ExpressionFactory {

    // 该字段将表示虚拟机是否支持安全管理器
    private static final boolean IS_SECURITY_ENABLED = (null != System.getSecurityManager());

    // 一个陌生的字符串，应该是 JAR 文件里面的文件路径
    private static final String SERVICE_RESOURCE_NAME = "META-INF/services/javax.el.ExpressionFactory";

    // 资源对象名字
    private static final String PROPERTY_NAME = "javax.el.ExpressionFactory";

    // 资源文件对象
    private static final String PROPERTY_FILE;

    // 不知道是什么的静态内部类
    private static final CacheValue nullTcclFactory = new CacheValue();

    private static final ConcurrentHashMap<CacheKey, CacheValue> factoryCache = new ConcurrentHashMap<>();

     // 该静态代码块将会初始化 PROPERTY_FILE 对象变量，该变量的值为 {JAVA_HOME/el.properties}
     // 该文件一般不存在
    static {
        if (IS_SECURITY_ENABLED) {
            PROPERTY_FILE = AccessController.doPrivileged((PrivilegedAction<String>) () -> System.getProperty("java.home") + File.separator +
                    "lib" + File.separator + "el.properties");
        } else {
            PROPERTY_FILE = System.getProperty("java.home") + File.separator + "lib" +
                    File.separator + "el.properties";
        }
    }

    public static ExpressionFactory newInstance(Properties properties) {
        ExpressionFactory result = null;
        return null;
    }

    /**
     * 该类用于缓存 {@link ExpressionFactory} 所发现过的每一个类加载器的信息
     * 类加载器引用不会变成 {@code null}，因为 tccl 会处理每一个类加载器？
     */
    private static class CacheKey {
        private final int hash;
        private final WeakReference<ClassLoader> ref;

        /**
         * 构造函数
         * @param cl 传入类加载器实例
         */
        public CacheKey(ClassLoader cl) {
            hash = cl.hashCode();
            ref = new WeakReference<>(cl);
        }

        /**
         * 重写该方法和下面的 equals 方法是为了保证该类的相等与否由它自己的 ClassLoader来决定
         * @return hashCode 值
         */
        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            ClassLoader thisCl = ref.get();
            if (null == thisCl) {
                return false;
            }
            return thisCl == ((CacheKey)obj).ref.get();
        }
    }

    /**
     * 静态内部类，缓存值？
     */
    private static class CacheValue {

        // 读写锁对象
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        private String className;

        // 虚引用对象
        private WeakReference<Class<?>> ref;

        public CacheValue() {

        }

        public ReadWriteLock getLock() {
            return lock;
        }

        public String getFactoryClassName() {
            return className;
        }

        public void setFactoryClassName(String className) {
            this.className = className;
        }

        public Class<?> getFactoryClass() {
            return ref != null ? ref.get() : null;
        }

        public void setFactoryClass(Class<?> clazz) {
            ref = new WeakReference<Class<?>>(clazz);
        }
    }


}
