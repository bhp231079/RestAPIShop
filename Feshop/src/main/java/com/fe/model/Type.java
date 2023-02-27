package com.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Type {
	
	private int id;

	private String name;


	public Type(int id) {
		super();
		this.id = id;
	}

	
	

}