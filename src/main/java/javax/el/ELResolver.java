package javax.el;

import javax.el.exception.PropertyNotFoundException;
import java.beans.FeatureDescriptor;
import java.util.Iterator;

/**
 * @auther Feng
 * @date 2019/11/19
 *
 * EL 表达式的抽象解析器
 **/
public abstract class ELResolver {

    public static final String TYPE = "type";

    // 是否在设计的时候解析
    public static final String RESOLVABLE_AT_DESIGN_TIME = "resolvableAtDesignTime";

    /**
     * @param context 当前“评价”的 EL 表达式容器
     * @param base 数组对象
     * @param property 数组索引值
     * @return 提供的 property 的值
     * @throws NullPointerException 如果提供的 context 是 null 的话，将会报出空指针异常
     * @throws PropertyNotFoundException 如果 base/property 组合
     * 并不能被 resolver 所解析，将报该异常
     */
    public abstract Object getValue(ELContext context,
                                    Object base, Object property);

    /**
     * 该方法，就是将 property 转化为一个整性索引，然后将 value 设置到 base 这个数组中
     * @param context ELContext
     * @param base 数组对象
     * @param property 数组索引
     * @param value 数组中的值
     */
    public abstract void setValue(ELContext context, Object base, Object property, Object value);

    /**
     * 获取 base 这个数组的基本构成对象的类型，比如 int[] 数组，将返回 Integer 这个对象
     * @param context 当前的 ELContext
     * @param base 基础类
     * @param property 属性对象
     * @return 类型
     */
    public abstract Class<?> getType(ELContext context, Object base, Object property);

    /**
     * 返回确定的属性是否可读的标志
     * @param context ELContext
     * @param base base
     * @param property property
     * @return boolean
     */
    public abstract boolean isReadOnly(ELContext context, Object base, Object property);

    /**
     * Iterator 是一个迭代器
     * FeatureDescriptor 是一个特征描述器，它是
     * @see java.beans.PropertyDescriptor,java.beans.MethodDescriptor,java.beans.EventSetDescriptor
     * 三个描述器的公共基础类
     * @param context 容器
     * @param base base
     * @return 迭代器
     */
    public abstract Iterator<FeatureDescriptor> getFeatureDescriptors(
            ELContext context, Object base
    );

    /**
     * 获取公共属性的类别
     * @param context 容器
     * @param base base
     * @return 类别
     */
    public abstract Class<?> getCommonPropertyType(ELContext context, Object base);

    /**
     * 通过给定的对象，反射它的一个方法，默认实现返回 null
     * @param context EL 容器
     * @param base 给定的对象
     * @param method 需要反射的方法
     * @param paramType 方法调用的参数类型
     * @param params 实际上的参数
     * @return null
     */
    public Object invoke(ELContext context, Object base,
                         Object method, Class<?>[] paramType, Object[] params) {
        return null;
    }

    /**
     * 将传入的 obj 转化成指定的类型，type
     * @param context 容器
     * @param obj 需要转化的类
     * @param type 将被转化的类型
     * @return 转化完成的对象
     */
    public Object convertToType(ELContext context, Object obj, Class<?> type) {
        context.setPropertyResolved(false);
        return null;
    }
}
