package com.windcoder.springBootKafka.service;

import com.windcoder.springBootKafka.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {
	@Autowired
	private KafkaSender KafkaSender;

	public void addKafkaContent(String content){
		KafkaSender.send(content);
	}
}
