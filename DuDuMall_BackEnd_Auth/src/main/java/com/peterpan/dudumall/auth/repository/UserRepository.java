package com.peterpan.dudumall.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.peterpan.dudumall.auth.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
