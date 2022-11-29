package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

/** @noinspection UnstableApiUsage*/
public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void validateCreating(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required!");
        }
        if (article.getTitle().length() >= 128) {
            throw new ValidationException("Title should be at most 127 symbols!");
        }
        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required!");
        }
        if (article.getText().length() >= 2048) {
            throw new ValidationException("Text should be at most 2047 symbols!");
        }
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
