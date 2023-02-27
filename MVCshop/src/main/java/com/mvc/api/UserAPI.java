package com.mvc.api;

import java.util.List;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mvc.entity.User;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;
import com.mvc.service.UserService;


@RestController
@RequestMapping("/api")
public class UserAPI {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	@PostMapping("/user")
	public ResponseEntity<User> add(@RequestBody User user)  {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			userService.add(user);
			return ResponseEntity.status(201).body(user);
		} catch (ObjectExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> get() {
		return ResponseEntity.status(200).body(userService.get());

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable int id) {
		userService.update(user,id);
		return ResponseEntity.status(200).body(user);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		userService.delete(id);
		return ResponseEntity.status(200).body("Xóa thành công");
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getbyId(@PathVariable int id)  {
		try {
			return  new ResponseEntity<User>(userService.getbyId(id),HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	
	}
}
