package com.windcoder.jwtDemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.windcoder.jwtDemo.entity.User;

public class TokenUtil {
	/**
	 *
	 * withAudience():向Payload中添加要保存的信息
	 * @param user
	 * @return
	 */
	public  static String getToekn(User user){
		String token = "";
		token = JWT.create().withAudience(user.getUsername())
				.sign(Algorithm.HMAC256(user.getPassword()));
		return token;
	}
}
