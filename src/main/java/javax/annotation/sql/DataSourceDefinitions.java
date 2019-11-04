package javax.annotation.sql;

/**
 * @author Feng
 * DataSourceDefinition 注解的复数形式
 */
public @interface DataSourceDefinitions {
    DataSourceDefinition[] value();
}
