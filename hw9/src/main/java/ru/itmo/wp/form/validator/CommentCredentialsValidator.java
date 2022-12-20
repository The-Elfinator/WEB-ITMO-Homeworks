package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.itmo.wp.form.CommentCredentials;

@Component
public class CommentCredentialsValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return CommentCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            CommentCredentials commentForm = (CommentCredentials) target;
            if (commentForm.getText() == null || commentForm.getText().matches("[\\s]*")) {
                errors.rejectValue("text", "text.invalid-text", "Comment should not contain only whitespaces");
            }
        }
    }
}
