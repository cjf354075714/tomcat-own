package javax.annotation;


import java.lang.annotation.*;

/**
 * @author Feng
 * 生产专用的注解，不知道有啥用
 */
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD,
ElementType.PACKAGE, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Generated {
    String[] value();
    // 日期表示字符串
    String date() default "";
    // 评论
    String comments() default "";
}
