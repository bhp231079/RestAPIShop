package com.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {

	private int id;

	


	private String name;
	
	
	private Type type;
	
	private double price;
	
	private String description;
	
	private int quantity;
	
	private String urlImage;
	

}