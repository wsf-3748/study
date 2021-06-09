package index.config;

import index.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public Person person1() {
        return new Person("张三", 18);
    }

    @Bean("LiSiPerson")
    public Person person2() {
        return new Person("李四", 28);
    }
}
