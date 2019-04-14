package com.seling.server03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server03")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Server03Controller {

	//http://127.0.0.1:7003/server03/getOrderInfo?orderId=1001
		@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
		@ResponseBody
		public String getOrderById(@RequestParam String orderId) {
			return "{\"orderId\": \""+orderId+"\",\"userAcct\": \"sa10001\"}";
		}

}
