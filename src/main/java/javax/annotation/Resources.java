package javax.annotation;

import java.lang.annotation.*;

/**
 * @author Feng
 * 代表资源的数组注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resources {
    Resource[] value();
}
