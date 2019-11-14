package javax.el;

import sun.util.resources.cldr.en.TimeZoneNames_en_PK;

import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Feng
 * @date 2019/11/05
 *
 * 该类没有使用 public 修饰，代表只能该包下的类才能使用这个类
 */
class Util {
    // 一个空的类对象的数组
    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];
    // 一个空的实例数组
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    /**
     * 仅处理线程死亡错误和虚拟机错误
     * @param t 异常错误的父类
     */
    static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
        // 其他的异常将会被忽视
    }

    static String message(ELContext context, String name, Object... props) {
        Locale locale = null;
        if (null != context) {
            locale = context.getLocale();
        }
        if (null == locale) {
            locale = Locale.getDefault();
            if (null == locale) {
                return "";
            }
        }
        // 获取本地语言环境支持的 properties 文件
        // 该资源文件所在路径是 javax.el.LocalStrings
        // 也证是因为本地环境是 zh_CN，所以会获取到 LocalStrings_zh_CN.properties
        ResourceBundle bundle = ResourceBundle.getBundle(
                "javax.el.LocalStrings", locale
        );
        try {
            String template = bundle.getString(name);
            if (props != null) {
                // template 对象是一种模板：My name is [0].
                // 而 props 就是一个数组，用来填补 [0]，[1]... 等空缺
                // 填补好空缺之后，就直接返回
                template = MessageFormat.format(template, props);
            }
            return template;
        } catch (MissingResourceException e) {
            return "在环境：" + locale.getDisplayName() + "下，找不到键为 "+ name +" 的资源值";
        }
    }

    // 静态内部类
    private static class CacheValue {
        // 读写锁对象，有什么用啊
        private final ReadWriteLock lock = new ReentrantReadWriteLock();


    }
}
