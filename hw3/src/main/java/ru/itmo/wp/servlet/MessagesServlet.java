package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class MessagesServlet extends HttpServlet {

    private ArrayList<HashMap<String, String>> messages;

    private boolean notAllSpace(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        switch (uri) {
            case "/message/auth":
                if (user == null) {
                    user = request.getParameter("user");
                }
                if (user == null) {
                    user = "";
                }
                if (!user.isEmpty() && notAllSpace(user)) {
                    session.setAttribute("user", user);
                    try (PrintWriter writer = response.getWriter()) {
                        String json = new Gson().toJson(user);
                        writer.print(json);
                        writer.flush();
                    }
                }
                break;
            case "/message/findAll":
//                ArrayList<HashMap<String, String>> messages = (ArrayList<HashMap<String, String>>) session.getAttribute("messages");
                if (messages != null) {
                    try (PrintWriter writer = response.getWriter()) {
                        String json = new Gson().toJson(messages);
                        writer.print(json);
                        writer.flush();
                    }
                }
                break;
            case "/message/add":
                String text = request.getParameter("text");
                if (text == null) {
                    text = "";
                }
                if (!text.isEmpty() && notAllSpace(text)) {
                    HashMap<String, String> map = new HashMap<>();
                    user = (String) session.getAttribute("user");
                    map.put("user", user);
                    map.put("text", text);
//                    message   s = (ArrayList<HashMap<String, String>>) session.getAttribute("messages");
                    if (messages == null) {
                        messages = new ArrayList<>();
                    }
                    messages.add(map);
//                    session.setAttribute("messages", messages);
                    try (PrintWriter writer = response.getWriter()) {
                        String json = new Gson().toJson(text);
                        writer.print(json);
                        writer.flush();
                    }
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
