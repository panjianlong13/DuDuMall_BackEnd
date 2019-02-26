package com.peterpan.dudumall.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peterpan.dudumall.user.entity.UserEntity;
import com.peterpan.dudumall.user.service.UserService;
import com.peterpan.dudumall.user.util.JsonResult;
import com.peterpan.dudumall.user.util.ResultCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userid}", method = { RequestMethod.GET })
	public JsonResult getUserById(@PathVariable("userid") String userid,
			@RequestHeader(value = "Authorization", required = false) String token) throws Exception {
		UserEntity searchUserEntity = userService.findByUserId(userid);
		if (searchUserEntity != null) {
			log.info("获取用户信息成功, username = " + searchUserEntity.getUsername());
			return new JsonResult(ResultCode.SUCCESS, "获取用户信息成功", searchUserEntity);
		} else {
			log.info("获取用户信息失败");
			return new JsonResult(ResultCode.DATANOTFOUND, "获取用户信息失败");
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/", method = { RequestMethod.POST })
	public JsonResult addUser(@RequestBody @Valid UserEntity userEntity) throws Exception {
		UserEntity addUserEntity = userService.addUser(userEntity);
		if (addUserEntity != null) {
			log.info("增加User成功, username = " + addUserEntity.getUsername());
			return new JsonResult(ResultCode.SUCCESS, "增加User成功", addUserEntity);
		} else {
			log.info("增加User失败");
			return new JsonResult(ResultCode.FAIL, "增加User失败", addUserEntity);
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/", method = { RequestMethod.PUT })
	public JsonResult updateUser(@RequestBody @Valid UserEntity userEntity) throws Exception {
		UserEntity searchUserEntity = userService.findByUserId(userEntity.getUserid());
		if (searchUserEntity != null) {
			log.info("获取用户信息成功, username = " + searchUserEntity.getUsername());
			UserEntity updateUserEntity = userService.updateByUserId(userEntity.getUserid(), userEntity);
			log.info("更新用户信息成功, username = " + updateUserEntity.getUsername());
			return new JsonResult(ResultCode.SUCCESS, "更新User成功", updateUserEntity);
		} else {
			log.info("更新User失败，未获取到对应UserId的USER");
			return new JsonResult(ResultCode.DATANOTFOUND, "未获取到对应UserId的USER");
		}
	}

	// only for department Admin user
	@RequestMapping(value = "/{userid}", method = { RequestMethod.DELETE })
	public JsonResult deleteUser(@PathVariable("userid") String userid) throws Exception {
		boolean result = userService.deleteByUserId(userid);
		if (result) {
			log.info("删除User成功, userid = " + userid);
			return new JsonResult(ResultCode.SUCCESS, "删除User成功", result);
		} else {
			log.info("删除User失败, userid = " + userid);
			return new JsonResult(ResultCode.FAIL, "删除User失败", result);
		}
	}

}
