package javax.annotation;

import java.lang.annotation.*;

/**
 * @author Feng
 * 用于注解到方法上，该方法就会在 tomcat 启动的时候运行了
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostConstruct {
}
