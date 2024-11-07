package com.mrajupaint.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mrajupaint.kafka.model.User;
import com.mrajupaint.kafka.model.UserWrapper;
import com.mrajupaint.kafka.model.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

	UserRepository userRepository;
	
	@KafkaListener(topics = "addUser")
	public synchronized void consumeTest(User message) {
		try {
			message.setId(getId());
			userRepository.save(message);
			log.info("User: {}",message);	
		}
		catch(Exception e) {
			log.error("Exception: ", e);
		}
	}
	
	@KafkaListener(topics = "addUsers")
	public synchronized void consumeTest(UserWrapper message) {
		try {
			var id = Integer.parseInt(getId());
			for(var user: message.getUsers()) {
				user.setId(String.valueOf(id));
				id++;
			}
			userRepository.saveAll(message.getUsers());
			log.info("User: {}",message.getUsers().size());	
		}
		catch(Exception e) {
			log.error("Exception: ", e);
		}
	}
	
	public synchronized String getId() {
		var maxId = userRepository.findAll().size() + 1;
		return String.valueOf(maxId);
	}
	
}
	