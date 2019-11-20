package javax.el.util;

import javax.el.ELContext;
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
}
