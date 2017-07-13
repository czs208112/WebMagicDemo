package com.summit.demo.taobao;

import java.io.IOException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class TaobaoTest implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

	public static final String URL_LIST = "https://www\\.cnblogs\\.com/#p\\d{1,3}";

	public static int pageNum = 1;

	public void process(Page page) {
		try {
			System.out.println(page.getHtml() + "###");
			String str = page.getHtml().xpath("//*[@id='J-From']/text()").get();
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws IOException {
		// System.setProperty("selenuim_config", "D:/config.ini");
		// SeleniumDownloader downloader = new SeleniumDownloader();

		PhantomJSDownloader downloader = new PhantomJSDownloader("d:/phantomjs.exe --ignore-ssl-errors=yes", "D:/crawl.js");
		downloader.setThread(3);
		Spider.create(new TaobaoTest()).setDownloader(downloader).addUrl("https://42.99.33.26/MSS-PORTAL/announcementjoin/list.do").thread(20).runAsync();
	}
}