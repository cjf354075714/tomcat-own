package javax.annotation.security;


import java.lang.annotation.*;

/**
 * @author Feng
 * 描述规则注解
 */
@Documented
// 注解在类，接口上
@Target(ElementType.TYPE)
// 注解会在运行时存在
@Retention(RetentionPolicy.RUNTIME)
public @interface DeclareRoles {
    String[] value();
}
