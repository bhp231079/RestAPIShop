package com.mvc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mvc.entity.Type;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;
import com.mvc.service.TypeService;

@RestController
@RequestMapping("/api")
public class TypeAPI {
	@Autowired
	private TypeService typeService;

	@PostMapping("/type")
	public ResponseEntity<Type> add(@RequestBody Type type) {
		try {
			typeService.add(type);
			return ResponseEntity.status(201).body(type);
		} catch (ObjectExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@GetMapping("/type")
	public ResponseEntity<List<Type>> get() {
		return ResponseEntity.status(200).body(typeService.get());

	}

	@PutMapping("/type/{id}")
	public ResponseEntity<Type> update(@RequestBody Type type, @PathVariable int id) {
		typeService.update(type,id);
		return ResponseEntity.status(200).body(type);
	}

	@DeleteMapping("/type/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		typeService.delete(id);
		return ResponseEntity.status(200).body("Xóa thành công");
	}

	@GetMapping("/type/{id}")
	public ResponseEntity<Type> getbyId(@PathVariable int id) {
		try {
			return new ResponseEntity<Type>(typeService.getbyId(id), HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
