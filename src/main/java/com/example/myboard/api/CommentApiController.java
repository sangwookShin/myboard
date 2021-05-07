package com.example.myboard.api;

import com.example.myboard.dto.CommentForm;
import com.example.myboard.entity.Article;
import com.example.myboard.entity.Comment;
import com.example.myboard.repository.ArticleRepository;
import com.example.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @PostMapping("/api/comments/{articleId}")
    public Long create(@PathVariable Long articleId, @RequestBody CommentForm form) {

        log.info("form: " + form.toString());
        Comment comment = form.toEntity();
        log.info("comment: " + comment.toString());

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글을 작성할 article이 없습니다."));

        comment.stickTo(article);
        log.info("written: " + comment.toString());
        Comment saved = commentRepository.save(comment);
        log.info("saved: " + saved.toString());
        return saved.getId();
    }
}
