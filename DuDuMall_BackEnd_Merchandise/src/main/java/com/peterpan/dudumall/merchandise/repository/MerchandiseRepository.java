package com.peterpan.dudumall.merchandise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.peterpan.dudumall.merchandise.entity.MerchandiseEntity;

public interface MerchandiseRepository extends MongoRepository<MerchandiseEntity, String> {

}
