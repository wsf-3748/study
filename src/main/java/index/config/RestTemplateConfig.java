package index.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 创建一个 RestTemplate 的配置类，同时设置连接池大小、超时时间、重试机制等等
 * delete()：这个方法是在特定的 URL 上对资源执行 HTTP DELETE 操作
 * exchange()：在 URL 上执行特定的 HTTP 方法，返回包含对象的 ResponseEntity，这个对象是从响应体中映射得到的
 * execute()：在 URL 上执行特定的 HTTP 方法，返回一个从响应体映射得到的对象
 * getForEntity()：发送一个 HTTP GET 请求，返回的 ResponseEntity 包含了响应体所映射成的对象
 * getForObject()：发送一个 HTTP GET 请求，返回的请求体将映射为一个对象
 * postForEntity()：POST 数据到一个 URL，返回包含一个对象的 ResponseEntity，这个对象是从响应体中映射得到的
 * postForObject()：POST 数据到一个 URL，返回根据响应体匹配形成的对象
 * headForHeaders()：发送 HTTP HEAD 请求，返回包含特定资源 URL 的 HTTP 头
 * optionsForAllow()：发送 HTTP OPTIONS 请求，返回对特定 URL 的 Allow 头信息
 * postForLocation()：POST 数据到一个 URL，返回新创建资源的 URL
 * put()：PUT 资源到特定的 URL
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000); // 连接超时时间
        factory.setReadTimeout(5000); // 数据读取超时时间
        return factory;
    }
}
