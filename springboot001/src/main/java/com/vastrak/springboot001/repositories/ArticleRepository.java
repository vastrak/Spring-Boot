package com.vastrak.springboot001.repositories;

import com.vastrak.springboot001.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author Christian
 *
 */
@Repository
public abstract interface ArticleRepository extends JpaRepository<Article, Long> {
}