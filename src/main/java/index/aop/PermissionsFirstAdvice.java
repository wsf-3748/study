package index.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 创建第一个AOP切面类，，只要在类上加个 @Aspect 注解即可。
 * @Aspect 注解用来描述一个切面类，定义切面类的时候需要打上这个注解。
 * @Component 注解将该类交给 Spring 来管理
 */
@Aspect
@Component
public class PermissionsFirstAdvice {
    // 定义一个切点：所有被PermissionAnnotation注解修饰的方法会织入advice
    @Pointcut("@annotation(index.aop.PermissionsAnnotation)")
    private void permissionCheck() {
        System.out.println("permissionCheck()");
    }

    // 环绕通知(@Around)： 动态代理，手动推进目标方法的执行。
    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());

        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        System.out.println("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
        System.out.println("name1->>>>>>>>>>>>>>>>>>>>>>" + name);

        // id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return joinPoint.proceed();
    }

}
