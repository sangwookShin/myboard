package com.example.myboard.service;

import com.example.myboard.dto.CommentForm;
import com.example.myboard.entity.Article;
import com.example.myboard.entity.Comment;
import com.example.myboard.repository.ArticleRepository;
import com.example.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(Long articleId, CommentForm form) {

        log.info("form: " + form.toString());
        Comment comment = form.toEntity();
        log.info("comment: " + comment.toString());

        Article article = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new IllegalArgumentException("댓글을 작성할 Article이 없습니다.")
                );

        comment.stickTo(article);
        log.info("written: " + comment.toString());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Long id, CommentForm form) {
        // 수정 댓글 폼을 엔티티로 변경
        log.info("form: " + form.toString());
        Comment edited = form.toEntity();
        log.info("edited: " + form.toString());

        // DB에서 기존 댓글을 가져옴
        Comment target = commentRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 댓글이 없습니다.")
                );
        log.info("target: " + target.toString());

        // 기존 댓글을 수정!
        target.rewrite(edited.getContent());
        log.info("updated: " + target.toString());
        return commentRepository.save(target);
    }

    @Transactional
    public Long destroy(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
