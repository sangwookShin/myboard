package com.example.myboard.controller;

import com.example.myboard.dto.ArticleForm;
import com.example.myboard.entity.Article;
import com.example.myboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}

