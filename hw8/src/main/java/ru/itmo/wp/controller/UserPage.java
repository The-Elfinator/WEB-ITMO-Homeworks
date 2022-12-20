package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public String getUser(Model model, @PathVariable String userId) {
        if (userId.matches("[0-9]+")) {
            long id;
            try {
                id = Long.parseLong(userId);
                User userPage = userService.findById(id);
                if (userPage != null) {
                    model.addAttribute("userPage", userPage);
                }
            } catch (NumberFormatException e) {
                // Do nothing
            }
        }
        return "UserPage";
    }
}
