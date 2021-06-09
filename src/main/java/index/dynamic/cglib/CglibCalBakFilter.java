package index.dynamic.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 创建cglib过滤器
 */
public class CglibCalBakFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        switch (method.getName()) {
            case "add":
                return 0;
            case "del":
                return 1;
            case "update":
                return 2;
        }
        return 0;
    }
}
