package com.bj.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil2 {

	
	
	
	private static Logger log = Logger.getLogger(HttpUtil.class.getName());


    public static String post(String url){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject requestJson = null;
       
        String rs = null;
        try{
            StringEntity entity = new StringEntity(requestJson.toString());
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                rs = EntityUtils.toString(httpEntity);
            }

        }catch (Exception e){
            System.out.println("exception:"+e.getMessage());
            System.out.println("detail:"+e);
            log.error("postMessageException:"+e.getMessage(),e);
        }
        return rs;
    }

    public static String get(String url){
        String rs = null;
        HttpClient client = new DefaultHttpClient();

//        HttpClient client = AndroidHttpClient.newInstance("Android");
        HttpGet get = new HttpGet(url);
        try{
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                rs = EntityUtils.toString(httpEntity);
            }
            System.out.println("responseCode:"+response.getStatusLine());
        }catch (Exception e){
            System.out.println("exception:"+e.getMessage());
            log.error("postMessageException:"+e.getMessage(),e);
           e.printStackTrace();
        }

        return rs;
    }

}
