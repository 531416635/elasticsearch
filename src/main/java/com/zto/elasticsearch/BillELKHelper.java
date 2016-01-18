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
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
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
	 * 关闭client
	 */
	public void close() {
		if (client != null) {
			client.close();
		}
	}

	public static void main(String[] args) {

	}
}
