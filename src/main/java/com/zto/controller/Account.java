package com.zto.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
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
		log.info("queryDate:" + d.size() + "");
		tity = elkHelper.queryDataByPage(indices, type, query, page);
		log.info("queryDataByPage:" + tity.getPageSize());

		// 查询大于等于多少工资的信息
		List<String> singleSearch = elkHelper.singleSearch(indices, type,
				query, 47406);
		log.info("singleSearch:" + singleSearch.toString());

		// scrollsSearch
		QueryBuilder postFilter = QueryBuilders.rangeQuery("age").from(20)
				.to(30);
		List<String> scrollsSearch = elkHelper.scrollsSearch(indices, query,
				postFilter);
		log.info("scrollsSearch:" + scrollsSearch.toString());

		// multiSearch
		QueryBuilder builder1 = QueryBuilders.queryStringQuery("Hanson");
		QueryBuilder builder2 = QueryBuilders.matchQuery("age", 27);
		List<String> multiSearch = elkHelper.multiSearch(builder1, builder2);
		log.info("multiSearch:" + multiSearch.toString());

	}
}
