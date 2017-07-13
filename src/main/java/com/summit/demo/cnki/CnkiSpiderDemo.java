package com.summit.demo.cnki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.handler.SubPageProcessor;
import us.codecraft.webmagic.processor.PageProcessor;

import com.summit.utils.IOUtils;

public class CnkiSpiderDemo implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

	private static Spider spider;

	// 本例中没用到，分支较多的process可以考虑使用。 用法参考CompositePageProcessor
	private List<SubPageProcessor> subPageProcessors = new ArrayList<SubPageProcessor>();

	public static final String URL_LIST = "https://www\\.cnblogs\\.com/#p\\d{1,3}";
	// public static final String URL_LIST = "https://www\\.hao123\\.com";

	public long startTime = System.currentTimeMillis();

	public static int pageNum = 1;

	public void process(Page page) {
		pageNum++;
		System.out.println(pageNum + "#######");
		// if (page.getUrl().regex("http://www.cnki\\.com\\.cn/Article/CJFDTotal-.*\\.htm").match())
		// {
		//
		//
		// }
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws IOException {
		// 使用SeleniumDownloader下载page
		System.setProperty("selenuim_config", "D:/config.ini");
		SeleniumDownloader downloader = new SeleniumDownloader();

		// 使用SeleniumDownloader下载page
		// Downloader downloader = new PhantomJSDownloader("d:/phantomjs.exe", "D:/crawl.js");

		// downloader.setSleepTime(2000);
		downloader.setThread(10);

		// 启动爬虫
		spider = Spider.create(new CnkiSpiderDemo());

		List<String> urls = new ArrayList<String>();
		List<String> urls2 = new ArrayList<String>();

		String path = CnkiSpiderDemo.class.getClassLoader().getResource("urls.txt").getPath();
		urls = IOUtils.readTxtFile(path);
		List<String> aaa = aaa(urls);

		String path1 = CnkiSpiderDemo.class.getClassLoader().getResource("urls_2.txt").getPath();
		urls2 = IOUtils.readTxtFile(path1);
		List<String> bbb = aaa(urls2);
		System.out.println(bbb.size());
		for (String str : aaa) {
			if (!bbb.contains(str)) {
				System.out.println(str + "***************************************");
			}
		}

		System.out.println(aaa.size());
		spider.addUrl(aaa.toArray(new String[aaa.size()])).thread(10).run();
	}

	public static List<String> aaa(List<String> u) {
		List<String> strs = new ArrayList<String>();
		for (String url : u) {
			if (!strs.contains(url)) {
				strs.add(url);
			}
		}
		return strs;
	}
}