package com.windcoder.jwtDemo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.windcoder.jwtDemo.annotation.PassToken;
import com.windcoder.jwtDemo.annotation.UserLoginToken;
import com.windcoder.jwtDemo.entity.User;
import com.windcoder.jwtDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
		String token = request.getHeader("token");

		// 非映射到方法直接通过
		if (!(object instanceof HandlerMethod)){
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()){
				return true;
			}
		}

		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				// 执行验证
				if (token == null) {
					throw new RuntimeException("无token，请重新登录");
				}
				String username;
				try {
					username = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException e) {
					throw new RuntimeException("401");
				}
				User user = userService.findByUsername(username);
				if (user == null) {
					throw new RuntimeException("用户不存在，请重新登录");
				}
				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					throw new RuntimeException("401");
				}
				return true;
			}
		}
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

	}
}
