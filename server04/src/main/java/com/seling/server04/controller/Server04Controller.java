package com.seling.server04.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.seling.common.utils.BuildResponse;
import com.seling.common.utils.Result;
import com.seling.server04.entity.OrderInfoEntity;
import com.seling.server04.exception.SeverException;
import com.seling.server04.form.OrderInfoForm;
import com.seling.server04.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/server04")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Api(value = "Server04Api", description = "Server04Api")
public class Server04Controller {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "getUserInfo", response = Result.class)
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public String getUserInfoById(@RequestParam String userId) {
		log.info("----------is test----------");
		return "{\"userId\": \"" + userId + "\",\"userName\": \"" + userId + "\"}";
	}

	//http://127.0.0.1:7004/server04/getUserInfo?userId=1001
	@ApiOperation(value = "getUserInfoByUserId", response = Result.class)
	@RequestMapping(value = "/getUserInfoByUserId", method = RequestMethod.GET)
	public Result getUserInfoByUserId(@RequestParam String userId) {
		log.info("----------is test----------");
		String tmp = "{\"userId\": \"" + userId + "\",\"userName\": \"" + userId + "\"}";
		return BuildResponse.buildSuccessResult("success", tmp);
	}

	@ApiOperation(value = "getUser", response = Result.class)
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public PageInfo<OrderInfoEntity> getOrderInfoByPage(@RequestBody @Valid OrderInfoForm orderInfoForm,
			BindingResult bindingResult) throws Exception {
		log.info("----------is test----------");
		if (bindingResult.hasErrors())
			throw new SeverException("1", bindingResult.getFieldErrors().toString());
		return orderService.getOrderInfo(orderInfoForm);

	}

}
