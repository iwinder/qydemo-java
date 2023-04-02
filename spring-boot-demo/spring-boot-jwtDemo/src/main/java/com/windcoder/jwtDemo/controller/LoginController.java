package com.windcoder.jwtDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.windcoder.jwtDemo.annotation.UserLoginToken;
import com.windcoder.jwtDemo.entity.User;
import com.windcoder.jwtDemo.service.UserService;
import com.windcoder.jwtDemo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LoginController {
	@Autowired
	UserService userService;

	//登录
	@PostMapping("/login")
	public Object login(@RequestBody User user){
		JSONObject jsonObject=new JSONObject();
		User userForBase=userService.findByUsername(user.getUsername());
		if(userForBase==null){
			jsonObject.put("message","登录失败,用户不存在");
			return jsonObject;
		}else {
			if (!userForBase.getPassword().equals(user.getPassword())){
				jsonObject.put("message","登录失败,密码错误");
				return jsonObject;
			}else {
				String token = TokenUtil.getToekn(userForBase);
				jsonObject.put("token", token);
				jsonObject.put("user", userForBase);
				return jsonObject;
			}
		}
	}
	@UserLoginToken
	@GetMapping("/getMessage")
	public String getMessage(){
		return "你已通过验证";
	}
}
