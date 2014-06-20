package com.bj.suho.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image2Byte {

	
	
	
	public static void convertByte2Image(byte[] bytes,String dest,String format){
		
		InputStream input = new ByteArrayInputStream(bytes);
		try {
			BufferedImage bufedImage = ImageIO.read(input);
			
			File output = new File(dest);
			
			ImageIO.write(bufedImage, format, output);
			
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----success----");
	}
	
	
	public static byte[] convertImage2Byte(String src,String format){
		byte[] bytes = null;
		File  input = new File(src);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			BufferedImage buffered = ImageIO.read(input);
			
			ImageIO.write(buffered, format, baos);
			
			baos.flush();
			
			bytes = baos.toByteArray();
			
			
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("-----bytes:"+bytes.length);
		
		return bytes;
	}
	
	public static void main(String[] args) {
		String src = "E:\\temp\\src.jpg";
		String dest = "E:\\temp\\dest.jpg";
		
		String format = "jpeg";
		
		byte[] buf = convertImage2Byte(src, format);
		
		convertByte2Image(buf, dest, format);
		
	}
}
