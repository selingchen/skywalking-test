package com.seling.server04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server04")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Server04Controller {

	//http://127.0.0.1:7004/server04/getUserInfo?userId=1001
		@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
		@ResponseBody
		public String getUserInfoById(@RequestParam String userId) {
			return "{\"userId\": \""+userId+"\",\"userName\": \""+userId+"\"}";
		}

}
