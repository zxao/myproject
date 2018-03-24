package com.itheima.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.taotao.pojo.SearchResult;
import com.itheima.taotao.search.dao.SearchDao;
import com.itheima.taotao.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		// 创建一个solrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置分页条件
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//指定默认搜索域
		query.set("df", "item_title");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//执行查询
		SearchResult search = searchDao.search(query);
		long recordCount = search.getRecordCount();
		long count = recordCount / rows;
		Double ceil = Math.ceil(count);
		search.setPageCount(ceil.longValue());
		
		return search;
	}

}
