package com.summit.demo.addvcdDemo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class WebMagicDemo implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

	public void process(Page page) {
		page.addTargetRequest(page.getUrl().get());
		System.out.println("#######" + page.getUrl().get());
		page.putField("addvcd", page.getHtml().xpath("//div[@class='TRS_PreAppend']/p[@class='MsoNormal']//span[@lang='EN-US']/text()").all());
		page.putField("addvnm", page.getHtml().xpath("//div[@class='TRS_PreAppend']/p[@class='MsoNormal']//span[@style='font-family: 宋体']/text()").all());

		// if (page.getResultItems().get("name") == null) {
		// // skip this page
		// page.setSkip(true);
		// }
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider spider = Spider.create(new WebMagicDemo());
		spider.addPipeline(new MyPipeLine());
		// spider.addPipeline(new JsonFilePipeline("d:\\a.txt"));
		spider.addUrl("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html").thread(5).run();
	}
}