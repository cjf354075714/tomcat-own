package javax.el;

import javax.el.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Feng
 * @date 2019/11/19
 *
 * EL 表达式容器
 **/
public abstract class ELContext {

    // 属性是否被"解决"的标志位？
    private boolean resolved;

    // property 对象，被解析时所触发的监听器
    private List<EvaluationListener> listeners = new ArrayList<>();

    public void setPropertyResolved(boolean resolved) {
        this.resolved = resolved;
    }

    /**
     * 将给定的 property 标记为已经解析过了，并且触发监听的对象
     * @param base 在 property 中找到的 base
     * @param property 被解析的 property
     */
    public void setPropertyResolved(Object base, Object property) {
        setPropertyResolved(true);
        // 通知监听器
        notifyPropertyResolved(base, property);
    }

    /**
     * 通知监听器，property 已经被解析了
     * @param base property 中找到的对象
     * @param property 被解析的对象
     */
    public void notifyPropertyResolved(Object base, Object property) {
        listeners.forEach(
                (item) -> {
                    try {
                        item.propertyResolved(this, base, property);
                    } catch (Throwable t) {
                        Util.handleThrowable(t);
                    }
                }
        );
    }
}
