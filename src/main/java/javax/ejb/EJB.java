package javax.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Feng
 * EJB 对象注解
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EJB {
    String name() default "";
    String description() default "";

    /**
     * @return 当前 bean 的类实例
     */
    Class beanInterface() default java.lang.Object.class;

    String beanName() default "";

    String mappedName() default "";

    String lookup() default "";
}
