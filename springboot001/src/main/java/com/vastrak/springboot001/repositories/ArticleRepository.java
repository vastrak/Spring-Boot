package com.vastrak.springboot001.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vastrak.springboot001.domain.Article;


/**
 * 
 * @author Christian
 *
 */
@Repository
public abstract interface ArticleRepository extends JpaRepository<Article, Long> {
	
	@Query("SELECT art FROM Article art WHERE art.user.user_id = :user_id")
	public abstract List<Article> findAllByUser(@Param(value = "user_id") Long user_id);
}