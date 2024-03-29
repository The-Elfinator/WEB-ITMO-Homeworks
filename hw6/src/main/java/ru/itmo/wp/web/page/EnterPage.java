package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings({"unused"})
public class EnterPage {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        userService.validateEnter(loginOrEmail, password);
        User user = userService.findByLoginOrEmailAndPassword(loginOrEmail, password);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("message", "Hello, " + user.getLogin());

        saveEvent(user);

        throw new RedirectException("/index");
    }

    private void saveEvent(User user) {
        final EventRepository eventRepository = new EventRepositoryImpl();
        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.Type.ENTER);
        eventRepository.save(event);
    }
}
