package com.vastrak.springboot001.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import com.vastrak.springboot001.dto.UserDto;


/**
 * 
 * @author Christian
 *
 */
public class UserMapperImpTest {
	private static final Log logger = LogFactory.getLog(UserMapperImpTest.class);

	public UserMapperImpTest() {
	}

	@Test
	public void test001_mapperUserToDto() {
		UserDto userDto = null;

		User user = new User();
		user.setUser_id(Long.valueOf(1L));
		user.setName("Luis");
		user.setEmail("luis@aol.com");
		user.addArticle(new Article());
		user.addArticle(new Article());

		userDto = UserMapper.MAPPER.userToUserDto(user);
		assertNotNull(userDto);
		assertTrue(userDto.getUserDto_id().equals(user.getUser_id()));
		assertTrue(userDto.getArticles().equals(Integer.valueOf(user.getArticles().size())));

		logger.info(">>>>>>>>>>> user list MappedTo userDto list size: " + userDto.getArticles());
	}

	@Test
	public void test002_mapperDtoToUser() {
		User user = null;

		UserDto userDto = new UserDto();
		userDto.setUserDto_id(Long.valueOf(1L));
		userDto.setName("Luis");
		userDto.setEmail("luis@aol.com");
		userDto.setArticles(Integer.valueOf(2));

		user = UserMapper.MAPPER.userDtoToUser(userDto);

		assertNotNull(user);
		assertTrue(user.getUser_id().equals(userDto.getUserDto_id()));
		assertNotNull(user.getArticles());
		assertTrue(user.getArticles().isEmpty());
	}
}