package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.form.validator.CommentCredentialsValidator;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentCredentialsValidator commentCredentialsValidator;

    public PostPage(PostService postService, CommentCredentialsValidator commentCredentialsValidator) {
        this.postService = postService;
        this.commentCredentialsValidator = commentCredentialsValidator;
    }

    @InitBinder("commentForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(commentCredentialsValidator);
    }

    @GetMapping({"/post/{id}", "/post"})
    public String postGet(@PathVariable(required = false) String id, Model model, HttpSession httpSession) {
        if (id == null) {
            putMessage(httpSession, "Post was not found!");
            return "redirect:/notFound";
        }
        long realId;
        try {
            realId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Post was not found!");
            return "redirect:/notFound";
        }
        Post post = postService.find(realId);
        if (post == null) {
            putMessage(httpSession, "Post was not found!");
            return "redirect:/notFound";
        }
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new CommentCredentials());
        return "PostPage";
    }

    @PostMapping({"/post/{id}", "/post"})
    public String postPost(@PathVariable(required = false) String id, @Valid @ModelAttribute("commentForm") CommentCredentials commentCredentials,
                       BindingResult bindingResult,
                       HttpSession httpSession, Model model) {
        if (id == null) {
            putMessage(httpSession, "Something is wrong!");
            return "redirect:/";
        }
        long realId;
        try {
            realId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Post was not found!");
            return "redirect:/notFound";
        }
        Post post = postService.find(realId);
        if (bindingResult.hasErrors()) {
            if (post == null) {
                return "IndexPage";
            }
            model.addAttribute("post", post);
            model.addAttribute("postComment", new Comment());
            return "PostPage";
        }
        if (post == null) {
            putMessage(httpSession, "Sorry, something is wrong!");
        } else {
            postService.writeComment(getUser(httpSession), post, commentCredentials);
            putMessage(httpSession, "Comment has been uploaded successfully!");
        }
        return "redirect:/post/" + realId;
    }
}
