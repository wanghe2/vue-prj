package com.wang.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
	
	private static List<Map<String, String>> students=new ArrayList<Map<String,String>>();
	static {
		Map<String,String>student1=new HashMap<String, String>();
		student1.put("name", "wanghe");
		student1.put("password", "123456");
		student1.put("introduce", "hell , i was wanghe");
		Map<String,String>student2=new HashMap<String, String>();
		student2.put("name", "wangjin");
		student2.put("password", "111111");
		student2.put("introduce", "i am a doctor");
		Map<String,String>student3=new HashMap<String, String>();
		student3.put("name", "caobingzhuang");
		student3.put("password", "123456");
		student3.put("introduce", "i sale my produce");
		Map<String,String>student4=new HashMap<String, String>();
		student4.put("name", "lengchunyang");
		student4.put("password", "123456");
		student4.put("introduce", "i live in qingdao");
		Map<String,String>student5=new HashMap<String, String>();
		student5.put("name", "gechao");
		student5.put("password", "123456");
		student5.put("introduce", "i study in Jilin");
		Map<String,String>student6=new HashMap<String, String>();
		student6.put("name", "zhaoyang");
		student6.put("password", "111111");
		student6.put("introduce", "i work in Lanzhou");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
	}
	
	public static List<Map<String,String>>getStudentInfo(){
		System.err.println("***************查询用户表，打印记录****************");
		return students;
	}
	
	public static List<Map<String,String>>getRoles(){
		List<Map<String, String>> roles=new ArrayList<Map<String,String>>();
		for(int i=0;i<10;i++) {
			Map<String,String>role=new HashMap<String, String>();
			role.put("code", "role"+i);
			role.put("name", "角色"+i);
			role.put("work", "职责"+i+" : 保卫家园，抵御外敌 ");
			roles.add(role);
		}
		System.err.println("***************查询角色表，打印记录****************");
		return roles;
	}
}
