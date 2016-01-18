package com.zto.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.client.transport.TransportClient;

import com.zto.entity.User;

public class First_ES_Test {

	private Client client;

	private static byte[] getIPBytes(String ip) {
		byte[] ipBytes = new byte[4];
		String[] ipStr = ip.split("[.]");

		for (int i = 0; i < 4; i++) {
			int m = Integer.parseInt(ipStr[i]);
			byte b = (byte) (m & 0xff);
			ipBytes[i] = b;
		}
		return ipBytes;
	}

	public void init() throws IOException {
		
		Settings settings = Settings.settingsBuilder()
		        .put("cluster.name", "yaoyuxiao-cluster").build();
		
		client = TransportClient
				.builder().settings(settings)
				.build()
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("10.10.19.172"), 9300));
		
	}

	public void close() {
		client.close();
	}

	/*
	 * create index
	 */
	public void createIndex() {
		for (int i = 0; i < 1000; i++) {
			User user = new User();
			user.setId(new Long(i));
			user.setName("xxx0624 " + i);
			user.setAge(i % 100);
			System.out.println("ok:" + i);
			String string = generateJson(user);
			client.prepareIndex("users", "user", "1").setSource(string).get();
		}
	}

	/*
	 * 转换成json对象
	 * 
	 * @param user
	 * 
	 * @return json(String)
	 */
	private String generateJson(User user) {
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
					.startObject();
			contentBuilder.field("id", user.getId() + "");
			contentBuilder.field("name", user.getName());
			contentBuilder.field("age", user.getAge() + "");
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public void search() {
		QueryBuilder qb = QueryBuilders.boolQuery()
				.must(QueryBuilders.termQuery("age", "0"))
				.should(QueryBuilders.termQuery("id", "0"))
		// .mustNot(QueryBuilders.termQuery("content", "test2"))
		// .should(QueryBuilders.termQuery("content", "test3"))
		;

		SearchResponse response = client.prepareSearch("users")
				.setTypes("user")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(qb) // Query
				// .setFilter(FilterBuilders.rangeFilter("age").from(0).to(100))
				// // Filter
				.setFrom(0).setSize(100).setExplain(true).execute().actionGet();
		SearchHits hits = response.getHits();
		System.out.println(hits.getTotalHits()+"        ============         ");
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}

	public static void main(String[] args) throws IOException {

		First_ES_Test client = new First_ES_Test();
		client.init();
		client.createIndex();
		client.search();
		client.close();

	}

	public void print(String output) {
		System.out.print(output);
	}

	public void println(String output) {
		System.out.println(output);
	}
}