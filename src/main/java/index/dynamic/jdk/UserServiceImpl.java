package index.dynamic.jdk;

public class UserServiceImpl implements UserService {

    public String getName(String name) {
        System.out.println("------getName------");
        return name;
    }

    public Integer getAge(int age) {
        System.out.println("------getAge------");
        return age;
    }
}
