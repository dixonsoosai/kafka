package com.mrajupaint.kafka.model;

import java.util.List;

import lombok.Data;

@Data
public class UserWrapper {

	private List<User> users;
}
