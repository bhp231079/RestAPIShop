package com.fe.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fe.model.Category;
import com.fe.service.CommonService;

@Controller
public class CategoryController {
	@Autowired
	private CommonService<Category> commonService;

	@RequestMapping("/category")
	public String get(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("jwt")) {
				List<Category> categories = commonService.get(cookie.getValue());
				req.setAttribute("categories", categories);
				break;
			}
		}

		return "category/list";
	}

}
