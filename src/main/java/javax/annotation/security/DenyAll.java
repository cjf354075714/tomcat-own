package javax.annotation.security;

import java.lang.annotation.*;


/**
 * @author Feng
 * “拒绝所有”的注解
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DenyAll {
}
