package com.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	private int id;

	
	public Category(int id) {
		super();
		this.id = id;
	}


	
	private String name;

	
}
