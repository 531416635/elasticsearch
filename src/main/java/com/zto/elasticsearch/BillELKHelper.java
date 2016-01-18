package com.zto.elasticsearch;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.zto.entity.PageEntity;

public class BillELKHelper implements InitializingBean {

	TransportClient client = null;

	private static Logger log = LoggerFactory.getLogger(BillELKHelper.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", "yaoyuxiao-cluster").build();
		client = TransportClient.builder().settings(settings).build();
		client.addTransportAddress(
				new InetSocketTransportAddress(InetAddress
						.getByName("10.10.19.172"), 9300))
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("10.10.19.172"), 9301))
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("10.10.19.172"), 9302));

	}

	/**
	 * 根据一组
	 * 
	 * @param indices
	 * @param query
	 * @return
	 */
	public List<Map<String, Object>> queryDate(String[] indices,
			QueryBuilder query) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		SearchResponse response;
		try {
			response = client.prepareSearch(indices).setQuery(query).execute()
					.get();
			SearchHits hits = response.getHits();

			for (int i = 0; i < hits.getHits().length; i++) {
				dataList.add(hits.getAt(i).sourceAsMap());
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataList;

	}

	/**
	 * 
	 * @param indeices
	 * @param type
	 * @param query
	 * @param page
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public PageEntity queryDataByPage(String[] indices, String[] type,
			QueryBuilder query, PageEntity page) throws InterruptedException,
			ExecutionException {
		Long startTime = System.currentTimeMillis();
		Long startCountDataTime = System.currentTimeMillis();
		CountResponse countResponse;
		try {
			countResponse = client.prepareCount(indices).setTypes(type)
					.setQuery(query).execute().get();

			int count = (int) countResponse.getCount();
			page.setRecords(count);
			log.info("es cpunt data cost time: "
					+ (System.currentTimeMillis() - startCountDataTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		startCountDataTime = System.currentTimeMillis();

		SearchRequestBuilder searchRequestBuilder = client
				.prepareSearch(indices);
		searchRequestBuilder.setTypes(type);
		searchRequestBuilder.setQuery(query);
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		searchRequestBuilder.setFrom(page.getStartIndex() - 1).setSize(
				page.getPageSize());
		searchRequestBuilder.setExplain(true);
		SearchResponse response = searchRequestBuilder.execute().actionGet();

		SearchHits hits = response.getHits();
		log.info("es query data cost time: "
				+ (System.currentTimeMillis() - startCountDataTime));
		if (hits.getHits().length > 0) {
			startCountDataTime = System.currentTimeMillis();
			log.info("BillRecordDataEntity --- query other data cost time: "
					+ (System.currentTimeMillis() - startCountDataTime));
		} else {
			page.setData(null);
		}
		log.info("es total cost time: "
				+ (System.currentTimeMillis() - startTime));
		return page;
	}

	/**
	 * 根据查询条件查询工资大于等于一定范围的值，并返回结果
	 * 
	 * @param indices
	 *            索引集合
	 * @param types
	 *            类型集合
	 * @param query
	 *            查询条件
	 * @param count
	 *            工资大小
	 * @return List<String>
	 */
	public List<String> singleSearch(String[] indices, String[] types,
			QueryBuilder query, int count) {
		SearchResponse response = client.prepareSearch(indices).setTypes(types)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(query)
				.setPostFilter(QueryBuilders.rangeQuery("balance").gte(count))
				.setFrom(0).setSize(20).setExplain(true).execute().actionGet();
		SearchHits result = response.getHits();
		List<String> dataList = new ArrayList<String>();
		for (int i = 0; i < result.getHits().length; i++) {
			dataList.add(result.getAt(i).sourceAsString());
		}
		return dataList;
	}

	/**
	 * scrollsSearch
	 * 
	 * @param indices
	 *            索引集合
	 * @param query
	 * @return List<String>
	 */
	public List<String> scrollsSearch(String[] indices, QueryBuilder query,
			QueryBuilder postFilter) {
		SearchResponse scrollRes = client.prepareSearch(indices)
				.setSearchType(SearchType.SCAN).setScroll(new TimeValue(60000))
				.setQuery(query).setSize(30).setPostFilter(postFilter)
				.execute().actionGet();
		List<String> dataList = new ArrayList<String>();
		while (true) {
			for (SearchHit hit : scrollRes.getHits().getHits()) {
				dataList.add(hit.sourceAsString());
			}
			scrollRes = client.prepareSearchScroll(scrollRes.getScrollId())
					.setScroll(new TimeValue(600000)).execute().actionGet();
			// Break condition: No hits are returned
			if (scrollRes.getHits().getHits().length == 0) {
				log.info("scrollRes.getHits().getHits().length:"
						+ scrollRes.getHits().getHits().length);
				break;
			}
		}
		return dataList;
	}

	/**
	 * multiSearch
	 * 
	 * @param builder1
	 * @param builder2
	 * @return List<String>
	 */
	public List<String> multiSearch(QueryBuilder builder1, QueryBuilder builder2) {

		SearchRequestBuilder srb1 = client.prepareSearch().setQuery(builder1)
				.setSize(1);
		SearchRequestBuilder srb2 = client.prepareSearch().setQuery(builder2)
				.setSize(1);

		MultiSearchResponse msr = client.prepareMultiSearch().add(srb1)
				.add(srb2).execute().actionGet();
		// You will get all individual responses from
		// MultiSearchResponse#getResponses()
		long nbHits = 0;
		List<String> dataList = new ArrayList<String>();
		for (MultiSearchResponse.Item item : msr.getResponses()) {
			SearchResponse response = item.getResponse();
			nbHits += response.getHits().getTotalHits();
			for (SearchHit hit : response.getHits()) {
				dataList.add(hit.sourceAsString());
			}
		}
		return dataList;
	}

	/**
	 * aggregationSearch
	 * 
	 * @param field
	 */
	public void aggregationSearch(String field) {
		SearchResponse sr = client
				.prepareSearch()
				.addAggregation(
						AggregationBuilders.terms("Aggregation_field").field(
								field)).execute().actionGet();
		log.info("aggregationSearch:" + sr.getHits().getAt(0).sourceAsString());
	}

	/**
	 * minSearch
	 * 
	 * @param field
	 */
	public void minSearch(String field) {
		SearchResponse sr = client
				.prepareSearch()
				.setIndices("bank")
				.addAggregation(
						AggregationBuilders.min("min_aggs").field(field))
				.execute().actionGet();
		Min min = sr.getAggregations().get("min_aggs");
		log.info(min.getName() + ":" + min.getValueAsString());

	}

	/**
	 * FullTextQueries
	 * 
	 * @param indices
	 * @param types
	 * @param query
	 * @return List<String>
	 */
	public List<String> FullTextQueries(String[] indices, String[] types,
			QueryBuilder query) {
		SearchResponse response = client.prepareSearch().setIndices(indices)
				.setTypes(types).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(query).setExplain(true).execute().actionGet();

		SearchHits hits = response.getHits();
		List<String> dataList = new ArrayList<String>();

		for (SearchHit hit : hits) {
			dataList.add(hit.sourceAsString());
		}
		return dataList;
	}

	/**
	 * 关闭client
	 */
	public void close() {
		if (client != null) {
			client.close();
		}
	}
}
