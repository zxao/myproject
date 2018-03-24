package com.itheima.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 内容管理的Controller
 * @author ZXAO
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.taotao.page.EasyUITreeNode;
import com.itheima.taotao.service.ContentCategoryService;
import com.itheima.taotao.utils.TaotaoResult;
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCat(@RequestParam(value="id",defaultValue="0") Long parentId) {
		return contentCategoryService.getContentCat(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCat(Long parentId,String name) {
		return contentCategoryService.insertContentCat(parentId, name);
	}
	
	@RequestMapping("/update")
	public void updateContentCat(Long id,String name){
		contentCategoryService.updateContentCat(id, name);
	}
	
	@RequestMapping("/delete")
	public void deleteContentCat(Long id) {
		contentCategoryService.deleteContentCat(id);
	}
	
	

}
