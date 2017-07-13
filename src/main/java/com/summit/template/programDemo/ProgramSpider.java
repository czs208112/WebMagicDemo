package com.summit.template.programDemo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ProgramSpider implements PageProcessor {
	private static Spider spider;

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

	public static final String URL_LIST = "http://www\\.xiazaiba\\.com/downlist/187_\\d{1,4}\\.html";

	public static final String URL_POST = "http://www\\.xiazaiba\\.com/html/\\d+.html";

	public void process(Page page) {
		if (page.getUrl().regex("http://www\\.xiazaiba\\.com/downlist/187\\.html").match() || page.getUrl().regex(URL_LIST).match()) {
			// 第一页
			// 加入详情页
			// 加入列表页
			page.addTargetRequests(page.getHtml().xpath("//ul[@class='cur-cat-list']/li/a[1]").links().all());
			page.addTargetRequests(page.getHtml().xpath("//div[@class='ylmf-page']").links().all());

		} else {
			// 详情页
			page.putField("url", page.getUrl());
			page.putField("ProgramName", page.getHtml().xpath("//div[@class='soft-title']/html()"));
			page.putField("ProgramContent", page.getHtml().xpath("//td[@class='soft-content']/html()"));

			spider.stop();
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		spider = Spider.create(new ProgramSpider());
		spider.addUrl("http://www.xiazaiba.com/downlist/187.html").thread(10).run();
	}
}