package com.itheima.taotao.service;

import java.util.List;

import com.itheima.taotao.page.EsayUIResult;
import com.itheima.taotao.pojo.TbItem;
import com.itheima.taotao.utils.TaotaoResult;

public interface ItemService {
	EsayUIResult queryItem(int page,int rows);
	TaotaoResult insertItem(TbItem item, String desc);
	TaotaoResult getItemDesc(Long itemId);
	TaotaoResult updateItem(TbItem item, String desc);
	TaotaoResult updateStatus(Long[] ids,int status);
}
