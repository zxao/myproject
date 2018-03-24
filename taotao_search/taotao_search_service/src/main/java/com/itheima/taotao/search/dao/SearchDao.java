package com.itheima.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itheima.taotao.pojo.SearchItem;
import com.itheima.taotao.pojo.SearchResult;

@Repository
public class SearchDao {
	
	//注入solrServer
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query) throws Exception{
		
		/*//根据查询条件查询索引库
		QueryResponse response = solrServer.query(query);
		//获取商品列表
		SolrDocumentList results = response.getResults();
		List<SearchItem> itemList = new ArrayList<>();
		//遍历结果集
		for (SolrDocument document : results) {
			SearchItem searchItem = new SearchItem();
			searchItem.setId((String) document.get("id"));
			searchItem.setCategory_name((String) document.get("item_category_name"));
			searchItem.setPrice((long) document.get("item_price"));
			searchItem.setImage((String) document.get("item_image"));
			searchItem.setSell_point((String) document.get("item_sell_point"));
			//取高亮
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(document.get("id")).get("item_title");
			String itemTitle="";
			if(list != null && list.size() >0) {
				itemTitle = list.get(0);
				
			}else{
				itemTitle = (String)document.get("item_title");
			}
			searchItem.setTitle(itemTitle);
			//添加商品到itemList
			itemList.add(searchItem);
		}
		
		SearchResult searchResult = new SearchResult();
		searchResult.setItemList(itemList);
		searchResult.setRecordCount(results.getNumFound());
		return searchResult;*/
		//根据query对象查询索引库
				QueryResponse response = solrServer.query(query);
				//取商品列表
				SolrDocumentList solrDocumentList = response.getResults();
				//商品列表
				List<SearchItem> itemList = new ArrayList<>();
				for (SolrDocument solrDocument : solrDocumentList) {
					SearchItem item = new SearchItem();
					item.setId((String) solrDocument.get("id"));
					item.setCategory_name((String) solrDocument.get("item_category_name"));
					item.setImage((String) solrDocument.get("item_image"));
					item.setPrice((long) solrDocument.get("item_price"));
					item.setSell_point((String) solrDocument.get("item_sell_point"));
					//取高亮显示
					Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
					List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
					String itemTitle = "";
					//有高亮显示的内容时。
					if (list != null && list.size() > 0) {
						itemTitle = list.get(0);
					} else {
						itemTitle = (String) solrDocument.get("item_title");
					}
					item.setTitle(itemTitle);
					//添加到商品列表
					itemList.add(item);
				}
				SearchResult result = new SearchResult();
				//商品列表
				result.setItemList(itemList);
				//总记录数
				result.setRecordCount(solrDocumentList.getNumFound());
				
				return result;
			}
		
	}
	

