package com.peterpan.dudumall.merchandise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.peterpan.dudumall.merchandise.entity.MerchandiseEntity;
import com.peterpan.dudumall.merchandise.repository.MerchandiseRepository;
import com.peterpan.dudumall.merchandise.util.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MerchandiseService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MerchandiseRepository respository;

	public MerchandiseEntity addMerchandise(MerchandiseEntity merchandiseEntity) {
		MerchandiseEntity result = respository.save(merchandiseEntity);
		return result;
	}

	public MerchandiseEntity findByMerchandiseId(String merchandiseId) {
		Query query = new Query(Criteria.where("merchandiseId").is(merchandiseId));
		MerchandiseEntity merchandiseEntity = mongoTemplate.findOne(query, MerchandiseEntity.class);
		return merchandiseEntity;
	}

	public MerchandiseEntity updateByMerchandiseId(String merchandiseId, MerchandiseEntity merchandiseEntity) {
		Query query = new Query(Criteria.where("merchandiseId").is(merchandiseId));
		Update update = new Update();
		update.set("merchandiseId", merchandiseEntity.getMerchandiseId());
		update.set("merchandiseName", merchandiseEntity.getMerchandiseName());
		update.set("price", merchandiseEntity.getPrice());
		update.set("merchandiseDescription", merchandiseEntity.getMerchandiseDescription());
		update.set("merchandiseCategory", merchandiseEntity.getMerchandiseCategory());
		mongoTemplate.updateFirst(query, update, MerchandiseEntity.class);
		return findByMerchandiseId(merchandiseId);
	}

	public boolean deleteByMerchandiseId(String merchandiseId) {
		MerchandiseEntity searchMerchandiseEntity = findByMerchandiseId(merchandiseId);
		if (searchMerchandiseEntity == null) {
			log.info("获取商品信息失败");
			return false;
		}
		log.info("获取用户商品成功, merchandiseName = " + searchMerchandiseEntity.getMerchandiseName());
		Criteria criteria = Criteria.where("merchandiseId").is(merchandiseId);
		Query query = new Query(criteria);
		mongoTemplate.remove(query, MerchandiseEntity.class);
		return true;
	}
}
