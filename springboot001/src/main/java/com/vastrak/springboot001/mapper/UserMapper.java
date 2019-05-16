package com.vastrak.springboot001.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import com.vastrak.springboot001.dto.UserDto;

/**
 * 
 * @author Christian
 *
 */
@Mapper
public interface UserMapper {
	public static final UserMapper MAPPER = (UserMapper) Mappers.getMapper(UserMapper.class);

	@Mappings({ @org.mapstruct.Mapping(source = "user_id", target = "userDto_id"),
			@org.mapstruct.Mapping(source = "name", target = "name"),
			@org.mapstruct.Mapping(source = "email", target = "email"),
			@org.mapstruct.Mapping(source = "articles", target = "articles") })
	UserDto userToUserDto(User paramUser);

	default Integer articlesToArticlesDto(List<Article> articles) {
		return articles != null ? Integer.valueOf(articles.size()) : null;
	}

	@Mappings({ @org.mapstruct.Mapping(source = "userDto_id", target = "user_id"),
			@org.mapstruct.Mapping(source = "name", target = "name"),
			@org.mapstruct.Mapping(source = "email", target = "email"),
			@org.mapstruct.Mapping(source = "articles", target = "articles") })
	User userDtoToUser(UserDto paramUserDto);

	default List<Article> articlesDtoToArticles(Integer articles) {
		return new ArrayList<Article>();
	}
}