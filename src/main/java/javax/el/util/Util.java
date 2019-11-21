package javax.el.util;

import javax.el.ELContext;
import javax.swing.text.html.parser.TagElement;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @auther Feng
 * @date 2019/11/19
 **/
public class Util {
    /**
     * 处理线程死亡和虚拟机错误的方法
     * 其他的就自动忽略
     * @param t 抛出的异常
     */
    public static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
    }

    /**
     * 根据传递的参数，获取当前区域下能够识别的资源文件夹，并通过拼接的方式，完成异常的提示
     * @param context EL 容器
     * @param name 传入的获取资源文件夹的对应的键
     * @param props 待拼接的属性
     * @return 拼接好的异常信息
     */
    public static String message(ELContext context,
                               String name, Object... props) {
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
        ResourceBundle bundle = ResourceBundle.getBundle("javax.el.LocalStrings", locale);
        try {
            String template = bundle.getString(name);
            if (null != props) {
                template = MessageFormat.format(template, props);
            }
            return template;
        } catch (MissingResourceException e) {
            return "在环境" + locale.getDisplayName() + "下，找不到键为：'" + name + "'的值";
        }
    }


    /**
     * 返回 target 是不是 src 的父类，注意，这是通过类的方式去判断的
     * @param src 是否是子类
     * @param target 是否是父类
     * @return 是否是
     *
     */
    public static boolean isAssignableFrom(Class<?> src, Class<?> target) {
        if (null == src) {
            return true;
        }
        Class<?> targetClass = null;
        // isPrimitive() 表示是不是基础的数据类型：byte、short、int、long、double、float等
        // 先判断是否是基本类型，因为，基本类型的 Class 是不能够与 Object、Number 这些类对象
        // 进行继承判断的，所以需要事先转化为对象类型，就是装箱
        if (target.isPrimitive()) {
            if (target == Boolean.TYPE) {
                targetClass = Boolean.class;
            } else if (target == Character.class) {
                targetClass = Character.class;
            } else if (target == Byte.TYPE) {
                targetClass = Byte.class;
            } else if (target == Short.TYPE) {
                targetClass = Short.class;
            } else if (target == Integer.TYPE) {
                targetClass = Integer.class;
            } else if (target == Long.TYPE) {
                targetClass = Long.class;
            } else if (target == Float.TYPE) {
                targetClass = Float.class;
            } else {
                targetClass = Double.class;
            }
        } else {
            targetClass = target;
        }
        return targetClass.isAssignableFrom(src);
    }
}
