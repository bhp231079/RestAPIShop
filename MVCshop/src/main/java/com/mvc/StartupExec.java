package com.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mvc.entity.User;
import com.mvc.exception.ObjectExistException;
import com.mvc.service.UserService;

@Component
public class StartupExec {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() throws ObjectExistException {
		if (userService.getbyEmail("bhphhuyen@gmail.com") == null) {
			User user = new User();
			user.setLastName("Phat");
			user.setFirstName("Bui");
			user.setPassword(encoder.encode("123456"));
			user.setPhone("0928644923");
			user.setAddress("KH");
			user.setRole("ROLE_ADMIN");
			user.setEmail("bhphhuyen@gmail.com");
			userService.add(user);
		}
		

	}
}
