package com.bj.server.gsn;

import org.testng.annotations.Test;

import com.bj.common.util.HttpUtil;
import com.bj.common.util.JsonUtil;

public class UserLogin {

	private String ip = "127.0.0.1";
	private String remoteIp = "182.92.156.64";
	@Test
	public void register(){
		String url = "http://"+ip+":8080/GSNServer/service/user/register";
		User user = new User();
		
		user.setNickName("我的token2dsd");
		user.setGender("1");
		user.setPasswd("root");
		user.setDeviceToken("dddd");
		String json = JsonUtil.toJson(user, true);
		System.out.println("----json:"+json);
		String request = HttpUtil.sendPostRequest(url, json);
		System.out.println("rs:"+request);
	}
	
	@Test
	public void login(){
		String url = "http://"+ip+":8080/GSNServer/service/user/login";
		User user = new User();
		
		user.setNickName("test_me");
		user.setPasswd("root");
		user.setPhoneType("1");
		String json = JsonUtil.toJson(user, true);
		System.out.println("----json:"+json);
		String request = HttpUtil.sendPostRequest(url, json);
		System.out.println("rs:"+request);
	}
}
