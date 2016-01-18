package com.zto.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zto.elasticsearch.BillELKHelper;
import com.zto.entity.PageEntity;

public class Account {
	private static Logger log = LoggerFactory.getLogger(Account.class);

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		BillELKHelper elkHelper = new BillELKHelper();

		try {
			elkHelper.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryBuilder query = QueryBuilders.rangeQuery("account_number").from(1)
				.to(1000);
		String[] indices = { "bank" };
		String[] type = { "accounts" };
		PageEntity page = new PageEntity();
		PageEntity tity = new PageEntity();
		page.setPage(1);
		page.setPageSize(20);
		page.setStartIndex(1);
		List<Map<String, Object>> d = elkHelper.queryDate(indices, query);
		System.out.println(d);
		log.info(d.size() + "");
		tity = elkHelper.queryDataByPage(indices, type, query, page);
		log.info(tity.getPageSize() + "-！！----");
	}
}
