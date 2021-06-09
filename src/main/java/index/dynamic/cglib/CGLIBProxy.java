package index.dynamic.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CGLIBProxy implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    public void before() {
        System.err.println("CglibProxy before");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        System.out.println("++++++ 正在执行intercept方法 ++++++");
        System.out.println("methodProxy.getSuperName()是" + methodProxy.getSuperName());
        System.out.println("method.getName()是" + method.getName());
        System.out.println("Object[]是" + Arrays.toString(objects));
        Object result = methodProxy.invokeSuper(o, objects);
        System.err.println("CGLIBProxy end ---------------------------");
        return result;
    }
}
