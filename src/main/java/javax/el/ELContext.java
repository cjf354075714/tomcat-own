package javax.el;

import java.util.Locale;
import java.util.Map;

/**
 * @author Feng
 * @date 2019/11/04
 *
 * EL 表达式容器的抽象类
 */
public abstract class ELContext {

    // 该类代表了一个区域，一个文化，宗教、地理等来区别
    private Locale locale;

    // 键是类，值是对象
    private Map<Class<?>, Object> map;

    // 是否解析的标记位
    private boolean resolved;


}
