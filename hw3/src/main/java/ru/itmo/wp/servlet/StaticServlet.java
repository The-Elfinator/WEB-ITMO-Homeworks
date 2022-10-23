package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uri = request.getRequestURI().split("\\+");
            for (String uri1 : uri) {
                File file = new File("C:\\users\\artem\\hw3\\src\\main\\webapp\\static", uri1);
                if (!file.isFile()) {
                    file = new File(getServletContext().getRealPath("/static" + uri1));
                }
                if (!file.isFile()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    // NOTE: do we really need return if one file not found?
                    return;
                } else {
                    if (response.getContentType() == null) {
                        response.setContentType(getServletContext().getMimeType(file.getName()));
                    }
                    try (OutputStream outputStream = response.getOutputStream()) {
                        Files.copy(file.toPath(), outputStream);
                    }
                }
            }
    }
}
