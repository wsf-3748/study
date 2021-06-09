package index.mapper;

import index.ApplicationTest;
import index.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("username", "18875856803");
        List<User> bean = userMapper.selectUserInfo(user);
        System.out.println("长度 = " + bean.size());
        for (User b : bean) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void test2() {
        User user = new User();
        List<User> bean = userMapper.selectUserInfo2(user);
        System.out.println("长度 = " + bean.size());
        for (User b : bean) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void test3() {
        User user = new User();
        List<User> bean = userMapper.selectUserInfo3(user);
        System.out.println("长度 = " + bean.size());
        for (User b : bean) {
            System.out.println(b.toString());
        }
    }
}
