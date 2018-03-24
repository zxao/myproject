package com.itheima.taotao.search.service;

import com.itheima.taotao.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString,int page,int rows) throws Exception;

}
