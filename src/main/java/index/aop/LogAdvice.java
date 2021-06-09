package index.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP切面类
 * 通知方法：
 * 前置通知(@Before)： 在目标方法运行之前执行。
 * 后置通知(@After)： 在目标方法运行结束之后执行，不管正常结束还是异常结束，都会执行。
 * 返回通知(@AfterReturn)： 在目标方法正常放回之后执行。
 * 异常通知(@AfterThrowing)： 在目标方法出现异常以后执行。
 * 环绕通知(@Around)： 动态代理，手动推进目标方法的执行。
 */
@Aspect
@Component
public class LogAdvice {


//     抽取公共的切入点表达式
//     @Pointcut("execution(* index.aop.*.*(..))")
//     定义一个切点：所有被GetMapping注解修饰的方法会织入advice
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logging() {}

    // 前置通知(@Before)： 在目标方法运行之前执行
    @Before(value = "logging()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() +
                "运行，参数列表是: {" + Arrays.asList(joinPoint.getArgs()) + "}");
    }

    // 后置通知(@After)： 在目标方法运行结束之后执行，不管正常结束还是异常结束，都会执行
    @After(value = "logging()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束...");
    }

    // 返回通知(@AfterReturn)： 在目标方法正常放回之后执行
    @AfterReturning(value = "logging()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常结束，结果是: {" + result + "}");
    }

    // 异常通知(@AfterThrowing)： 在目标方法出现异常以后执行
    @AfterThrowing(value = "logging()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println(joinPoint.getSignature().getName() + "异常，异常信息: {" + e.getMessage() + "}");
    }
}
