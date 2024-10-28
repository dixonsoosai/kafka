package com.mrajupaint.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mrajupaint.kafka.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {

	
	@KafkaListener(topics = "test")
	public void consumeTest(User message) {
		try {
			log.info("User: {}",message);	
		}
		catch(Exception e) {
			log.error("Exception: ", e);
		}
	}
	
	@KafkaListener(topics = "multi-test")
	public void consumeMultiTest(String message) {
		log.info(message);
	}
}
	