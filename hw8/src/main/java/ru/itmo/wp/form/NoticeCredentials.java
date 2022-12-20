package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoticeCredentials {

    @NotNull
    @NotEmpty
    @Size(max = 2047)
    @Pattern(regexp = "[\\s]*\\S(.|\\s)*", message = "Text shouldn't content whitespaces only!")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
