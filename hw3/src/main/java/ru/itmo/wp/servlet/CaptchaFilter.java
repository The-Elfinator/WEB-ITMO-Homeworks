package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;


public class CaptchaFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        String captchaPasted = (String) session.getAttribute("captchaCompleted");
//        Integer value = (Integer) session.getAttribute("value");
//        System.out.println(captchaPasted);
//        if (captchaPasted == null) {
//            session.setAttribute("captchaCompleted", "false");
//            int randomNumber = (int) (Math.random()*1000);
//            if (randomNumber < 100) {
//                randomNumber *= 10;
//                if (randomNumber < 100) {
//                    randomNumber *= 10;
//                }
//            }
//
//        }
    }
}
