package com.fe.service.impl;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl {

	@Autowired
	private RestTemplate restTemplate;

	private String RestUrl;

	public LoginServiceImpl(RestTemplate restTemplate, @Value(value = "${urlCommon}") String restUrl) {

		this.restTemplate = restTemplate;
		this.RestUrl = restUrl;
	}

	public String getJWT(Map<String, String> params) {

		String result = restTemplate.postForObject(RestUrl + "login?username={username}&password={password}", null,
				String.class, params);

		return result;
	}

}
