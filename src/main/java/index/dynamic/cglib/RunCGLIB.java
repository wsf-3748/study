package index.dynamic.cglib;

import index.dynamic.jdk.UserService;
import index.dynamic.jdk.UserServiceImpl;

/**
 * 测试CGLIB动态代理
 */
public class RunCGLIB {

    public static void main(String[] args) {
        CGLIBProxy cglibProxy = new CGLIBProxy();
        UserService userService = (UserService) cglibProxy.getInstance(new UserServiceImpl());
        System.out.println(userService.getName("dog"));
        System.out.println(userService.getAge(2));
    }

}
