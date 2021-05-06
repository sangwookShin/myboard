package com.example.myboard.api;

import com.example.myboard.dto.ArticleForm;
import com.example.myboard.entity.Article;
import com.example.myboard.repository.ArticleRepository;
import com.example.myboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form) {
        log.info(form.toString());

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id) {

        Article entity = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 article이 없습니다."));

        return new ArticleForm(entity);
    }

    @GetMapping("/api/articles")
    public List<ArticleForm> getArticles() {

        Iterable<Article> articles = articleRepository.findAll();

        List<ArticleForm> articleFormList = new ArrayList<>();
        for (Article article: articles) {
            articleFormList.add(new ArticleForm(article));
        }
        return articleFormList;
    }


    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody ArticleForm form) {
        Article saved = articleService.update(id, form);

        return saved.getId();
    }

    @DeleteMapping("/api/articles/{id}")
    public Long destroy(@PathVariable Long id) {
        return articleService.destroy(id);
    }
}
