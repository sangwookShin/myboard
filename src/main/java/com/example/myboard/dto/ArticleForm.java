package com.example.myboard.dto;

import com.example.myboard.entity.Article;
import lombok.Data;

@Data
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .id(null)
                .title(title)
                .content(content)
                .build();
    }
}
