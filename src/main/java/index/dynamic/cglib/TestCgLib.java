package index.dynamic.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class TestCgLib {

    /*
    总结：有输出结果可以看出，
    add动态代理进行了功能增强，
    del 原样输出，
    update输出是固定的值，并不会调用目标方法，
    由此我们可以想象spring aop 就是这样的原理，如果方法有增强标识则进行功能增强。（回掉数组与我们拦截器返回值对应）。
     */
    public static void main(String[] args) {
        //创建字节码增强类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetClassCglib.class);
        enhancer.setCallbackFilter(new CglibCalBakFilter());

        Callback c1 = new CGLIBProxy();
        Callback c2 = NoOp.INSTANCE;
        Callback c3 = new FixValueProxy();
        Callback[] callbacks = new Callback[] {c1,c2,c3};
        enhancer.setCallbacks(callbacks);

        TargetClassCglib cglib = (TargetClassCglib)enhancer.create();
        cglib.add();
        cglib.del();
        cglib.update();
    }
}
