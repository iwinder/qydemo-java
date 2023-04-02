package com.windcoder.webservice.service;

import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class HelloService {

	public String sayHello(String name,String msg){
		log.error("name:= {},msg={}",name,msg);
		return "name:="+name+"msg="+msg;
	}
}
