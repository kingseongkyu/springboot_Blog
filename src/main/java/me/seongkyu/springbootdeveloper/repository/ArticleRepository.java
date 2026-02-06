package me.seongkyu.springbootdeveloper.repository;

import me.seongkyu.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
