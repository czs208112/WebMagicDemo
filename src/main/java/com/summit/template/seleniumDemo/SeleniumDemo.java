package com.summit.template.seleniumDemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class SeleniumDemo implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

	public void process(Page page) {
		// System.getProperties().setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		// driver.get("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000");
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// WebElement webElement = driver.findElement(By.id("tableData_stockListCompany"));
		// // WebElement webElement =
		// // driver.findElement(By.xpath("//div[@class='table-responsive sse_table_T05']"));
		// String str = webElement.getAttribute("outerHTML");
		// System.out.println(str);
		//
		// Html html = new Html(str);
		Html html = page.getHtml();
		System.out.println(html.xpath("//tbody/tr").all());
		String companyCode = html.xpath("//tbody/tr[1]/td/text()").get();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = html.xpath("//tbody/tr[3]/td/text()").get().split("/")[0];

		String stockCode = html.xpath("//tbody/tr[2]/td/text()").get().split("/")[0];
		String name = html.xpath("//tbody/tr[5]/td/text()").get().split("/")[0];
		String department = html.xpath("//tbody/tr[14]/td/text()").get().split("/")[0];
		System.out.println(companyCode);
		System.out.println(stockCode);
		System.out.println(name);
		System.out.println(department);
		// driver.close();

	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		System.setProperty("selenuim_config", "D:/config.ini");
		SeleniumDownloader downloader = new SeleniumDownloader();
		downloader.setSleepTime(3000);
		downloader.setThread(5);
		Spider.create(new SeleniumDemo()).setDownloader(downloader).addUrl("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000").thread(5).run();
		// Spider.create(new
		// SeleniumDemo()).addUrl("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000").thread(5).runAsync();

	}
}
