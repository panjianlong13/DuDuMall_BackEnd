package com.peterpan.dudumall.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.peterpan.dudumall.user.entity.UserEntity;
import com.peterpan.dudumall.user.repository.UserRepository;
import com.peterpan.dudumall.user.util.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository respository;

	public UserEntity addUser(UserEntity userEntity) {
		UserEntity result = respository.save(userEntity);
		return result;
	}

	public UserEntity findByUserId(String userid) {
		Query query = new Query(Criteria.where("userid").is(userid));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}

	public UserEntity updateByUserId(String userid, UserEntity userEntity) {
		Query query = new Query(Criteria.where("userid").is(userid));
		Update update = new Update();
		update.set("username", userEntity.getUsername());
		update.set("mobile", userEntity.getMobile());
		update.set("password", userEntity.getPassword());
		update.set("role", userEntity.getRole());
		update.set("permission", userEntity.getPermission());
		update.set("country", userEntity.getCountry());
		update.set("province", userEntity.getProvince());
		update.set("city", userEntity.getCity());
		// update.set("imageUrl", userEntity.getImageUrl());
		mongoTemplate.updateFirst(query, update, UserEntity.class);
		return findByUserId(userid);
	}

	public boolean deleteByUserId(String userid) {
		UserEntity searchUserEntity = findByUserId(userid);
		if (searchUserEntity == null) {
			log.info("获取用户信息失败");
			return false;
		}
		log.info("获取用户信息成功, username = " + searchUserEntity.getUsername());
		Criteria criteria = Criteria.where("userid").is(userid);
		Query query = new Query(criteria);
		mongoTemplate.remove(query, UserEntity.class);
		return true;
	}
}
