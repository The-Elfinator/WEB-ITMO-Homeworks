package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.wp.security.Guest;

@Controller
public class AccessDeniedPage extends Page {

    @Guest
    @GetMapping("/accessDenied")
    public String index() {
        return "AccessDeniedPage";
    }
}
