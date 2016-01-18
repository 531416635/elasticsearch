package com.zto.controller;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zto.elasticsearch.BillELKHelper;

/**
 * 
 * @author yaoyuxiao
 *
 */
public class Search {
	private static Logger log = LoggerFactory.getLogger(Search.class);

	public static void main(String[] args) {
		BillELKHelper elkHelper = new BillELKHelper();
		try {
			elkHelper.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// use aggregations
		String field = "balance";
	//	elkHelper.aggregationSearch(field);
		// min aggregations
		elkHelper.minSearch(field);
	}
}
