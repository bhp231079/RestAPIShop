package com.mvc.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.mvc.entity.Category;
import com.mvc.entity.Product;
import com.mvc.entity.Type;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;
import com.mvc.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductAPI {
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> add(Product product, @RequestParam(name = "idType") int idType,
			@RequestParam(name = "file") MultipartFile file) throws IOException {
		try {
			Path path = Paths.get("C:\\Users\\bhphh\\eclipse-workspace\\MVCshop\\src\\main\\webapp\\WEB-INF\\img\\")
					.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			Type type = new Type(idType);
			product.setType(type);
			product.setUrlImage(file.getOriginalFilename());
			productService.add(product);
			return ResponseEntity.status(201).body(product);
		} catch (ObjectExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@GetMapping("/product/download/{fileName:.+}")
	public void showImg(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileName) {

		response.setContentType("image/jpeg");
		response.addHeader("Content-Disposition", "attachment;" + fileName);
		Path path = Paths.get("C:\\Users\\bhphh\\eclipse-workspace\\MVCshop\\src\\main\\webapp\\WEB-INF\\img\\")
				.resolve(fileName);
		try {
			Files.copy(path, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> get() {
		return ResponseEntity.status(200).body(productService.get());

	}

	@PostMapping(value = "/product/{id}")
	public ResponseEntity<Product> update(Product product, @RequestParam(name = "idType") int idType,
			@RequestParam(name = "file") MultipartFile file, @PathVariable int id) throws IOException {
			System.out.println(product.getName());
			Path path = Paths.get("C:\\Users\\bhphh\\eclipse-workspace\\MVCshop\\src\\main\\webapp\\WEB-INF\\img\\")
					.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			Type type = new Type(idType);
			product.setType(type);
			product.setUrlImage(file.getOriginalFilename());
			productService.update(product,id);
			return ResponseEntity.status(201).body(product);
		

	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		productService.delete(id);
		return ResponseEntity.status(200).body("Xóa thành công");
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getbyId(@PathVariable int id) {
		try {
			return new ResponseEntity<Product>(productService.getbyId(id), HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
