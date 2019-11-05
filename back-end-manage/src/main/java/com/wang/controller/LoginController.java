package com.wang.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.db.Database;

@RestController
public class LoginController {

	@RequestMapping(value="/login")
	public Map<String, String> login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username= request.getParameter("username");
		String password=request.getParameter("password");
		Map<String, String>reponse_data=new HashMap<String, String>();
		reponse_data.put("flag", "fail");
		reponse_data.put("message", "username is not exist");
		List<Map<String, String>>students_db=Database.getStudentInfo();
		for(Map<String,String>student:students_db) {
			if(student.get("name").equals(username)) {
				if(student.get("password").equals(password)){
					reponse_data.put("flag", "success");
					reponse_data.put("message", "success login");
				}else {
					reponse_data.put("message", "password is error");
				}
				break;
			}
		}
		return reponse_data;
	}
	
	@RequestMapping(value="/userList")
	public List<Map<String, String>>getUserList(){
		return Database.getStudentInfo();
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String introduce=request.getParameter("introduce");
		Map<String,String>student=new HashMap<String, String>();
		student.put("name", username);
		student.put("password", password);
		student.put("introduce", introduce);
		List<Map<String, String>>students_db=Database.getStudentInfo();
		students_db.add(student);
		return "success";
	}
	
	@RequestMapping(value="/editUser")
	public String editUser(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String introduce=request.getParameter("introduce");
		List<Map<String, String>>students_db=Database.getStudentInfo();
		for(Map<String,String>student:students_db) {
			if(student.get("name").equals(username)) {
				student.put("password", password);
				student.put("introduce", introduce);
			}
		}
		return "success";
	}
	
	@RequestMapping(value="/delUser")
	public String delUser(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		List<Map<String, String>>students_db=Database.getStudentInfo();
		int index=0;
		Boolean flag=false;
		for(Map<String,String>student:students_db) {
			if(student.get("name").equals(username)) {
				flag=true;
				break;
			}
			index++;
		}
		if(flag) {
			students_db.remove(index);
		}
		return "success";
	}
	
	/**
	 * 解决跨域
	 * @param response
	 */
	public void cros(HttpServletResponse response) {

		// 指定允许其他域名访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		// 响应头设置
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
	}
	
	@RequestMapping("/rolelist")
	public List<Map<String,String>> getRoleList() {
		return Database.getRoles();
	}
	
}
