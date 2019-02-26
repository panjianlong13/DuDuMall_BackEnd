package com.peterpan.dudumall.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.peterpan.dudumall.user.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
