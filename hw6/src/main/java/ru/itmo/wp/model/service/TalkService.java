package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class TalkService {

    private final TalkRepository talkRepository = new TalkRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(Talk talk) {
        talkRepository.save(talk);
    }


    public List<Talk> findAll() {
        return talkRepository.findAll();
    }

    public User findTargetUser(String login) {
        return userRepository.findByLogin(login);
    }

    public String findUserLogin(long userId) {
        return userRepository.find(userId).getLogin();
    }
}
