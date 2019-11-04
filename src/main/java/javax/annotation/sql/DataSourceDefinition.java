package javax.annotation.sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Feng
 * 代表着数据池的注解，用于定义数据库连接池
 */
// 既然代表着数据库连接池，那肯定是在类上注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceDefinition {
    // 数据库连接池的类名称 DruidDataSource
    String className();

    // 数据库连接池的名称，这个随便取
    String name();

    // 连接池的描述字符串
    String description() default "";

    // 数据库连接的 URL jdbc:localhost:3306....
    String url() default "";

    // 应该是数据库连接的用户名，比如 root
    String user() default "";

    // 数据库连接密码
    String password() default "";

    // mysql oracle 等
    String databaseName() default "";

    // 连接端口 3306
    int portNumber() default -1;

    // 默认的主机名
    String serverName() default "localhost";

    // 默认隔离级别
    int isolationLevel() default -1;

    // 是否开启事务
    boolean transactional() default true;

    // 初始化连接池的大小
    int initialPoolSize() default -1;

    // 最大的连接池大小
    int maxPoolSize() default -1;

    // 最小的连接池大小
    int minPoolSize() default -1;

    // 连接的最大空闲时间
    int maxIdleTime() default -1;

    // 最大的会话连接数
    int maxStatements() default -1;

    // 配置信息 应该是是否使用时区等
    String[] properties() default {};

    // 登录超时时间
    int loginTimeout() default 0;
}
