package com.bj.server.gsn;

import org.testng.annotations.Test;

import com.bj.common.util.HttpUtil;
import com.bj.common.util.JsonUtil;

public class FriendTest {

	private String ip = "127.0.0.1";
	private String remoteIp = "182.92.156.64";
	
	@Test
	public void friendSearch(){
		String url = "http://"+remoteIp+":8080/GSNServer/service/user/search";
		
		Search search = new Search();
		search.setContent("test");
		search.setGender("-1");
		
		String request = HttpUtil.sendPostRequest(url, JsonUtil.toJson(search, true));
		System.out.println(request);
		
		
	}
}
