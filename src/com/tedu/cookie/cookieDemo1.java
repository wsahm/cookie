package com.tedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "cookieDemo1",urlPatterns = "/cookieDemo1")
public class cookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决中文乱码
        response.setContentType("text/html;charset=utf-8");
        //1.获取当前时间
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String time = s.format(date);
        //2.将当前访问时间发送给服务器
        //>>创建一个cookie对象
        Cookie cookie = new Cookie("time", time);
        //>>将cookie添加到response中发送给服务器保存
        response.addCookie(cookie);

        //3.获取上次访问时间
        //>>获取请求中的所有cookie
        Cookie[] cookies = request.getCookies();
        //>>遍历所有cookie,匹配到cookie中name为time的
        String timeStr=null;
        if(cookies!=null) {
            for (Cookie c :
                    cookies) {
                if ("time".equals(c.getName())) {
                    timeStr = c.getValue();
                }
            }
        }
        if(timeStr!=null){
            response.getWriter().write("上次访问该网站时间为："+timeStr);
        }else{
            response.getWriter().write("您是第一次访问该网站");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
