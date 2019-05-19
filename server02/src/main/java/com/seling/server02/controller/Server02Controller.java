package com.seling.server02.controller;

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
@RequestMapping("/server02")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "Server02Api", description = "Server02Api")
@Slf4j
public class Server02Controller {

	@Value("${server03.url}")
	private String server03Url;
	
	@Value("${server04.url}")
	private String server04Url;

	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	@ApiOperation(value = "getOrderInfo", response = Result.class)
	public String getOrderById(@RequestParam String orderId, @RequestParam String userId) {
		String url03 ="http://"+ server03Url + "/server03/getOrderInfo?orderId="+orderId;
		String url04 ="http://"+ server04Url + "/server04/getUserInfo?userId="+userId;
		String res03 = HttpUtils.getInstance().httpGet(url03);
		String res04 = HttpUtils.getInstance().httpGet(url04);
		return  "["+res03 + "," + res04+"]";
	}

}
