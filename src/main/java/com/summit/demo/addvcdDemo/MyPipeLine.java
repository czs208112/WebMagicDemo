package com.summit.demo.addvcdDemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipeLine implements Pipeline {

	public void process(ResultItems resultItems, Task task) {
		List<String> addvnmList = resultItems.get("addvnm");
		List<String> addvcdList = resultItems.get("addvcd");
		System.out.println("avvcdd.size:" + addvcdList.size());
		System.out.println("addvnmList.size_before:" + addvnmList.size());
		for (int i = addvnmList.size() - 1; i >= 0; i--) {
			if (addvnmList.get(i).replaceAll("　", "").equals("")) {
				addvnmList.remove(i);
			}
		}

		List<AddvcdBean> beanList = new ArrayList<AddvcdBean>();
		for (int i = 0; i < addvcdList.size(); i++) {
			AddvcdBean bean = new AddvcdBean();
			bean.setAddvcd(addvcdList.get(i).trim());
			bean.setAddvnm(addvnmList.get(i).replaceAll("　", "").trim());
			beanList.add(bean);
		}

		try {
			Dao.batcAdd(beanList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("addvnmList.size_after:" + addvnmList.size());
	}
}
