package com.itheima.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.taotao.mapper.TbItemDescMapper;
import com.itheima.taotao.mapper.TbItemMapper;
import com.itheima.taotao.page.EsayUIResult;
import com.itheima.taotao.pojo.TbItem;
import com.itheima.taotao.pojo.TbItemDesc;
import com.itheima.taotao.pojo.TbItemExample;
import com.itheima.taotao.service.ItemService;
import com.itheima.taotao.utils.IDUtils;
import com.itheima.taotao.utils.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public EsayUIResult queryItem(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		long total = new PageInfo<>(list).getTotal();
		EsayUIResult esayUIResult = new EsayUIResult(total,list);
		return esayUIResult;
	}

	@Override
	public TaotaoResult insertItem(TbItem item, String desc) {
		// 生成商品的ID
		long genItemId = IDUtils.genItemId();
		item.setId(genItemId);
		//商品状态1正常2下架3删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		
		//插入数据
		itemMapper.insert(item);
		//创建TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(genItemId);
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		// 根据商品ID查询商品描述信息
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc) {
		//商品状态1正常2下架3删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		
		//插入数据
		itemMapper.updateByPrimaryKey(item);
		//创建TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.updateByPrimaryKey(itemDesc);
		
		return TaotaoResult.ok();
		
	}
	/**
	 * 删除商品
	 */
	@Override
	public TaotaoResult updateStatus(Long[] ids,int status) {
		
		for (Long id : ids) {
			TbItem item = itemMapper.selectByPrimaryKey(id);
			item.setStatus((byte) status);
			itemMapper.updateByPrimaryKey(item);
		}
		return TaotaoResult.ok();
	}
	

}
