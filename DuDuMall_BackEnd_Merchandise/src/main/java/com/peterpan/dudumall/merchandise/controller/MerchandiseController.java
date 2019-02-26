package com.peterpan.dudumall.merchandise.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peterpan.dudumall.merchandise.entity.MerchandiseEntity;
import com.peterpan.dudumall.merchandise.service.MerchandiseService;
import com.peterpan.dudumall.merchandiseutil.JsonResult;
import com.peterpan.dudumall.merchandiseutil.ResultCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/merchandise")
@Slf4j
public class MerchandiseController {

	@Autowired
	private MerchandiseService merchandiseService;

	@RequestMapping(value = "/{merchandiseid}", method = { RequestMethod.GET })
	public JsonResult getMerchandiseById(@PathVariable("merchandiseid") String merchandiseid) throws Exception {
		MerchandiseEntity searchMerchandiseEntity = merchandiseService.findByMerchandiseId(merchandiseid);
		if (searchMerchandiseEntity != null) {
			log.info("获取商品信息成功, merchandisename = " + searchMerchandiseEntity.getMerchandiseName());
			return new JsonResult(ResultCode.SUCCESS, "获取商品信息成功", searchMerchandiseEntity);
		} else {
			log.info("获取商品信息失败");
			return new JsonResult(ResultCode.DATANOTFOUND, "获取商品信息失败");
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/", method = { RequestMethod.POST })
	public JsonResult addMerchandise(@RequestBody @Valid MerchandiseEntity merchandiseEntity) throws Exception {
		MerchandiseEntity addMerchandiseEntity = merchandiseService.addMerchandise(merchandiseEntity);
		if (addMerchandiseEntity != null) {
			log.info("增加商品成功, merchandisename = " + addMerchandiseEntity.getMerchandiseName());
			return new JsonResult(ResultCode.SUCCESS, "增加商品成功", addMerchandiseEntity);
		} else {
			log.info("增加商品失败");
			return new JsonResult(ResultCode.FAIL, "增加商品失败", addMerchandiseEntity);
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/", method = { RequestMethod.PUT })
	public JsonResult updateMerchandise(@RequestBody @Valid MerchandiseEntity merchandiseEntity) throws Exception {
		MerchandiseEntity searchMerchandiseEntity = merchandiseService.findByMerchandiseId(merchandiseEntity.getMerchandiseId());
		if (searchMerchandiseEntity != null) {
			log.info("获取商品信息成功, merchandisename = " + searchMerchandiseEntity.getMerchandiseName());
			MerchandiseEntity updateMerchandiseEntity = merchandiseService.updateByMerchandiseId(merchandiseEntity.getMerchandiseId(), merchandiseEntity);
			log.info("更新商品信息成功, merchandisename = " + updateMerchandiseEntity.getMerchandiseName());
			return new JsonResult(ResultCode.SUCCESS, "更新商品成功", updateMerchandiseEntity);
		} else {
			log.info("更新商品失败，未获取到对应MerchandiseId的商品");
			return new JsonResult(ResultCode.DATANOTFOUND, "未获取到对应MerchandiseId的商品");
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/{merchandiseId}", method = { RequestMethod.DELETE })
	public JsonResult deleteMerchandise(@PathVariable("merchandiseId") String merchandiseId) throws Exception {
		boolean result = merchandiseService.deleteByMerchandiseId(merchandiseId);
		if (result) {
			log.info("删除Merchandise成功, merchandiseId = " + merchandiseId);
			return new JsonResult(ResultCode.SUCCESS, "删除Merchandise成功", result);
		} else {
			log.info("删除Merchandise失败, merchandiseId = " + merchandiseId);
			return new JsonResult(ResultCode.FAIL, "删除Merchandise失败", result);
		}
	}

}
