package javax.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Feng
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

    /**
     * 验证类型的枚举类，还可以在注解里面定义一个类
     */
    enum AuthenticationType {
        /**
         * 容器
         */
        CONTAINER,

        /**
         * 应用
         */
        APPLICATION
    }
    String name() default "";

    /**
     * @return 返回这个资源实例的类型，Class 类型
     */
    Class<?> type() default Object.class;

    /**
     * @return 返回该实例的验证类型，默认是 CONTAINER
     */
    AuthenticationType authenticationType() default AuthenticationType.CONTAINER;

    /**
     * @return 是否可分享
     */
    boolean shareable() default true;

    /**
     * @return 返回描述
     */
    String description() default "";

    /**
     * @return 映射的名称
     */
    String mappedName() default "";

    /**
     * @return 当前资源实例的名称，如果有的话
     */
    String lookup() default "";
}
