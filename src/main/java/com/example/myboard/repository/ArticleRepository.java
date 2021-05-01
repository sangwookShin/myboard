package com.example.myboard.repository;

import com.example.myboard.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}
