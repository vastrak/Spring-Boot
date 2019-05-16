package com.vastrak.springboot001.mapper;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import com.vastrak.springboot001.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author Christian
 *
 */
@Mapper
public interface ArticleMapper {
	public static final ArticleMapper MAPPER = (ArticleMapper) Mappers.getMapper(ArticleMapper.class);

	@Mappings({ @org.mapstruct.Mapping(source = "article_id", target = "article_id"),
			@org.mapstruct.Mapping(source = "title", target = "title"),
			@org.mapstruct.Mapping(source = "body", target = "body"),
			@org.mapstruct.Mapping(source = "user.name", target = "userName") })
	ArticleDto articleToArticleDto(Article paramArticle);

	@Mappings({ @org.mapstruct.Mapping(source = "article_id", target = "article_id"),
			@org.mapstruct.Mapping(source = "title", target = "title"),
			@org.mapstruct.Mapping(source = "body", target = "body"),
			@org.mapstruct.Mapping(source = "userName", target = "user") })
	Article articleDtoToArticle(ArticleDto paramArticleDto);

	default User articlesDtoToArticles(String userName) {
		User user = new User();
		if (userName == null) {
			return null;
		}
		user.setName(userName);
		return user;
	}
}