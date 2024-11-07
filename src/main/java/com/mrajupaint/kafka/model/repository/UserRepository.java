package com.mrajupaint.kafka.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mrajupaint.kafka.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
