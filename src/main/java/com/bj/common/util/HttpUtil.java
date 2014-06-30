package com.bj.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class HttpUtil {

    public static String httpRequest(String urlString, HashMap<String, String> headerMap, int connectionTimeout, int readTimeout){
        StringBuilder sb = null;
        InputStream input = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;

        try {
        URL url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(connectionTimeout);
                connection.setReadTimeout(readTimeout);

        connection.setDoOutput(true);
        connection.setUseCaches(false);

        if(headerMap != null){
                @SuppressWarnings("rawtypes")
                        Iterator iter = headerMap.keySet().iterator();
                while(iter.hasNext()){
                        String key = (String) iter.next();
                        String value = (String) headerMap.get(key);
                        connection.setRequestProperty(key, value);
                }
        }

        String line = null;
        input = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        sb = new StringBuilder();;

                while ((line = reader.readLine()) != null) {
                  sb.append(line + "\n");
                }
        } catch (Exception e) {

        	System.out.println(e.getMessage());
            return null;
    }finally{
            close(input);
            close(reader);
            connection.disconnect();
    }

    return sb.toString();
}
    public static String sendPostRequest(String url,byte[] buf){
    	 StringBuilder sb = new StringBuilder();
         InputStream inputStream = null;
         BufferedReader reader = null;
         HttpURLConnection connection = null;
         OutputStream outputStream = null;

         try {
         URL postUrl = new URL(url);
         connection = (HttpURLConnection) postUrl.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Proxy-Connection", "Keep-Alive");
         connection.setRequestProperty("Content-Type", "application/json");
         connection.setDoOutput(true);
         connection.setDoInput(true);
         connection.setUseCaches(false);
//         connection.
         outputStream = connection.getOutputStream();
         outputStream.write(buf);
         outputStream.flush();
         

         int responseCode = connection.getResponseCode();
         System.out.println("----responseCode:"+responseCode);
         if(responseCode==HttpURLConnection.HTTP_OK){
       	  inputStream = connection.getInputStream();
           reader = new BufferedReader(new InputStreamReader(inputStream));
           String line = null;
           while ((line = reader.readLine()) != null) {
                     sb.append(line + "\n");
                   }
         }
         }catch(Exception e){
       	  System.out.println(e.getMessage());
       	  e.printStackTrace();
         }finally{
       	  close(inputStream);
       	  close(outputStream);
       	  if(connection!=null){
       		  connection.disconnect();
       	  }
         }
   	
   	return sb.toString();
    }
    public static String sendPostRequest(String url,String content){
    	
    	 byte[] buf = null;
    	 try {
			buf = content.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
    	 return sendPostRequest(url, buf);
    }
		public static void close(Closeable is) {
		    if (is != null){
		            try {
		                    is.close();
		            } catch (IOException e)
		            {
		                    System.out.println(e.getMessage());
		            }
		    }
}

}
