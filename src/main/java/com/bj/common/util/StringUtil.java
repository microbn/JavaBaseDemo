package com.bj.common.util;

public class StringUtil {

	//检测是否为空
	public static boolean isBlank(String str){
		if(str==null){
			return true;
		}
		if(str.length()==0){
			return true;
		}
		return false;
	}
	
	//字符串反转
	public static String reverse(String src){
		if(src==null){
				return null;
		}
		StringBuilder builder = new StringBuilder();
		char[] arrays = src.toCharArray();
		for(int i=arrays.length-1;i>=0;i--){
			builder.append(arrays[i]);
		}
		return builder.toString().trim();
	}
}
