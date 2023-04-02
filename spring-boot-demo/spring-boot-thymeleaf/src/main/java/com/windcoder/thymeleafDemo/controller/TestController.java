package com.windcoder.thymeleafDemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", defaultValue = "springboot-test") String name){

		return "hello "+ name;
	}
}
