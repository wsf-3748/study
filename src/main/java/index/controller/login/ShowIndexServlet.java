package index.controller.login;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ShowIndexServlet", urlPatterns = "/show")
@Controller("/show")
public class ShowIndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (null != username) {
            // 在登陆状态
            response.getWriter().write("欢迎回来，" + username);
        } else {
            // 不在登陆状态，根据需求：①提示 ②跳转到登陆页
            //response.getWriter().write("您还未登陆，<a href='/demo/login.html'>请登陆</a>");
            response.sendRedirect("/demo/login.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
