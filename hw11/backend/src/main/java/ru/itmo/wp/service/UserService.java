package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserRegisterCredentials;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User register(UserRegisterCredentials userRegisterCredentials) {
        User user = new User();
        user.setLogin(userRegisterCredentials.getLogin());
        user.setName(userRegisterCredentials.getUsername());
        user.setAdmin(false);
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userRegisterCredentials.getLogin(), "12345");
        return user;
    }

    public void createPost(String title, String text, Long userId) {
        User user = findById(userId);
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        user.addPost(post);
        userRepository.save(user);
        postRepository.save(post);
        return;
    }
}
