package com.itheima.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * SolrJ的测试
 * @author ZXAO
 *
 */
public class SolrjTest {
	
	//添加文档
	
	@Test
	public void testSolrj() throws SolrServerException, IOException {
	
		//创建solrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.113.128:8080/solr");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域，必须有id域
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		document.addField("item_price", "200");
		//把文档添加到索引库中
		solrServer.add(document);
		//提交
		solrServer.commit();
		
	}
	/**
	 * 根据id删除文档
	 * @throws Exception
	 */
	@Test
	public void deleteDecumentById() throws Exception {
		//创建一个sorlServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.113.128:8080/solr");
		//调用根据id删除的方法
		solrServer.deleteById("test001");
		//提交
		solrServer.commit();
	}
}
