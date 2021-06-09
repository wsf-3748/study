package index.aop;

import java.lang.annotation.*;

/**
 * 使用@Target、@Retention、@Documented自定义一个注解
 * 创建一个切面类，切点设置为拦截所有标注PermissionsAnnotation的方法，截取到接口的参数，进行简单的权限校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionsAnnotation {
}
