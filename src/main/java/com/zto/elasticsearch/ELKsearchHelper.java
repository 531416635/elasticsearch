package com.zto.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zto.entity.User;

public class ELKsearchHelper {

	public Client client = null;
	private static final Logger log = LoggerFactory
			.getLogger(ELKsearchHelper.class);
	public User user;

	// 初始化启动连接
	public void init() throws IOException {
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", "yaoyuxiao-cluster").build();
		client = TransportClient
				.builder()
				.settings(settings)
				.build()
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("10.10.19.172"), 9300));
	}

	// 创建索引
	public void createIndex() {
		try {
			IndexResponse response = client
					.prepareIndex("users", "user", "2")
					.setSource(
							XContentFactory.jsonBuilder().startObject()
									.field("id", 234).field("age", 23)
									.field("name", "name2").endObject()).get();
			log.info("创建索引的index：" + response.getIndex() + "--type:"
					+ response.getType() + "--id:" + response.getId()
					+ "--version:" + response.getVersion() + "--context:"
					+ response.getContext());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("创建索引失败：" + e.getMessage());
		}
	}

	/**
	 * 单个获取索引
	 */
	public void getIndex() {
		GetResponse response = client.prepareGet("users", "user", "2").get();
		log.info("获得索引的index：" + response.getIndex() + "--type:"
				+ response.getType() + "--id:" + response.getId()
				+ "--version:" + response.getVersion() + "--context:"
				+ response.getContext());
	}

	/**
	 * 单个删除索引
	 */
	public void deleteIndex() {
		DeleteResponse response = client.prepareDelete("users", "user", "2")
				.get();
		log.info("单个删除索引index：" + response.getIndex() + "--type:"
				+ response.getType() + "--id:" + response.getId()
				+ "--version:" + response.getVersion() + "--context:"
				+ response.getContext());
	}

	/**
	 * 批量获取索引
	 */
	public void multiGetIndex() {
		MultiGetResponse multiGetresponse = client.prepareMultiGet()
				.add("users", "user", "1").add("users", "user", "2", "3", "4")
				.get();
		for (MultiGetItemResponse itemResponse : multiGetresponse) {
			GetResponse response = itemResponse.getResponse();
			if (response.isExists()) {
				String json = response.getSourceAsString();
				log.info(json);
				log.info("批量获取索引index：" + response.getIndex() + "--type:"
						+ response.getType() + "--id:" + response.getId()
						+ "--version:" + response.getVersion() + "--context:"
						+ response.getContext());
			}
		}
	}

	/**
	 * 批量操作
	 * @throws IOException 
	 */
	public void bulkOperate() throws IOException {

		BulkRequestBuilder bulkRequest = client.prepareBulk();
		bulkRequest.add(client.prepareIndex("twitter", "tweet", "1").setSource(
				XContentFactory.jsonBuilder().startObject().field("user", "kimchy")
						.field("postDate", new Date())
						.field("message", "trying out Elasticsearch")
						.endObject()));

		bulkRequest.add(client.prepareIndex("twitter", "tweet", "2").setSource(
				XContentFactory.jsonBuilder().startObject()
						.field("user", "kimchy").field("postDate", new Date())
						.field("message", "another post").endObject()));

		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
		}

	}

	/* 几种常用的数据传输格式 */
	public void transJson() throws IOException {
		// json字符串
		String jsonString = "{" + "\"user\":\"kimchy\","
				+ "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		// json Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("user", "kimchy");
		jsonMap.put("postDate", new Date());
		jsonMap.put("message", "trying out Elasticsearch");
		// 序列化bean,自动转化为json
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		byte[] json = mapper.writeValueAsBytes(user);
		// use elasticsearch helper
		XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
				.field("user", "kimchy").field("postDate", new Date())
				.field("message", "trying out Elasticsearch").endObject();
	}

	// 关闭
	public void close() {
		client.close();
	}
}