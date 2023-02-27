package com.fe.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fe.model.Category;
import com.fe.service.CommonService;

@Service
public class CategoryServiceImpl implements CommonService<Category> {

	@Autowired
	private RestTemplate restTemplate;

	private String RestUrl;

	public CategoryServiceImpl(RestTemplate restTemplate, @Value("${urlCommon}") String restUrl) {
		super();
		this.restTemplate = restTemplate;
		this.RestUrl = restUrl;
	}

	public List<Category> get(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer " +  token);
		System.out.println(headers);
		HttpEntity<Void> requestEntity = new HttpEntity<Void>(headers);
		ResponseEntity<List<Category>> response = restTemplate.exchange(RestUrl + "category", HttpMethod.GET,
				requestEntity, new ParameterizedTypeReference<List<Category>>() {
				});
		return response.getBody();
	}

	public Category getbyId(int id, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(Category dto, String token) {
		// TODO Auto-generated method stub

	}

	public void update(Category dto, int id, String token) {
		// TODO Auto-generated method stub

	}

	public void delete(int id, String token) {
		// TODO Auto-generated method stub

	}

	public Category getbyName(String str, String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
