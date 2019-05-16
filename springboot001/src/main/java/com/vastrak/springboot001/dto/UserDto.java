package com.vastrak.springboot001.dto;


/**
 * 
 * @author Christian
 *
 */
public class UserDto {
	private Long userDto_id;
	private String name;
	private String email;
	private Integer articles;

	public UserDto() {
	}

	public Long getUserDto_id() {
		return userDto_id;
	}

	public void setUserDto_id(Long userDto_id) {
		this.userDto_id = userDto_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getArticles() {
		return articles;
	}

	public void setArticles(Integer articles) {
		this.articles = articles;
	}
}
