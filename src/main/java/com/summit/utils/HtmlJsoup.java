package com.summit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlJsoup {

	/**
	 * 检测图片路径是否为真实有效的路径方法
	 * 
	 * @param src
	 * @return
	 */
	public static boolean checkImage(String src) {

		// 使用正则表达式，排除img标签src属性值已经是base64编码的情况
		Pattern pattern = Pattern.compile("^data:image/(png|gif|jpg|jpeg|bmp|tif|psd|ICO);base64,.*");
		Matcher matcher = pattern.matcher(src);
		if (matcher.matches())
			return false;
		// 排除src路径并非图片格式的情况
		pattern = Pattern.compile("^.*[.](png|gif|jpg|jpeg|bmp|tif|psd|ICO)$");
		matcher = pattern.matcher(src);
		if (!matcher.matches()) {
			return false;
		}

		return true;
	}

	/**
	 * html中的img转换为base64编码图片,并返回编码后的html
	 * 
	 * @param html
	 * @return
	 */
	public static String html_ImgToBase64(String html) {
		Document doc = Jsoup.parse(html, "utf-8");
		Elements imgs = doc.getElementsByTag("img");

		for (Element img : imgs) {
			String src = img.attr("src");

			if (!checkImage(src))
				continue;
			// 将有效的路径传入base64编码的方法
			img.attr("src", Image2Base64.imageToBase64WithHead(src));

		}

		// 返回base64编码后的html文档
		return doc.getElementsByTag("body").html();
	}

	public static void main(String[] args) {
		String str = html_ImgToBase64("<img src='http://images2015.cnblogs.com/blog/545446/201706/545446-20170612171500775-635214580.png' alt='' width='830' height='552'/>");
		System.out.println(str);
	}
}