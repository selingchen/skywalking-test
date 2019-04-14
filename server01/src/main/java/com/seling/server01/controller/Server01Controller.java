package com.seling.server01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.seling.utils.HttpUtils;

@Controller
@RequestMapping("/server01")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Server01Controller {

	@Value("${server02.url}")
	private String server02Url;

	//http://127.0.0.1:7001/server01/getOrderInfo?orderId=1002&userId=1008
	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getOrderById(@RequestParam String orderId, @RequestParam String userId) {
		String url ="http://"+ server02Url + "/server02/getOrderInfo?orderId="+orderId+"&userId="+userId;
		return  "Get form server02.getOrderById : "+ HttpUtils.getInstance().httpGet(url);
	}

}
