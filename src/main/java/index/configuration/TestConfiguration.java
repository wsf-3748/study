package index.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    public TestConfiguration() {
        System.out.println("TestConfiguration初始化...");
    }
}
