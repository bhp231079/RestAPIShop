package com.mvc.api;

import java.util.List;

import org.apache.http.protocol.HTTP;
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

import com.mvc.entity.Category;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;
import com.mvc.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryAPI {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public ResponseEntity<Category> add(@RequestBody Category category)  {
		try {
			categoryService.add(category);
			return ResponseEntity.status(201).body(category);
		} catch (ObjectExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@GetMapping("/category")
	public ResponseEntity<List<Category>> get() {
		return ResponseEntity.status(200).body(categoryService.get());

	}

	@PutMapping("/category/{id}")
	public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable int id) {
		categoryService.update(category,id);
		return ResponseEntity.status(200).body(category);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		categoryService.delete(id);
		return ResponseEntity.status(200).body("Xóa thành công");
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getbyId(@PathVariable int id)  {
		try {
			return  new ResponseEntity<Category>(categoryService.getbyId(id),HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	
	}
}
