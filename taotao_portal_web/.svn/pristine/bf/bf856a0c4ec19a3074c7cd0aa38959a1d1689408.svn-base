package com.itheima.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 展示首页的controller
 * @author ZXAO
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.support.json.JSONUtils;
import com.itheima.taotao.pojo.TbContent;
import com.itheima.taotao.service.ContentService;
import com.itheima.taotao.utils.Ad1Node;
import com.itheima.taotao.utils.JsonUtils;
@Controller
public class IndexController {
	
	@Value("${AD1_CID}")
	private Long AD1_CID;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index.html")
	public String showIndex(Model model) {
		
		List<TbContent> list = contentService.getContentListByCid(AD1_CID);
		/*List<Ad1Node> listNode = new ArrayList<>();
		for (TbContent content : list) {
			Ad1Node ad1Node = new Ad1Node();
			ad1Node.setAlt(content.getTitle());
			ad1Node.setHref(content.getUrl());
			ad1Node.setHeight(AD1_HEIGHT);
			ad1Node.setHeightB(AD1_HEIGHT_B);
			ad1Node.setWidth(AD1_WIDTH);
			ad1Node.setWidthB(AD1_WIDTH_B);
			ad1Node.setSrc(content.getPic());
			ad1Node.setSrcB(content.getPic2());
			listNode.add(ad1Node);
		}
		
		String objectToJson = JsonUtils.objectToJson(listNode);
		//将数据传递到页面
		
		model.addAttribute("ad1", objectToJson);*/
		
		List<Ad1Node> ad1List = new ArrayList<>();
		for (TbContent tbContent : list) {
			Ad1Node node = new Ad1Node();
			node.setAlt(tbContent.getTitle());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			node.setHref(tbContent.getUrl());
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			//添加到列表
			ad1List.add(node);
		}
		//把数据传递给页面。
		model.addAttribute("ad1", JsonUtils.objectToJson(ad1List));
		
		return "index";
	}
}
