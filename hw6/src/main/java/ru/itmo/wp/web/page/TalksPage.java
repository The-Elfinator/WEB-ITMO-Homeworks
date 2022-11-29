package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class TalksPage {
    private final TalkService talkService = new TalkService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // Do nothing
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new RedirectException("/index");
        }
        view.put("talks", talkService.findAll());
    }

    private void send(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new RedirectException("/index");
        }
        Talk talk = new Talk();
        talk.setSourceUserId(user.getId());
        User targetUser = talkService.findTargetUser(request.getParameter("login"));
        if (targetUser == null) {
            throw new ValidationException("User not found");
        }
        talk.setTargetUserId(targetUser.getId());
        if (Strings.isNullOrEmpty(request.getParameter("message"))) {
            throw new ValidationException("Message is required");
        }
        talk.setText(request.getParameter("message"));
        talkService.save(talk);
        throw new RedirectException("/talks");
    }

}
