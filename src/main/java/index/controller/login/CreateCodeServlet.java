package index.controller.login;

import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

//@WebServlet(name = "CreateCodeServlet", urlPatterns = "/createCode")
@Controller("/createCode")
public class CreateCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 60;//定义图片宽度
        int height = 32;//定义图片高度
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建画笔对象
        Graphics g = image.getGraphics();
        //设置背景颜色
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);//实心矩形
        //设置边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);//空心矩形

        Random rdm = new Random();
        //画干扰椭圆
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        //产生随机字符串
        String hash1 = Integer.toHexString(rdm.nextInt());
        //生成四位随机验证码
        String capstr = hash1.substring(0, 4);
        //将产生的验证码存储到session域中，方便以后进行验证码校验!
        request.getSession().setAttribute("existCode", capstr);
        System.out.println(capstr);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(capstr, 8, 24);
        g.dispose();
        //将图片响应到浏览器
        response.setContentType("image/jpeg");
        OutputStream strm = response.getOutputStream();
        ImageIO.write(image, "jpeg", strm);
        strm.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
