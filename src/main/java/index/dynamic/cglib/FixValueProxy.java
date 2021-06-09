package index.dynamic.cglib;

import org.springframework.cglib.proxy.FixedValue;

/**
 * 创建固定值过滤
 */
public class FixValueProxy implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.err.println("FixValueProxy ....返回固定信息");
        return null;
    }
}
