package index.controller.login;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "LoginServlet", urlPatterns = "/login")
@Controller("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取输入的验证码
        String validateCode = request.getParameter("validateCode");
        // 将输入的验证码和产生的随机验证码进行校验
        String existCode = (String) request.getSession().getAttribute("existCode");
        if (validateCode.equals(existCode)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("username="+username);
            System.out.println("password="+password);
            if (null == username) {
                // 登陆失败，跳转登陆页面，转发
                request.getRequestDispatcher("/login.html").forward(request, response);
            } else {
                // 登陆成功，跳转到首页，重定向
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("password", password);
                response.sendRedirect("/demo/show");
            }
        } else {
            // 校验不通过，跳转到登陆页面
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}