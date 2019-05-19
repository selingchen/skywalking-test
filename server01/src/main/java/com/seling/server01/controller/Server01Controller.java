package com.seling.server01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seling.common.utils.HttpUtils;
import com.seling.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/server01")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Api(value = "Server01Api", description = "Server01Api")
public class Server01Controller {

	@Value("${server02.url}")
	private String server02Url;

	//http://127.0.0.1:7001/server01/getOrderInfo?orderId=1002&userId=1008
	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	@ApiOperation(value = "getOrderInfo", response = Result.class)
	public String getOrderById(@RequestParam String orderId, @RequestParam String userId) {
		String url ="http://"+ server02Url + "/server02/getOrderInfo?orderId="+orderId+"&userId="+userId;
		return  "Get form server02.getOrderById : "+ HttpUtils.getInstance().httpGet(url);
	}

}
