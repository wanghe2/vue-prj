package com.wang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LayUIController {

	@RequestMapping("/getUser")
	public List<Map<String,String>>getUser(){
		Map<String, String>user=new HashMap<String, String>();
		List<Map<String,String>>users=new ArrayList<Map<String,String>>();
		setUser(user);
		users.add(user);
		return users;
	} 
	
	public void setUser(Map<String,String> user) {
		user.put("id", "id");
		user.put("username", "username");
		user.put("experience", "experience");
		user.put("sex", "sex");
		user.put("score", "score");
		user.put("city", "city");
		user.put("sign", "sign");
		user.put("classify", "classify");
		user.put("wealth", "wealth");
	}
	
}
