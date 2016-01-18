package com.zto.controller;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zto.elasticsearch.BillELKHelper;

public class FullTextQueries {

	private static final Logger log = LoggerFactory
			.getLogger(FullTextQueries.class);

	public static void main(String[] args) {
		BillELKHelper elkHelper = new BillELKHelper();
		try {
			elkHelper.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] indices = { "bank" };
		String[] types = { "accounts" };
		/**
		 * Match Query matchQuery(String name, Object text)
		 */
		QueryBuilder MQuery = QueryBuilders.matchQuery("account_number", "462");
		List<String> MQueryList = elkHelper.FullTextQueries(indices, types,
				MQuery);
		log.info("匹配查询  Match Query:" + MQueryList.toString());
		/**
		 * MultiMatchQuery multiMatchQuery(Object text, String... fieldNames)
		 */
		QueryBuilder MMQuery = QueryBuilders.multiMatchQuery("45",
				"account_number", "age");
		List<String> MMQueryList = elkHelper.FullTextQueries(indices, types,
				MMQuery);
		log.info("匹配多个字段查询  MultiMatchQuery:" + MMQueryList.toString());
		/**
		 * Common Terms Query commonTermsQuery(String name, Object text)
		 * 必须匹配字符串字段
		 */
		QueryBuilder CTQuery = QueryBuilders.commonTermsQuery("address",
				"810 Milford Street");
		List<String> CTQueryList = elkHelper.FullTextQueries(indices, types,
				CTQuery);
		log.info("-----  commonTermsQuery:" + CTQueryList.toString());
		/**
		 * Query String Query 匹配全部类型的字段（即匹配全类型）
		 */
		QueryBuilder QSQuery = QueryBuilders
				.queryStringQuery("rachellerice@enaut.com");
		List<String> QSQueryList = elkHelper.FullTextQueries(indices, types,
				QSQuery);
		log.info("字符串查询 Query String Query:" + QSQueryList.toString());
		/**
		 * Simple Query String Qyery
		 */
		QueryBuilder SQSQuery = QueryBuilders.simpleQueryStringQuery("garciahess@quiltigen.comf");
		List<String> SQSQueryList = elkHelper.FullTextQueries(indices, types,
				SQSQuery);
		log.info("简单字符串查询 Query String Query:" + SQSQueryList.toString());

	}
}
