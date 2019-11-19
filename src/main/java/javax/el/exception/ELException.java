package javax.el.exception;

/**
 * @auther Feng
 * @date 2019/11/19
 *EL 表达式解析时，会产生的异常
 * 类构造的方法没什么特殊的
 **/
public class ELException extends RuntimeException {
    private static final long serialVersionUID = -6228042809457459161L;

    public ELException() {
        super();
    }

    public ELException(String message) {
        super(message);
    }

    public ELException(Throwable cause) {
        super(cause);
    }

    public ELException(String message, Throwable cause) {
        super(message, cause);
    }
}
