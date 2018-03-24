package com.itheima.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * cloudSolrJ的测试
 * @author ZXAO
 *
 */
public class CloudSolrJTest {
	@Test
	public void testCouldSolr() throws SolrServerException, IOException {
		//使用cloudSolrServer创建solrServer对象
		CloudSolrServer solrServer = new CloudSolrServer("192.168.113.128:2181,192.168.113.128:2182,192.168.113.128:2183");
		//需要设置默认的collection属性
		solrServer.setDefaultCollection("collection2");
		//创建document对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加属性
		document.setField("id", "test001");
		document.setField("item_title", "测试商品");
		document.setField("item_price", "100");
		//将document添加到solrServer，把文档提交到索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
		
	}
	
	@Test
	public void testDeleteCloudSolr() throws SolrServerException, IOException {
		//创建solrServer
		CloudSolrServer solrServer = new CloudSolrServer("192.168.113.128:2181,192.168.113.128:2182,192.168.113.128:2183");
		//设置默认collection属性
		solrServer.setDefaultCollection("collection2");
		//删除
		solrServer.deleteById("test001");
		solrServer.commit();
		
	}
}
