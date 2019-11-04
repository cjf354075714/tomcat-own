package javax.annotation;

import java.lang.annotation.*;

/**
 * @author Feng
 * 优先级注解，肯定是注解在类上面
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {
}
