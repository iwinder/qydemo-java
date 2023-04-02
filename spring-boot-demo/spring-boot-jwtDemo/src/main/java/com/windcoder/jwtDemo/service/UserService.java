package com.windcoder.jwtDemo.service;

import com.windcoder.jwtDemo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

	private static Map<String, User> users = new HashMap<String, User>();

	static {
		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("admin");
		user1.setPassword("888888");;
		users.put(user1.getUsername(), user1);
	}

	public User findByUsername(String username){
		return users.get(username);
	}
}
