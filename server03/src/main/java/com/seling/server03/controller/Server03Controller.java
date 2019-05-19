package com.seling.server03.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seling.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/server03")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Api(value = "Server03Api", description = "Server03Api")
public class Server03Controller {

	//http://127.0.0.1:7003/server03/getOrderInfo?orderId=1001
	@ApiOperation(value = "getOrderInfo", response = Result.class)
	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	public String getOrderById(@RequestParam String orderId) {
		return "{\"orderId\": \"" + orderId + "\",\"userAcct\": \"sa10001\"}";
	}

}
