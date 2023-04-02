package com.windcoder.springBootKafka.controller;

import com.windcoder.springBootKafka.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
	@Autowired
	private KafkaSenderService kafkaService;

	@GetMapping(value = "addKafka")
	public void  addKafkaMessage(String content){
		kafkaService.addKafkaContent(content);
	}


}
