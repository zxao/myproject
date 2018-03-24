package com.itheima.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.taotao.mapper.TbItemCatMapper;
import com.itheima.taotao.page.EasyUITreeNode;
import com.itheima.taotao.pojo.TbItemCat;
import com.itheima.taotao.pojo.TbItemCatExample;
import com.itheima.taotao.pojo.TbItemCatExample.Criteria;
import com.itheima.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for (TbItemCat itemCat : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(itemCat.getId());
			easyUITreeNode.setText(itemCat.getName());
			easyUITreeNode.setState(itemCat.getIsParent() ? "closed":"open");
			result.add(easyUITreeNode);
		}
		return result;
	}

}
