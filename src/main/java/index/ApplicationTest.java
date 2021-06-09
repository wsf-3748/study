package index;

import index.config.PersonConfig;
import index.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = {"index.mapper"}, annotationClass = Mapper.class)
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class);
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationTest.class);
        // 返回IOC容器
//        ConfigurableApplicationContext context = SpringApplication.run(ApplicationTest.class);
//
//        // 查看容器里的组件
//        String[] names = context.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//
//        // 单实例
//        Person person1 = context.getBean("person1",Person.class);
//        Person person2 = context.getBean("LiSiPerson", Person.class);
//        System.out.println(person1.toString());
//        System.out.println(person2.toString());
//
//        PersonConfig personConfig = context.getBean(PersonConfig.class);
//        Person newPerson3 = personConfig.person1();
//        Person newPerson4 = personConfig.person1();
//        System.out.println("newPerson3 == newPerson4" + (newPerson3 == newPerson4));
//
//        PersonConfig personConfig2 = new PersonConfig();
//        Person newPerson1 = personConfig2.person1();
//        Person newPerson2 = personConfig2.person1();
//        System.out.println("newPerson1 == newPerson2" + (newPerson1 == newPerson2));
    }
}
