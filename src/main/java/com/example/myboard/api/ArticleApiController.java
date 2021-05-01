package com.example.myboard.api;

import com.example.myboard.dto.ArticleForm;
import com.example.myboard.entity.Article;
import com.example.myboard.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form) {
        log.info(form.toString());

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return saved.getId();
    }
}
