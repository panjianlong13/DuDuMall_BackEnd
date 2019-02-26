package com.peterpan.dudumall.merchandise.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MerchandiseEntity {
	@Id
	private String merchandiseId;
	@Field
	private String merchandiseName;
	@Field
	private double price;
	@Field
	private String merchandiseDescription; 
	@Field
	private int merchandiseCategory; 
	
}
