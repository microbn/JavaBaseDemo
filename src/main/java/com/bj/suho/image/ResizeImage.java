package com.bj.suho.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 对图片的大小进行修改
 * @author binzhang205916
 *
 */
public class ResizeImage {

	
	/**
	 * 修改图片大小
	 * @param im 原图
	 * @param mulriple 要缩小的参数，0.5 即缩小为原来的一半，2将图片扩大一倍
	 * @return 缩小后的图
	 */
	public static BufferedImage zoomImage(BufferedImage im ,float mulriple){
			int width = im.getWidth();
			int height = im.getHeight();
			int toWidth = (int) (width * mulriple);
			int toHeight = (int) (height * mulriple);
			
			BufferedImage bufImage = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_BGR);
			
			bufImage.getGraphics().drawImage(im.getScaledInstance(toWidth,toHeight , java.awt.Image.SCALE_SMOOTH),0,0,null);
			
			
			return bufImage;
	}
	
	
	public static BufferedImage getBufferImage(String path){
		File file = new File(path);
		BufferedImage bufferedImage = null;
		try {
			 bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
	
	public static void writeBufferedImageToDisk(BufferedImage im,String path,String format){
		
		File file = new File(path);
		
		try {
			ImageIO.write(im, format, file);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String src = "E:\\temp\\src.jpg";
		String dest = "E:\\temp\\zoomDest2.jpg";
		String format = "jpg";
		
		BufferedImage srcImage = getBufferImage(src);
		
		BufferedImage destImage = zoomImage(srcImage, 0.25f);
		
		writeBufferedImageToDisk(destImage, dest, format);
		
		
	}
}
