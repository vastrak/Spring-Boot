package com.vastrak.springboot001.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrak.springboot001.Application;
import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.service.UserService;
import com.vastrak.springboot001.validators.error.ErrorMessages;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc userController;

	@Test
	public void test001_getUserById_NormalId() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setUserDto_id(50L);
		userDto.setName("Pablo");
		userDto.setEmail("pablo@clavounpablito.com");
		userDto.setArticles(0);

		when(userService.getUserById(50L)).thenReturn(userDto);

		this.userController.perform(get("/users/50")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.totalRecords", is(1))).andExpect(jsonPath("$.playload[0].userDto_id", is(50)))
				.andExpect(jsonPath("$.playload[0].name", is("Pablo")))
				.andExpect(jsonPath("$.playload[0].email", is("pablo@clavounpablito.com")))
				.andExpect(jsonPath("$.playload[0].articles", is(0)));

	}

	@Test
	public void test002_getUserById_NegativeId() throws Exception {

		this.userController.perform(get("/users/-5")).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())));

	}

	@Test
	public void test003_getUserById_AlphanumericId() throws Exception {

		this.userController.perform(get("/users/hola")).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())));
	}

	@Test
	public void test004_getUsers() throws Exception {

		UserDto user1 = new UserDto();
		user1.setUserDto_id(1L);
		user1.setName("Pablo");
		user1.setEmail("pablo@clavounpablito.com");
		user1.setArticles(0);

		UserDto user2 = new UserDto();
		user2.setUserDto_id(2L);
		user2.setName("Luis");
		user2.setEmail("luis@notengowifi.com");
		user2.setArticles(4);

		List<UserDto> listUserDto = new ArrayList<>();
		listUserDto.add(user1);
		listUserDto.add(user2);

		when(userService.getUsers()).thenReturn(listUserDto);

		this.userController.perform(get("/users/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.totalRecords", is(2))).andExpect(jsonPath("$.playload[0].userDto_id", is(1)))
				.andExpect(jsonPath("$.playload[0].name", is("Pablo")))
				.andExpect(jsonPath("$.playload[1].userDto_id", is(2)));

	}

	@Test
	public void test005_getUsersEmptyList() throws Exception {

		// empty list!
		List<UserDto> listUserDto = new ArrayList<>();

		when(userService.getUsers()).thenReturn(listUserDto);

		this.userController.perform(get("/users/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.totalRecords", is(0)));
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cuando se pretende crear un usuario nuevo el DTO debe traer el índice en null
	 * y el resto de los campos completos: nombre y email.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test006_createUserIdNotNull() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setUserDto_id(5L); // <- en usuarios nuevos debe ser null!
		userDto.setName(null);  // <- resto de los campos en null
		userDto.setEmail(null);

		// por las dudas!
		when(userService.createUpdateUser(userDto)).thenReturn(1L);

		this.userController.perform(post("/users/add/").content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is(ErrorMessages.USERDTO_FIELD_ERROR)))
				.andExpect(jsonPath("$.errors[0]", is(ErrorMessages.USERDTO_ID_MUST_BE_NULL)))
				.andExpect(jsonPath("$.errors[1]", is(ErrorMessages.USERDTO_FIELD_WRONG_FORMAT)));

	}
	
	@Test
	public void test007_createUserBadlyFormattedFields() throws Exception {
		
		UserDto userDto = new UserDto();
		userDto.setUserDto_id(null); // <- null => se trata de un usuario nuevo
		userDto.setName("");  // <- resto de los campos mal formateados o incompletos
		userDto.setEmail("nonam:e_notmail.com");
		
		when(userService.createUpdateUser(userDto)).thenReturn(1L);
		
		this.userController.perform(post("/users/add/").content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
				.andExpect(jsonPath("$.message", is(ErrorMessages.USERDTO_FIELD_ERROR)))
				.andExpect(jsonPath("$.errors[0]", is(ErrorMessages.USERDTO_FIELD_WRONG_FORMAT)));
		
	}
	
	/**
 	 *
	 * @throws Exception
	 */
	
	@Test
	public void test008_createUserOk() throws Exception {
		
		UserDto userDto = new UserDto();
		userDto.setUserDto_id(null);
		userDto.setName("Luis");  
		userDto.setEmail("luis@notengoemail.com");
	
		// no es el mismo objeto el que creamos aquí, que el que se 
		// ejecuta en el controlador, por eso uso any UserDto.class
		// de lo contrario el mock retorna 0
		when(userService.createUpdateUser(any(UserDto.class))).thenReturn(3L);
		
		this.userController.perform(post("/users/add/").content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalRecords", is(1)))
				.andExpect(jsonPath("$.playload[0].userDto_id", is(3)))
				.andExpect(jsonPath("$.playload[0].name", is("Luis")))
				.andExpect(jsonPath("$.playload[0].email", is("luis@notengoemail.com")))
				.andExpect(jsonPath("$.playload[0].articles", is(0)));
	}
	
	
	
	
	

}
