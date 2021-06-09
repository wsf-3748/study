package index.controller;

import index.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET 请求1：getForObject() 方法的使用
     * getForObject() 用于发送一个 HTTP GET 请求。它和 getForEntity() 用法几乎相同。
     * 区别在于 getForObject() 返回值返回的是响应体，省略了很多 response 的信息。
     *
     * @return 返回获取到的字符串
     */
    @GetMapping("/rest") // 访问 http://localhost:9080/MybatisTest/rest
    public String rest() {
        String url = "http://jsonplaceholder.typicode.com/posts/1"; // 返回json字符串接口
        String str = restTemplate.getForObject(url, String.class); // 返回json字符串
        System.out.println(str);
        return str;
    }

    @GetMapping("/rest2") // 访问 http://localhost:9080/MybatisTest/rest2
    public void rest2() {
        String url = "http://jsonplaceholder.typicode.com/posts/1"; // 返回json字符串接口
        Message message = restTemplate.getForObject(url, Message.class); // 返回自定义对象
        if (message == null) {
            System.out.println("message: null");
        } else {
            System.out.println(message.toString());
        }
    }

    @GetMapping("/rest3") // 访问 http://localhost:9080/MybatisTest/rest3
    public void rest3() {
        String url = "http://jsonplaceholder.typicode.com/posts"; // 返回json字符串数组接口
        Message[] message = restTemplate.getForObject(url, Message[].class);
        if (message == null || message.length == 0) {
            System.out.println("message: 空");
        } else {
            System.out.println("数组长度: " + message.length);
            for (Message msg : message) {
                System.out.println("id=" + msg.getId() + ", title=" + msg.getTitle());
            }
        }
    }

    @GetMapping("/rest4") // 访问 http://localhost:9080/MybatisTest/rest4
    public void rest4() {
        /*String url = "http://jsonplaceholder.typicode.com/{1}/{2}"; // 占位符形式传参
        Message message = restTemplate.getForObject(url, Message.class, "posts", 1);*/

        /*String url = "http://jsonplaceholder.typicode.com/{type}/{id}"; // 占位符形式传参
        String type = "posts";
        int id = 1;
        Message message = restTemplate.getForObject(url, Message.class, type, id);*/

        String url = "http://jsonplaceholder.typicode.com/{type}/{id}"; // 占位符形式传参
        Map<String, Object> map = new HashMap<>();
        map.put("type", "posts");
        map.put("id", 1);
        Message message = restTemplate.getForObject(url, Message.class, map);

        if (message == null) {
            System.out.println("message: 空");
        } else {
            System.out.println(message.toString());
        }
    }

    /**
     * GET 请求2：getForEntity() 方法的使用
     * getForEntity() 同样用于发送一个 HTTP GET 请求。它和上面介绍的 getForObject() 用法几乎相同。
     * 区别在于 getForEntity() 返回的是 ResponseEntity<T>：
     *      ResponseEntity<T> 是 Spring 对 HTTP 请求响应的封装，包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等。
     *      其中响应消息体可以通过 ResponseEntity 对象的 getBody() 来获取。
     */
    @GetMapping("/rest5") // 访问 http://localhost:9080/MybatisTest/rest5
    public void rest5() {
        String url = "http://jsonplaceholder.typicode.com/posts/5"; // 返回json字符串数组接口
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class); // 返回字符串
        String body = responseEntity.getBody(); // 获取响应体
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头
        HttpStatus status = responseEntity.getStatusCode(); // 获取响应码
        int statusCode = responseEntity.getStatusCodeValue(); // 获取响应码值

        System.out.println("body: " + body);
        System.out.println("headers: " + headers);
        System.out.println("status: " + status);
        System.out.println("statusCode: " + statusCode);
    }

    @GetMapping("/rest6") // 访问 http://localhost:9080/MybatisTest/rest6
    public void rest6() {
        String url = "http://jsonplaceholder.typicode.com/posts/5"; // 返回json字符串数组接口
        ResponseEntity<Message> responseEntity = restTemplate.getForEntity(url, Message.class); // 返回自定义对象
        Message body = responseEntity.getBody(); // 获取响应体
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头
        HttpStatus status = responseEntity.getStatusCode(); // 获取响应码
        int statusCode = responseEntity.getStatusCodeValue(); // 获取响应码值

        System.out.println("body: " + body);
        System.out.println("headers: " + headers);
        System.out.println("status: " + status);
        System.out.println("statusCode: " + statusCode);
    }

    @GetMapping("/rest7") // 访问 http://localhost:9080/MybatisTest/rest7
    public void rest7() {
        String url = "http://jsonplaceholder.typicode.com/posts"; // 返回json字符串数组接口
        ResponseEntity<Message[]> responseEntity = restTemplate.getForEntity(url, Message[].class); // 返回自定义对象
        Message[] body = responseEntity.getBody(); // 获取响应体
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头
        HttpStatus status = responseEntity.getStatusCode(); // 获取响应码
        int statusCode = responseEntity.getStatusCodeValue(); // 获取响应码值

        System.out.println("body: " + body.length);
        System.out.println("headers: " + headers);
        System.out.println("status: " + status);
        System.out.println("statusCode: " + statusCode);
    }

    /**
     * POST 请求1：postForObject() 方法的使用
     * 原文出自：www.hangge.com  转载请保留原文链接：https://www.hangge.com/blog/cache/detail_2516.html
     */
    @GetMapping("/rest8") // 访问 http://localhost:9080/MybatisTest/rest8
    public void rest8() {
        String url = "http://jsonplaceholder.typicode.com/posts"; // 返回json字符串数组接口
        Message message = new Message(); // 要发送的数据对象
        message.setUserId(222);
        message.setTitle("abcdefg");
        message.setBody("大飞");

        String str = restTemplate.postForObject(url, message, String.class);
        System.out.println(str);
    }

    /**
     * 使用 Form 表单的形式提交数据
     */
    @GetMapping("/rest9") // 访问 http://localhost:9080/MybatisTest/rest9
    public void rest9() {
        long s = System.currentTimeMillis();
        // 使用 POST 方式发送 multipart/form-data 格式的数据
//        String url = "http://jsonplaceholder.typicode.com/posts"; // 返回json字符串数组接口
        String url = "http://localhost:9080/MybatisTest/changeInfo2"; // 返回json字符串数组接口

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // valueOf("application/x-www-form-urlencoded")

        // 提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("title", "hello");
        map.add("body", "大飞哥");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // 发送post请求，并输出结果
        String str = restTemplate.postForObject(url, request, String.class);
        System.out.println(str);
        long e = System.currentTimeMillis();
        System.out.println("耗时: " + (e-s) + "ms");
    }

    /**
     * 使用 Form 表单的形式提交数据
     */
    @GetMapping("/rest10") // 访问 http://localhost:9080/MybatisTest/rest10
    public void rest10() {
        long s = System.currentTimeMillis();
        // 使用 POST 方式发送 json 格式的数据
//        String url = "http://jsonplaceholder.typicode.com/posts"; // 返回json字符串数组接口
        String url = "http://localhost:9080/MybatisTest/changeInfo"; // 返回json字符串数组接口

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8); // valueOf("application/json;charset=UTF-8")

        // 提交参数设置
        Message message = new Message(102, 666, "dafei", "你好啊");

        // 组装请求体
        HttpEntity<Message> request = new HttpEntity<>(message, headers);

        // 发送post请求，并输出结果
        Message response = restTemplate.postForObject(url, request, Message.class);
        System.out.println(response.toString());
        long e = System.currentTimeMillis();
        System.out.println("耗时: " + (e-s) + "ms");
    }

    /**
     * POST 请求2：postForEntity()方法的使用
     * postForEntity() 用于发送一个 HTTP POST 请求。它和上面的 postForObject() 用法几乎相同。
     * 区别在于 getForEntity() 返回的是 ResponseEntity<T>：
     *      ResponseEntity<T> 是 Spring 对 HTTP 请求响应的封装，包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等。
     *      其中响应消息体可以通过 ResponseEntity 对象的 getBody() 来获取。
     */
    @GetMapping("/rest11") // 访问 http://localhost:9080/MybatisTest/rest11
    public void rest11() {
        // 请求地址
        String url = "http://jsonplaceholder.typicode.com/posts";

        // 要发送的数据对象
        Message message = new Message();
        message.setUserId(222);
        message.setTitle("adc");
        message.setBody("打算上单");

        // 发送post请求，并输出结果
        ResponseEntity<Message> responseEntity = restTemplate.postForEntity(url, message, Message.class);
        Message body = responseEntity.getBody(); // 获取响应体
        HttpStatus statusCode = responseEntity.getStatusCode(); // 获取响应码
        int statusCodeValue = responseEntity.getStatusCodeValue(); // 获取响应码值
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头

        System.out.println("body：" + body);
        System.out.println("statusCode：" + statusCode);
        System.out.println("statusCodeValue：" + statusCodeValue);
        System.out.println("headers：" + headers);
    }

    /**
     * 使用 Form 表单的形式提交数据
     * 使用 POST 方式发送 multipart/form-data 格式的数据
     */
    @GetMapping("/rest12") // 访问 http://localhost:9080/MybatisTest/rest12
    public void rest12() {
        // 请求地址
        String url = "http://jsonplaceholder.typicode.com/posts";

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id", 3444);
        map.add("userId", 5666);
        map.add("title", "士大夫");
        map.add("body", "手动阀手动阀");

        // 组装请求体
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        // 发送post请求，并输出结果
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        String body = responseEntity.getBody(); // 获取响应体
        HttpStatus statusCode = responseEntity.getStatusCode(); // 获取响应码
        int statusCodeValue = responseEntity.getStatusCodeValue(); // 获取响应码值
        HttpHeaders responseHeaders = responseEntity.getHeaders(); // 获取响应头

        System.out.println("body：" + body);
        System.out.println("statusCode：" + statusCode);
        System.out.println("statusCodeValue：" + statusCodeValue);
        System.out.println("responseHeaders：" + responseHeaders);
    }

    /**
     * POST 请求3：postForLocation() 方法的使用
     * postForLocation() 也是通过 Post 方式提交新资源，postForLocation() 方法的参数和前面两种（postForObject、postForEntity）的参数基本一致。
     * 区别在于 postForLocation() 方法的返回值为 Uri，这个只需要服务提供者返回一个 Uri 即可，该 Uri 表示新资源的位置。
     */
    @GetMapping("/rest13") // 访问 http://localhost:9080/MybatisTest/rest13
    public void rest13() {
        // 请求地址
//        String url = "http://jsonplaceholder.typicode.com/posts";
        String url = "http://localhost:9080/MybatisTest/changeInfo3";

        // 要发送的数据对象
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("username", "hangge");
        request.add("password", "123456");

        // 发送post请求，并输出结果
        URI uri = restTemplate.postForLocation(url, request);
        System.out.println(uri);
    }
}
