package com.vastrak.springboot001.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vastrak.springboot001.Application;
import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes=Application.class)
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
		.andExpect(jsonPath("$.userDto_id", is(50)))
		.andExpect(jsonPath("$.name", is("Pablo")))
		.andExpect(jsonPath("$.email", is("pablo@clavounpablito.com")))   
		.andExpect(jsonPath("$.articles", is(0)));		   
		
	}
	
	@Test
	public void test002_getUserById_NegativeId() throws Exception {
		
		this.userController.perform(get("/users/-5")).andExpect(status().isBadRequest());		   
		
	}
	
	@Test
	public void test003_getUserById_AlphanumericId() throws Exception {
		
		this.userController.perform(get("/users/hola")).andExpect(status().isBadRequest());	
	}
	
	
}
