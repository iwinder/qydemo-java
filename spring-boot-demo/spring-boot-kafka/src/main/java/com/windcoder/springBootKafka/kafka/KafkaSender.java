package com.windcoder.springBootKafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {
	@Autowired
	private KafkaTemplate kafkaTemplate;

	// 发送消息方法
	public void send(String body) {

		kafkaTemplate.send("testTopic", body);
		log.info("发送消息完成,内容 为:" + body);
	}

	@KafkaListener(topics = { "testTopic" })
	public void listen(ConsumerRecord<?, ?> record) {
		log.info("接收消息为:" + record.value());
	}
}
