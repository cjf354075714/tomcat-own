package javax.el;

/**
 * @author Feng
 * @date 2019/11/05
 *
 * 表示表达式解析过程中会发生的一些异常
 */
public class ELException extends RuntimeException{
    private static final long serialVersionUID = -6228042809457459161L;

    /**
     * 默认构造方法
     */
    public ELException() {
        super();
    }

    /**
     * 根据一个字符串创建一个 ELException 异常
     * @param message 字符串
     */
    public ELException(String message) {
        super(message);
    }

    /**
     * 根据给定 cause 对象创建 ELException 异常
     * @param cause Throwable 实例
     */
    public ELException(Throwable cause) {
        super(cause);
    }

    /**
     * 根据字符串和 cause 创建 ELException 对象
     * @param message 字符串
     * @param cause Throwable 实例
     */
    public ELException(String message, Throwable cause) {
        super(message, cause);
    }
}
