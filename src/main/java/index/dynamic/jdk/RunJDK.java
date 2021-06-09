package index.dynamic.jdk;

/**
 * 测试JDK动态代理
 */
public class RunJDK {

    public static void main(String[] args) {
        MyInvocationHandler proxy = new MyInvocationHandler();

        UserService userServiceProxy = (UserService) proxy.bind(new UserServiceImpl());

        System.out.println(userServiceProxy.getName("cat"));
        System.out.println(userServiceProxy.getAge(1));
    }
}
