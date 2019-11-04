package javax.annotation.security;

import java.lang.annotation.*;

/**
 * @author Feng
 * "允许所有"的注解
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermitAll {
}
