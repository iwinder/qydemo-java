package com.eindcoder.webservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class HelloService {

	public String sayHello(String name,String msg){
		log.error("name:= {},msg={}",name,msg);
		return "name:="+name+"msg="+msg;
	}
}
