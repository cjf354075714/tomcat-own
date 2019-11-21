package javax.el.elcontextimpl;

import javax.el.ELContext;
import javax.el.ELResolver;
import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther Feng
 * @date 2019/11/21
 **/
public class BeanELResolver extends ELResolver {

    private static final int CACHE_SIZE;
    private static final String CACHE_SIZE_PROP = "org.apache.el.BeanELResolver.CACHE_SIZE";

     // 静态代码块，是要获取 CACHE_SIZE_PROP 中的值
    static {
        String cacheSizeStr;
        if (null == System.getSecurityManager()) {
            // getProperty 方法有两个参数，第一个参数是键，第二个参数是如果找不到这个键值对，就返回这个数值
            cacheSizeStr = System.getProperty(CACHE_SIZE_PROP, "1000");
        } else {
            cacheSizeStr = AccessController.doPrivileged(
                    (PrivilegedAction<String>) () -> System.getProperty(CACHE_SIZE_PROP, "1000")
            );
        }
        CACHE_SIZE = Integer.parseInt(cacheSizeStr);
    }

    private final boolean readOnly;

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {

    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return false;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return null;
    }

    /**
     * 该类里面有两个 Map，一个是同步 Map，一个是 Weak Map
     * 逻辑就是单纯的获取值和写入值
     * 获取值先从同步 Map 中获取，写入值需要先判断，如果同步 Map 已经满了，就需要将同步 Map 中的数据
     * 全部写入到 Weak 中，然后写入同步 Map
     * @param <K>
     * @param <V>
     */
    private static final class ConcurrentCache<K, V> {
        private final int size;
        private final Map<K ,V> eden;
        private final Map<K, V> longTerm;

        public ConcurrentCache(int size) {
            this.size = size;
            this.eden = new ConcurrentHashMap<>(size);
            this.longTerm = new WeakHashMap<>(size);
        }

        public V get(K key) {
            V value = this.eden.get(key);
            if (null == value) {
                synchronized (longTerm) {
                    value = this.longTerm.get(key);
                }
                if (null != value) {
                    this.eden.put(key, value);
                }
            }
            return value;
        }

        public void put(K key, V value) {
            if (this.eden.size() >= this.size) {
                synchronized (longTerm) {
                    this.longTerm.putAll(this.eden);
                }
                this.eden.clear();
            }
            this.eden.put(key, value);
        }
    }

    static final class BeanProperty {
        private final Class<?> type;
        private final Class<?> owner;
        private final PropertyDescriptor descriptor;
        private Method read;
        private Method write;

    }
}
