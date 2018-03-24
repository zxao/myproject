package com.itheima.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itheima.taotao.pojo.SearchResult;
import com.itheima.taotao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Value("${ITEM_ROWS}")
	private Integer ITEM_ROWS;
	@RequestMapping("/search")
	public String search(@RequestParam("q") String queryString,@RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
		SearchResult search = searchService.search(queryString, page, ITEM_ROWS);
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", search.getPageCount());
		model.addAttribute("itemList", search.getItemList());
		model.addAttribute("page", page);
		return "search";
		
	}
}
