package com.itheima.taotao.service;

import java.util.List;

import com.itheima.taotao.page.EasyUITreeNode;
import com.itheima.taotao.pojo.TbContent;
import com.itheima.taotao.utils.EasyUIResult;
import com.itheima.taotao.utils.TaotaoResult;

/**
 * 内容管理分类
 * @author ZXAO
 *
 */
public interface ContentService {
	
	EasyUIResult getContentList(Long categoryId,int page,int rows);
	TaotaoResult insertContent(TbContent content);
	List<TbContent> getContentListByCid(Long cid);
}