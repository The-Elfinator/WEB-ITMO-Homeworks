package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post find(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public void writeComment(User user, Post post, CommentCredentials commentCredentials) {
        Comment comment = new Comment();
        comment.setText(commentCredentials.getText());
        post.addComment(comment);
        comment.setPost(post);
        comment.setUser(user);
        postRepository.save(post);
    }
}
