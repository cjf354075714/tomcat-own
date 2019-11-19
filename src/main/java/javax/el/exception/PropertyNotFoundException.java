package javax.el.exception;

/**
 * @auther Feng
 * @date 2019/11/19
 * 属性没有找到的异常，继承
 * @see ELException
 **/
public class PropertyNotFoundException extends ELException {
    private static final long serialVersionUID = -3799200961303506745L;

    public PropertyNotFoundException() {
        super();
    }

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(Throwable cause) {
        super(cause);
    }

    public PropertyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
