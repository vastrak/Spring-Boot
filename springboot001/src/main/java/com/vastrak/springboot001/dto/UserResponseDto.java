package com.vastrak.springboot001.dto;

import java.util.ArrayList;
import java.util.List;


public class UserResponseDto {
	
	private Integer totalRecords;
	private List<UserDto> playLoad;
	
	public UserResponseDto() {
		this.playLoad = new ArrayList<>();
		this.totalRecords = playLoad.size();
	}
	
	/**
	 * 	
	 * @param status HttpStatus
	 * @param playload List<UserDto>
	 */
	public UserResponseDto(List<UserDto> playload) {
		super();
		this.playLoad = playload;
		this.totalRecords = playload.size();
	}
	
	public List<UserDto> getPlayload() {
		return playLoad;
	}
	
	public void setPlayload(List<UserDto> playload) {
		this.playLoad = playload;
		this.totalRecords = playLoad.size();
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * Agrega un UserDto a la carga del UserResponseDto
	 * @param userDto
	 */
	public void addUserDto(UserDto userDto) {
		this.playLoad.add(userDto);
		this.totalRecords = playLoad.size();
	}
	
}
