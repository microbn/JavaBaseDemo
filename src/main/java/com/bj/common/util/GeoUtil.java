package com.bj.common.util;

import java.util.HashMap;
import java.util.Map;


public class GeoUtil {

	
	private static int numbits = 20;//对经纬度划分20次
	private static char[] digits = {'0','1','2','3','4','5','6','7','8','9','b', 'c', 'd', 'e',
		'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p','q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private final static Map<Character, Integer> lookup = new HashMap<Character, Integer>();
	static{
		int i=0;
		for(char c : digits){
			lookup.put(c, i++);
		}
	}
	private static double down_lat = -90;
	private static double up_lat = 90;
	private static double down_lng = -180;
	private static double up_lng = 180;
	
	//划分经纬度
		private static byte[] getBits(double loc ,double floor,double ceiling){
			byte[] buf = new byte[numbits];
			for(int i=0 ;i< numbits; i++){
				double mid = (floor + ceiling) /2;
				if(loc >= mid){//位于高区
					buf[i] = 0x1;
					ceiling = mid;
				}else{//位于低区
					buf[i] = 0x0;
					floor = mid;
				}
			}
			return buf;
		}
		//将经纬度转化为长整型
		private static long getBits(byte[] bits){
			long rs = 0;
			int lenth = bits.length;
			for(int i=0 ;i<bits.length;i++){
				Long temp = (long) bits[i];
				rs += temp << (lenth -i-1);
			}
			
			return rs;
		}
		//对长整型数据进行32位编码
		private static String base32(long src){
			char[] buf = new char[numbits*2];
			int index = 0;
			System.out.println(Long.toBinaryString(src));
			while(src >= 32){
				int current = (int) (src %32);
				buf[index++] = digits[current];
				src = src >>5;
			}
			buf[index] = digits[(int)src];
			return new String(buf);
		}
		
		
		public static String geoHash(double lat,double lng){
			byte[] bits = getBits(lat, up_lat, down_lat);
			byte[] lng_bits = getBits(lng, up_lng, down_lng);
			byte[] dest = getEncode(lng_bits, bits);
			long value = getBits(dest);
			String base32 = base32(value);
			String reverse = StringUtil.reverse(base32);
			return reverse;
		}
		//将经纬度混合
		private static byte[] getEncode(byte[] lats,byte[] lngs){
			byte[] dest = new byte[numbits*2];
			for(int i=0;i <numbits *2;i++){
				int index = i/2;
				if(i%2==0){
					dest[i]=lats[index];
				}else{
					dest[i]=lngs[index];
				}
			}
			return dest;
		}
		
		
}
