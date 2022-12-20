package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.wp.security.Guest;

@Controller
public class NotFoundPage extends Page {

    @Guest
    @GetMapping("/notFound")
    public String index() {
        return "NotFoundPage";
    }
}
