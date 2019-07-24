package com.controller;

import cn.dsna.util.images.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 验证码的作用：通常的登录或者注册系统时，都会要求用户输入验证码，
 * 以此区别用户行为和计算机程序行为，目的是有人防止恶意注册、暴力破解密码等。
 *
 * 实现验证码的思路：用 server 实现随机生成数字和字母组成图片的功能，
 * 用 jsp 页面实现显示验证码和用户输入验证码的功能，再用 server 类分别获取图片和用户输入的数据
 * ，判断两个数据是否一致。
 */
@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* ValidateCode vc = new ValidateCode(110, 25, 4, 9);
        //向session中保存验证码
        request.getSession().setAttribute("scode", vc.getCode());
        vc.write(response.getOutputStream());*/
       test(request,response);
    }

    //自定义验证码
    protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        int width = 60;
        int height = 20;
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //创建内存图像并获得图形上下文
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        /*
         * 产生随机验证码
         * 定义验证码的字符表
         */
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }

        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);

           /*
           * 随机产生120个干扰点
            */

        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        //在不同高度输出验证码的不同字符
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
        g.dispose();

        //将图像传到客户端
        ServletOutputStream sos = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        byte[] buffer = baos.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        baos.close();
        sos.close();

        session.setAttribute("scode", new String(rands));
    }
}
