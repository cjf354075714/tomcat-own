package javax.el.elcontextimpl;

import javax.el.ELContext;
import javax.el.ELResolver;
import java.beans.FeatureDescriptor;
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
        return null;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {

    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        // 先确定 context 是否是 null
        Objects.requireNonNull(context);
        // 获取 base 的类对象，就可以判断 base 是否是数组
        if (null != base && base.getClass().isArray()) {
            context.setPropertyResolved(base, property);
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
}
