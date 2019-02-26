package com.peterpan.dudumall.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class UserEntity {
	@Id
	private String userid;
	@Field
	private String username;
	@Field
	private String mobile;
	@Field
	private String country; 
	@Field
	private String province; 
	@Field
	private String city;
	
}
