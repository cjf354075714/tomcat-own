package javax.el;

/**
 * @auther Feng
 * @date 2019/11/19
 *
 * 评估表达式的监听器
 **/
public abstract class EvaluationListener {

    /**
     * 在评估之前的执行逻辑
     * @param context 容器
     * @param expression 表达式
     */
    public void beforeEvaluation(ELContext context, String expression) {
        // do something
    }

    /**
     * 在评估表达式之后触发
     * @param context 表达式所拥有的容器
     * @param expression 表达式
     */
    public void afterEvaluation(ELContext context, String expression) {
        // do something
    }

    /**
     * 在一个属性已经被解析过后，触发
     * @param context 容器
     * @param base property 中的对象
     * @param property 属性
     */
    public void propertyResolved(ELContext context, Object base, Object property) {
        // do something
    }
}
