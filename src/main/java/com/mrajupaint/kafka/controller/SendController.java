package com.mrajupaint.kafka.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrajupaint.kafka.model.User;
import com.mrajupaint.kafka.model.UserWrapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class SendController {


	KafkaTemplate<String, User> kafkaTemplate;
	
	@PostMapping("addUser")
	public ResponseEntity<String> send(@RequestBody User user) {
		try {
			var response = kafkaTemplate.send("addUser", user.getId(), user).get();
			log.info("Response: {}", response);
		}
		catch(Exception e) {
			log.error("Exception: ", e);	
		}

		return new ResponseEntity<>("Send successfully", HttpStatus.OK);
		
	}
	
	@PostMapping("addUsers")
	public ResponseEntity<String> send(@RequestBody List<User> user) {
		try {
			var users = new UserWrapper();
			users.setUsers(user);
			Message<UserWrapper> message = MessageBuilder.withPayload(users)
					.setHeader(KafkaHeaders.TOPIC, "addUsers").build();
			var response = kafkaTemplate.send(message).get();
			log.info("Response: {}", response.getRecordMetadata());
		}
		catch(Exception e) {
			log.error("Exception: ", e);	
		}

		return new ResponseEntity<>("Send successfully", HttpStatus.OK);
		
	}
}
