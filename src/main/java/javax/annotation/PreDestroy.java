package javax.annotation;

import java.lang.annotation.*;

/**
 * @author Feng
 * 在摧毁之前，调用的方法
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreDestroy {
}
