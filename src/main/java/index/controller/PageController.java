package index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageController {

    @RequestMapping("/myPage")
    public String picture() {
        return "myPage";
    }

    @RequestMapping("/myPage2")
    public String picture2() {
        return "html/myPage2";
    }

    @RequestMapping("/myPage3")
    public String picture3(HttpServletRequest request, HttpServletResponse response) {
        String name = (String) request.getAttribute("name");
        System.out.println("name = " + name);
        ServletContext s = request.getServletContext();
        RequestDispatcher dispatcher = s.getRequestDispatcher("");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return "html/myPage2";
    }
}
