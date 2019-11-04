package javax.el;

/**
 * @author Feng
 * @date 2019/11/04
 *
 * EL 表达式解析器的抽象类
 */
public abstract class ELResolver {
    public static final String TYPE = "type";

    // 在设计阶段，是否可以解析
    public static final String RESOLVABLE_AT_DESIGN_TIME = "resolvableAtDesignTime";

    public abstract Object getValue();
}
