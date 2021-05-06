package com.example.myboard.controller;

import com.example.myboard.dto.ArticleForm;
import com.example.myboard.entity.Article;
import com.example.myboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model) {

        Iterable<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        return "articles/index";

    }

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles")
    public String create(ArticleForm form) {
        log.info(form.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        Article article = articleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 article이 없습니다."));
        model.addAttribute("article", article);
        return "articles/show";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model) {
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );
        model.addAttribute("article", target);
        return "articles/edit";
    }

}

