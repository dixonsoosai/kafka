package com.mrajupaint.kafka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document("User")
@Data
public class User {

	@Id
	private String id;
	@Field("first_name")
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	@Field("last_name")
	private String lastName;
	private int age;
	private String gender;
	@JsonProperty("ip_address")
	@Field("ip_address")
	private String ipAddress;
	private int balance;
	private int debit;
	
}
