package javax.annotation.security;

import java.lang.annotation.*;

/**
 * @author Feng
 * 运行的规则？
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunAs {
}
