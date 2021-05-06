package com.example.myboard.dto;

import com.example.myboard.entity.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleForm {

    private String title;
    private String content;
    private Long id;

    public Article toEntity() {
        return Article.builder()
                .id(null)
                .title(title)
                .content(content)
                .build();
    }

    public ArticleForm(Article entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
