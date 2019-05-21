package com.vastrak.springboot001.service;

import java.util.List;

import com.vastrak.springboot001.dto.ArticleDto;
import com.vastrak.springboot001.dto.UserDto;

public interface UserService {
	

	public UserDto getUserById(Long user_id);
	public List<UserDto> getUsers();
	public Long createUpdateUser(UserDto userDto);
	public boolean existUser(Long user_id);
	public void removeUser(Long user_id);
	public List<ArticleDto> getUserArticles(Long user_id);

}
