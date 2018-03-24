package com.itheima.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.taotao.pojo.TbContent;
import com.itheima.taotao.service.ContentService;
import com.itheima.taotao.utils.EasyUIResult;
import com.itheima.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult getContentList(Long categoryId,int page,int rows){
		
		return contentService.getContentList(categoryId, page, rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		return contentService.insertContent(content);
	}
	

}
