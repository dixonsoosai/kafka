package com.mrajupaint.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic addUser() {
		return new NewTopic("addUser", 1, (short) 1);
	}
	
	@Bean
	public NewTopic addUsers() {
		return new NewTopic("addUsers", 1, (short) 1);		
	}
}
