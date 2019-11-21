package javax.el.elcontextimpl;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.exception.PropertyNotFoundException;
import javax.el.exception.PropertyNotWritableException;
import javax.el.util.Util;
import java.beans.FeatureDescriptor;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Objects;

/**
 * @auther Feng
 * @date 2019/11/19
 *
 * ELResolver 的继承类
 **/
public class ArrayELResolver extends ELResolver {

    private final boolean readOnly;

    public ArrayELResolver() {
        this.readOnly = false;
    }

    public ArrayELResolver(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        Objects.requireNonNull(context);
        if (null != base && base.getClass().isArray()) {
            int idx = coerce(property);
            if (idx < 0 || idx >= Array.getLength(base)) {
                return null;
            }
            return Array.get(base, idx);
        }
        return null;
    }

    @Override
    public void setValue(ELContext context,
                         Object base, Object property, Object value) {
        Objects.requireNonNull(context);
        if (null != base && base.getClass().isArray()) {
            context.setPropertyResolved(base, property);
            if (this.readOnly) {
                throw new PropertyNotWritableException(Util.message(context, "解析器不可写入",
                        base.getClass().getName()));
            }
            int idx = coerce(property);
            checkBounds(base, idx);
            if (null != value && !Util.isAssignableFrom(value.getClass(), base.getClass().getComponentType())) {
                throw new ClassCastException(Util.message(context, "objectNotAssignable", value.getClass().getName(), base.getClass().getComponentType().getName()));
            }
            Array.set(base, idx, value);
        }

    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        // 先确定 context 是否是 null
        Objects.requireNonNull(context);
        // 获取 base 的类对象，就可以判断 base 是否是数组
        if (null != base && base.getClass().isArray()) {
            context.setPropertyResolved(base, property);
            try{
                int idx = coerce(property);
                checkBounds(base, idx);
            } catch (IllegalArgumentException e) {
                // ignore
            }
            return base.getClass().getComponentType();
        }
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
     * 检查 idx 是否在 base 中
     * @param base base
     * @param idx idx
     */
    private static final void checkBounds(Object base, int idx) {
        if (idx < 0 || idx >= Array.getLength(base)) {
            throw new PropertyNotFoundException(
                    new ArrayIndexOutOfBoundsException(idx).getMessage()
            );
        }
    }

    /**
     * 获取 property 中的值，前提是它是 string boolean number character
     * @word coerce 胁迫
     * @param property 传入对象
     * @return 对象将要转化的值
     */
    private static final int coerce(Object property) {
        if (property instanceof Number) {
            return ((Number) property).intValue();
        }
        if (property instanceof Character) {
            return (Character) property;
        }
        if (property instanceof Boolean) {
            return (int) property;
        }
        if (property instanceof String) {
            return Integer.parseInt((String) property);
        }
        throw new IllegalArgumentException(property != null ? property.toString() : "null");
    }
}
