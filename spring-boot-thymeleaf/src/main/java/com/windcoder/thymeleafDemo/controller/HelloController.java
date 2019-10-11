package com.windcoder.thymeleafDemo.controller;

import com.windcoder.thymeleafDemo.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/a")
public class HelloController {

	@RequestMapping("/hello")
	public String hello(Model model,  @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name){
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping("/index")
	public String hello(Model model){
		Person person = new Person();
		person.setName("张三");
		person.setAge(22);

		List<Person> people = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setName("李四");
		p1.setAge(23);
		people.add(p1);

		Person p2 = new Person();
		p2.setName("王五");
		p2.setAge(24);
		people.add(p2);

		Person p3 = new Person();
		p3.setName("赵六");
		p3.setAge(25);
		people.add(p3);
		model.addAttribute("person", person);
		model.addAttribute("people", people);

		return "index";
	}
}
