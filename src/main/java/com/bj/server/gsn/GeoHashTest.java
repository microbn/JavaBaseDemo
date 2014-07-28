package com.bj.server.gsn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bj.common.util.GeoUtil;


public class GeoHashTest {

	private Logger log = Logger.getLogger(getClass());
	
	public List<String> readLocFromFile(String path){
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		BufferedReader reader = null;
		try{
			 reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine())!=null){
				line = line.trim();
				if(line.length()>0){
					list.add(line);
				}
			}
		}catch(Exception e){
			log.error("readLocFromFile"+e.getMessage(),e);
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return list;
	}
	
	public void connectMysql(List<String> list){
		String url = "jdbc:mysql://182.92.156.64:3306/test?useUnicode=true&characterEncoding=utf8";
		
		String driver = "com.mysql.jdbc.Driver";
		String userName = "gsnUser";
		String passwd = "gsn";
		
		Connection connection = null;
		Statement statement = null;
		String sql = "insert into t_geo (userId,lat,lng,geo_loc) values ('ddddddd',";
		String _sql = ")";
		try{
			
			
			Class.forName(driver);
			 connection = DriverManager.getConnection(url, userName, passwd);
			
			 statement = connection.createStatement();
			
			 
			 for(String loc :list){
				 String[] split = loc.split(",");
				 double lat =  Double.valueOf(split[0].replace("(", "").trim());
				 double lng =  Double.valueOf(split[1].replace(")", "").trim());
				 StringBuffer buf = new StringBuffer(sql);
				 String geoHash = GeoUtil.geoHash(lat, lng);
				 buf.append(lat).append(",").append(lng).append(",'").append(geoHash).append("'").append(_sql);
				 String sql_temp = buf.toString();
				 log.info("exe sql:"+sql_temp);
				 statement.executeUpdate(sql_temp);
			 }
			
			
			
		}catch(Exception e){
			log.error("GeoHashTest,connectMysql:"+e.getMessage(),e);
		}finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		GeoHashTest test = new GeoHashTest();
		List<String> list = test.readLocFromFile("/Users/zhangbin/Downloads/filename.txt");
		test.connectMysql(list);
		
	}
}
