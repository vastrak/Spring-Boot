package com.vastrak.springboot001.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import com.vastrak.springboot001.dto.ArticleDto;
import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.mapper.ArticleMapper;
import com.vastrak.springboot001.mapper.UserMapper;
import com.vastrak.springboot001.repositories.ArticleRepository;
import com.vastrak.springboot001.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private static final Log logger = LogFactory.getLog(UserServiceImp.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public UserDto getUserById(Long user_id) {

		if (user_id == null) {
			return null;
		}
		User userfound = userRepository.findOne(user_id);
		return (userfound == null ? null : UserMapper.MAPPER.userToUserDto(userfound));
	}

	/**
	 * Returns an empty list if there are no users in the repository.
	 * @return empty Lit<UserDto>
	 */
	@Override
	public List<UserDto> getUsers() {

		List<User> listUser = userRepository.findAll();
		List<UserDto> listUserDto = new ArrayList<>();
		for (User user : listUser) {
			listUserDto.add(UserMapper.MAPPER.userToUserDto(user));
		}
		return listUserDto;
	}

	@Override
	public Long createUpdateUser(UserDto userDto) {

		if (userDto != null) {
			Long id = userDto.getUserDto_id();
			boolean isCreateOrUpdate = ((id == null) || existUser(id));
			if (isCreateOrUpdate) {
				User user = UserMapper.MAPPER.userDtoToUser(userDto);
				user = userRepository.save(user);
				return user.getUser_id();
			}
		}
		return null;
	}

	@Override
	public boolean existUser(Long user_id) {

		return user_id == null ? false : userRepository.exists(user_id);
	}

	@Override
	public void removeUser(Long user_id) {

		if(user_id != null) {		
		   userRepository.delete(user_id);
		}
	}

	@Override
	public List<ArticleDto> getUserArticles(Long user_id) {

		if(user_id!=null) {
     		User user = userRepository.findOne(user_id);
     		if(user!=null) {
     			List<Article> listArticle = user.getArticles();
     			List<ArticleDto> listArticleDto = new ArrayList<>();
     			for(Article article: listArticle) {
     				listArticleDto.add(ArticleMapper.MAPPER.articleToArticleDto(article));
     			}
     			return listArticleDto;
     		}
		}
		return null;
	}	
}
