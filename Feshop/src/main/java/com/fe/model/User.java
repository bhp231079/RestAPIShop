package com.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int id;

	
	private String firstName;

	
	private String lastName;

	
	private String email;

	private String phone;
	
	private String address;
	
	
	private String role;
	
	private String password;
	
	
	
}
