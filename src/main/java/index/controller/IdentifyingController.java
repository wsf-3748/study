package index.controller;

import index.util.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class IdentifyingController {

    @RequestMapping(value = "/getImg", method = {RequestMethod.POST, RequestMethod.GET})
    protected void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String date = req.getParameter("date");
        System.out.println("date="+date);
        //1.生成随机的验证码及图片
        Object[] objs = VerifyUtil.createImage();
        //2.将验证码存入session
        String imgcode = (String) objs[0];
        HttpSession session = req.getSession();
        session.setAttribute("imgcode", imgcode);
        System.out.println("imgcode=" + imgcode);
        //3.将图片输出给浏览器
        BufferedImage img = (BufferedImage) objs[1];
        res.setContentType("image/png");
        res.setCharacterEncoding("UTF-8");

        Cookie cookie = new Cookie("imagecode", imgcode);
        res.addCookie(cookie);
        //服务器自动创建输出流，目标指向浏览器
        OutputStream os = res.getOutputStream();
        ImageIO.write(img, "png", os);
        os.close();
    }

    @RequestMapping(value = "/checkImg", method = {RequestMethod.POST, RequestMethod.GET})
    protected void checkImageCode(HttpServletRequest req, HttpServletResponse res) {
        //2.将验证码存入session
        HttpSession session = req.getSession();
        String imgcode = (String)session.getAttribute("imgcode"); // 多个人同时登录会覆盖
        System.out.println("session======imgcode=" + imgcode);
        String reqImgCode = req.getParameter("imgcode");
        System.out.println("reqImgCode="+reqImgCode);

    }
}