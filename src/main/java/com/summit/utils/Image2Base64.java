package com.summit.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class Image2Base64 {

	/**
	 * 将图片转换成Base64编码 ,带头文件
	 * 
	 * @param imgFilePath
	 *            待处理图片
	 * @return
	 */
	public static String imageToBase64WithHead(String imgFilePath) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String type = imgFilePath.substring(imgFilePath.length() - 3, imgFilePath.length());
		// 为编码添加头文件字符串
		String head = "data:image/" + type + ";base64,";

		return head + imageToBase64(imgFilePath);
	}

	/**
	 * 将图片转换成Base64编码
	 * 
	 * @param imgFile
	 *            待处理图片
	 * @return
	 */
	public static String imageToBase64(String imgFilePath) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		BufferedInputStream bis = null;

		// 定义一个输出流，相当StringBuffer（），会根据读取数据的大小，调整byte的数组长度
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] data = null;

		try {
			// 读取图片字节数组
			URL url = new URL(imgFilePath);
			in = url.openStream();
			bis = new BufferedInputStream(in);
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = bis.read(tmp)) != -1) {
				baos.write(tmp, 0, len);
			}

			// 把文件输出流的数据，放到字节数组
			data = baos.toByteArray();

			bis.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String(Base64.encodeBase64(data));
	}

	public static void main(String[] args) {
		String encodeImage = imageToBase64WithHead("http://images2015.cnblogs.com/blog/545446/201706/545446-20170612171500775-635214580.png");
		System.out.println(encodeImage);
	}
}