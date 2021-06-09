package index.controller;

import index.configuration.MyConfiguration;
import index.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
//@RequestMapping("/hello")
public class MyController {

    @Autowired
    String helloWorld;

    @GetMapping("/world")
    public String test1() {
        System.out.println(helloWorld);
        return helloWorld;
    }

    @RequestMapping("/picture")
    public String picture() {
        return "myPage.html";
    }

    @PostMapping("/changeInfo")
    public Message changeMessage(@RequestBody Message message) {
        if (message != null) {
            System.out.println("changeInfo已经拿到message了");
            System.out.println(message.toString());

            message.setTitle("你总是这样");
        }
        return message;
    }

    @PostMapping("/changeInfo2")
    public Message changeMessage2(@RequestParam String title, @RequestParam String body) {
        System.out.println("changeInfo2");
        System.out.println(title);
        System.out.println(body);

        return new Message(1, 2, title, body);
    }

    @PostMapping("/changeInfo3")
    public URI changeMessage3(@RequestParam String username, @RequestParam String password) throws URISyntaxException {
        System.out.println("changeInfo3");
        System.out.println(username);
        System.out.println(password);

        return new URI("https://www.baidu.com/");
    }
}
