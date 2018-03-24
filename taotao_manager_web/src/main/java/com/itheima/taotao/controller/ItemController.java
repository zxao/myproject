package com.itheima.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.taotao.page.EsayUIResult;
import com.itheima.taotao.pojo.TbItem;
import com.itheima.taotao.search.service.SearchItemService;
import com.itheima.taotao.service.ItemService;
import com.itheima.taotao.utils.TaotaoResult;
/**
 * 商品信息的controller
 * @author ZXAO
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EsayUIResult getItemList(Integer page, Integer rows){
		
		EsayUIResult queryItem = itemService.queryItem(page, rows);
		
		return queryItem;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult insertItem(TbItem item, String desc){
		
		TaotaoResult insertItem = itemService.insertItem(item, desc);
	
		return insertItem;
	}
	
	@RequestMapping("/rest/page/item-edit")
	public String showItemEdit() {
		return "item-edit";
	}
	
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable("itemId") Long itemId){
		TaotaoResult itemDesc = itemService.getItemDesc(itemId);
		return itemDesc;
	}
	
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult updateItem(TbItem item, String desc){
		TaotaoResult updateItem = itemService.updateItem(item, desc);
		return updateItem;
	}
	
	/*@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult deleteItem(Long[] ids) {
		//删除的状态
		int status = 3;
		TaotaoResult deleteItem = itemService.updateStatus(ids,status);
		return deleteItem;
	}
	
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public TaotaoResult instockItem(Long[] ids) {
		//下架的状态
		int status = 2;
		TaotaoResult instockItem = itemService.updateStatus(ids,status);
		return instockItem;
	}
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public TaotaoResult reshelfItem(Long[] ids) {
		//上架的状态
		int status = 1;
		TaotaoResult reshelfItem = itemService.updateStatus(ids,status);
		return reshelfItem;
	}
	*/
	
	@RequestMapping("/rest/item/{operate}")
	@ResponseBody
	public TaotaoResult updateStatus(@PathVariable String operate,Long[] ids) {
		int status;
		if("delete".equals(operate)){
			//删除商品的状态
			status=3;
		}else if("instock".equals(operate)){
			//下架商品的状态
			status=2;
		}else{
			//上架商品的状态
			status=1;
		}
		TaotaoResult reshelfItem = itemService.updateStatus(ids,status);
		return reshelfItem;
	}
	
	//一键导入商品信息到索引库
	@RequestMapping("/rest/item/importAllItem")
	public TaotaoResult importAllItems() {
		try {
			TaotaoResult importAllItems = searchItemService.importAllItems();
			return importAllItems;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, "一键导入商品信息到索引库失败！");
		}
	}


}
