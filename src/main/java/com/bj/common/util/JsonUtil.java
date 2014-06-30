package com.bj.common.util;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper om = new ObjectMapper();
	
	private static JsonFactory factory = new JsonFactory();
	
	private static Logger log = Logger.getLogger(JsonUtil.class);
	
	public static <T> Object fromJson(String jsonStr,Class<T> clazz){
		T value = null;
		try {
			value = om.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error("fromJson error,jsonStr:"+jsonStr+",class:"+clazz.getName()+",exception:"+e.getMessage());
		} 
		return value;
	}
	
	public static String toJson(Object obj, boolean prettyPrinter){
		StringWriter sw = new StringWriter();
		
		try {
			JsonGenerator jg = factory.createGenerator(sw);
			
			if(prettyPrinter){
			
				jg.useDefaultPrettyPrinter();
			
			}
			
			om.writeValue(jg, obj);
			
		} catch (IOException e) {
			log.error("toJson error,jsonObj:"+obj.getClass().getName()+",prettyPrinter:"+prettyPrinter+",exception:"+e.getMessage());
		}
		return sw.toString();
	}
}
