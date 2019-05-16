package com.vastrak.springboot001.mapper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import com.vastrak.springboot001.dto.ArticleDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * 
 * @author Christian
 *
 */
public class ArticleMapperImpTest {
	private static final Log logger = LogFactory.getLog(ArticleMapperImpTest.class);

	public ArticleMapperImpTest() {
	}

	@Test
	public void mapperArticleToDto() {
		Article article = new Article();
		article.setArticle_id(Long.valueOf(1L));
		article.setTitle("Un artículo nuevo");
		article.setBody("Hasta el 40 de mayo...");
		User user = new User();
		user.setUser_id(Long.valueOf(2L));
		user.setName("Luis");
		user.setEmail("luis@aol.com");
		user.addArticle(article);
		article.setUser(user);

		ArticleDto articleDto = ArticleMapper.MAPPER.articleToArticleDto(article);

		assertNotNull(articleDto);
		assertTrue(articleDto.getArticle_id().equals(article.getArticle_id()));
		assertTrue(articleDto.getUserName().equals(user.getName()));

		logger.info(">>>>>>>>>>> article MappedTo articleDto user name: " + articleDto.getUserName());
	}

	@Test
	public void mapperArticleDtoToArticle() {
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticle_id(Long.valueOf(1L));
		articleDto.setTitle("Un artículo no tan nuevo");
		articleDto.setBody("Al mal tiempo, buena cara");
		articleDto.setUserName("Miguel");

		Article article = ArticleMapper.MAPPER.articleDtoToArticle(articleDto);

		assertNotNull(article);
		assertNotNull(article.getUser());
		assertTrue(article.getArticle_id().equals(articleDto.getArticle_id()));
		assertTrue(article.getUser().getName().equals(articleDto.getUserName()));
	}
}
