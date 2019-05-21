package com.vastrak.springboot001.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.vastrak.springboot001.dto.ArticleDto;
import com.vastrak.springboot001.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImpTest {

	private static final Log logger = LogFactory.getLog(UserServiceImpTest.class);

	@Autowired
	private UserService userService = new UserServiceImp();

	@Test
	@Sql(scripts = { "classpath:create-data.sql" })
	public void test001_getUserById() {

		UserDto userDtoFound = userService.getUserById(1L);
		UserDto userDtoNotFound = userService.getUserById(55L);

		assertNotNull(userDtoFound);
		assertTrue(userDtoFound.getName().equals("Luis"));
		assertTrue(userDtoFound.getArticles() == 4);
		assertNull(userDtoNotFound);

	}

	@Test
	public void test002_existUserById() {

		assertTrue(userService.existUser(1L));
		assertFalse(userService.existUser(555L));
	}

	@Test
	public void test003_getUsers() {

		List<UserDto> listUserDto = userService.getUsers();

		int i = 1;
		for (UserDto userDto : listUserDto) {
			logger.info("nº: " + (i++) + " > " + userDto);
		}

		assertNotNull(listUserDto);
		assertTrue(listUserDto.size() == 5);
	}

	@Test
	public void test004_createUpdateUser() {

		// create user
		UserDto userDto = new UserDto();
		userDto.setArticles(null);
		userDto.setName("Carlos");
		userDto.setEmail("carlos@notengomail.com");
		userDto.setArticles(null);

		// persist new user
		Long id = userService.createUpdateUser(userDto);
		assertNotNull(id);

		// update new user
		userDto.setName("Carlitos");
		userDto.setEmail("carlos@tengomail.com");

		// persist update
		id = userService.createUpdateUser(userDto);

		// retrieve user by id
		UserDto found = userService.getUserById(id);
		assertNotNull(found);
		assertTrue(found.getName().equals("Carlitos"));

		// trying to send a fake id ..
		UserDto fakeIdDto = new UserDto();
		fakeIdDto.setUserDto_id(555L);
		fakeIdDto.setName("Fake Luis");
		fakeIdDto.setEmail("luis@fakemail.com");

		Long idfake = userService.createUpdateUser(fakeIdDto);

		assertNull(idfake);

	}
	
	/**
	 * 
	 * @param listArticleDto
	 */
	private void loggerListArticles(List<ArticleDto> listArticleDto) {
		logger.info(">>>> listArticles.size -> " + (listArticleDto == null ? null : listArticleDto.size()));
		for (ArticleDto articleDto : listArticleDto) {
			logger.info(">>>>>>>> " + articleDto);
		}
	}

	@Test
	public void test006_getUserArticles() {

		// el usuario escribió 4 artículos
		List<ArticleDto> listArticleDto = userService.getUserArticles(1L);
		loggerListArticles(listArticleDto);

		assertTrue(listArticleDto.size() == 4);

		// el usuario no escribió ningún artículo => retorna list.size()==0
		listArticleDto = userService.getUserArticles(5L);
		assertTrue(listArticleDto.isEmpty());

		// el usuario no existe => retorna null
		listArticleDto = userService.getUserArticles(55L);
		assertNull(listArticleDto);

		// el usuario es null => retorna null
		listArticleDto = userService.getUserArticles(null);
		assertNull(listArticleDto);

	}

	@Test
	@Sql(scripts = { "classpath:clean-up.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void test007_removeUser() {

		UserDto userDto = userService.getUserById(1L);
		assertNotNull(userDto);
		userService.removeUser(userDto.getUserDto_id());
		assertFalse(userService.existUser(userDto.getUserDto_id()));

	}

}
