package com.itheima.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.taotao.pojo.SearchItem;
import com.itheima.taotao.search.dao.mapper.SearchItemMapper;
import com.itheima.taotao.search.service.SearchItemService;
import com.itheima.taotao.utils.TaotaoResult;
@Service
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	private SolrServer solrServer;
	
	@Autowired
	private SearchItemMapper searchItemMapper;

	@Override
	public TaotaoResult importAllItems() throws Exception {
		//查询所有商品的数据
		List<SearchItem> itemList = searchItemMapper.getItemList();
		//为每一个商品创建一个document对象
		for (SearchItem searchItem : itemList) {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getId());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			solrServer.add(document);
		}
		
		//提交
		solrServer.commit();
		
		return TaotaoResult.ok();
	}

}
