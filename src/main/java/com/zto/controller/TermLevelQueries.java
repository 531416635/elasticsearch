package com.zto.controller;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zto.elasticsearch.BillELKHelper;

/**
 * 
 * @author yaoyuxiao
 *
 */
public class TermLevelQueries {
	private static final Logger log = LoggerFactory
			.getLogger(TermLevelQueries.class);

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
		 * term查询 找到文件,包含字段中指定的术语 指定
		 * 
		 * 查询字符串是出了一点问题，留待解决
		 */
		QueryBuilder TQuery = QueryBuilders.termQuery("balance", 34487);
		List<String> TQueryList = elkHelper.TermLevelQueries(indices, types,
				TQuery);
		log.info("精确查询 termQuery:" + TQueryList.toString());
		/**
		 * terms查询 找到文件,包含任何的确切条款中指定的字段 指定
		 */
		QueryBuilder TsQuery = QueryBuilders.termQuery("gender", "M");
		List<String> TsQueryList = elkHelper.TermLevelQueries(indices, types,
				TsQuery);
		log.info("ss精确查询 termsQuery:" + TsQueryList.toString());
		/**
		 * Range Query查询 找到文件,包含指定的字段值(日期、数字、 或字符串)在指定的范围内
		 */
		QueryBuilder RQuery = QueryBuilders.rangeQuery("balance").from(1000)
				.to(5000).includeLower(true)// true 表示包含下限值 ，false表示不包含下限值
				.includeUpper(false);// true 表示包含上限值 ，false表示不包含上线值
		List<String> RQueryList = elkHelper.TermLevelQueries(indices, types,
				RQuery);
		log.info("范围查询 Range Query:" + RQueryList.toString());
		/**
		 * Exists Query查询 找到文件,指定的字段包含任何非空值
		 */
		QueryBuilder EQuery = QueryBuilders.existsQuery("lastname");
		List<String> EQueryList = elkHelper.TermLevelQueries(indices, types,
				EQuery);
		log.info("Exists Query:" + EQueryList.toString());
		/**
		 * Missing Query查询: 发现文件是缺失或只包含指定的字段null值
		 */
		QueryBuilder MissQuery = QueryBuilders.missingQuery("age");
		List<String> MissQueryList = elkHelper.TermLevelQueries(indices, types,
				MissQuery);
		log.info("MissQuery:" + MissQueryList.toString());
		/**
		 * Prefix Query查询: 找到文件,包含条款与指定的字段 指定的前缀
		 */
		QueryBuilder PrefixQuery = QueryBuilders.prefixQuery("address", "806");
		List<String> PrefixQueryList = elkHelper.TermLevelQueries(indices,
				types, PrefixQuery);
		log.info("PrefixQuery:" + PrefixQueryList.toString());
		/**
		 * Wildcard Query 找到文件,包含条件匹配指定的字段 指定的模式,模式支持单个字符通配符 (?)和多字符通配符(*)
		 */
		QueryBuilder WildcardQuery = QueryBuilders.wildcardQuery("address",
				"8?6*");
		List<String> WildcardQueryList = elkHelper.TermLevelQueries(indices,
				types, WildcardQuery);
		log.info("WildcardQuery:" + WildcardQueryList.toString());
		/**
		 * Regexp Query查询 找到文件,包含条件匹配指定的字段 指定正则表达式
		 */
		QueryBuilder RegexpQuery = QueryBuilders.regexpQuery("address", "8.*6");
		List<String> RegexpQueryList = elkHelper.TermLevelQueries(indices,
				types, RegexpQuery);
		log.info("RegexpQuery:" + RegexpQueryList.toString());
		/**
		 * Fuzzy Query查询 找到文件,指定的字段包含条款不明确地 类似于指定的项
		 */
		QueryBuilder FuzzyQuery = QueryBuilders.fuzzyQuery("address", "6")
				.prefixLength(3);
		List<String> FuzzyQueryList = elkHelper.TermLevelQueries(indices,
				types, FuzzyQuery);
		log.info("FuzzyQuery:" + FuzzyQueryList.toString());
		/*
		 * ids查询 找到文件指定类型和id。
		 */
		QueryBuilder IdsQuery = QueryBuilders.idsQuery().addIds("23", "1", "3");
		List<String> IdsQueryList = elkHelper.TermLevelQueries(indices, types,
				IdsQuery);
		log.info("IdsQuery:" + IdsQueryList.toString());
	}
}
