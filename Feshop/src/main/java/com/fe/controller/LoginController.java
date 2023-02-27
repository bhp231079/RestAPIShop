package com.fe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fe.service.impl.LoginServiceImpl;

@Controller
public class LoginController {

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@GetMapping(value = "/login")
	public String login() {
		return "auth/login";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			HttpServletResponse resp) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", email);
		param.put("password", password);
		String result = loginServiceImpl.getJWT(param);
		Cookie cookie = new Cookie("jwt", result);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60);
		cookie.setSecure(true);
		resp.addCookie(cookie);

		return "auth/login";

	}

}
