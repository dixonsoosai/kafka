package com.mrajupaint.kafka.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrajupaint.kafka.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class SendController {


	KafkaTemplate<String, User> kafkaTemplate;
	
	@PostMapping("add")
	public ResponseEntity<String> send(@RequestBody User user,
			@RequestParam("topic") String topic) throws InterruptedException, ExecutionException {
		try {
			Message<User> message = MessageBuilder.withPayload(user)
					.setHeader(KafkaHeaders.TOPIC, topic).build();
			var response = kafkaTemplate.send(message).get();
			log.info("Response: {}", response);
		}
		catch(Exception e) {
			log.error("Exception: ", e);
		}

		return new ResponseEntity<>("Send successfully", HttpStatus.OK);
		
	}
	
}
