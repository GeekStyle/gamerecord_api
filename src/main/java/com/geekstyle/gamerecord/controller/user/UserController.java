package com.geekstyle.gamerecord.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekstyle.gamerecord.model.common.Response;
import com.geekstyle.gamerecord.model.user.User;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,headers={"Accept=application/json"})
	public @ResponseBody Response createUser(@RequestBody User user) {
		//TODO
		//user input back-end validation
		
		try{
			userService.createUser(user);
			
		}catch(Exception e) {
			e.printStackTrace();
			//TOTO
			//exception handle
			
		}
		
		//封装
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(null);
		return response;
	}
	
}
