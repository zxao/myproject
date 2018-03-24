package com.itheima.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.itheima.taotao.mapper.TbContentCategoryMapper;
import com.itheima.taotao.page.EasyUITreeNode;
import com.itheima.taotao.pojo.TbContent;
import com.itheima.taotao.pojo.TbContentCategory;
import com.itheima.taotao.pojo.TbContentCategoryExample;
import com.itheima.taotao.pojo.TbContentCategoryExample.Criteria;
import com.itheima.taotao.service.ContentCategoryService;
import com.itheima.taotao.utils.EasyUIResult;
import com.itheima.taotao.utils.TaotaoResult;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	private int deleteByPrimaryKey;
	
	@Override
	public List<EasyUITreeNode> getContentCat(Long parentId) {
		// 根据parentId查询分类
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		
		//遍历查询的结果
		for (TbContentCategory contentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent()? "closed":"open");
			resultList.add(node);
		}
		
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCat(Long parentId, String name) {
		// 创建ContentCatgory来接受返回的主键
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategoryMapper.insert(contentCategory);
		//判断父节点的isparent是否为true,如果不为true,修改为true
		TbContentCategory contentCategory2 = contentCategoryMapper.selectByPrimaryKey(parentId);
		Boolean isParent = contentCategory2.getIsParent();
		if(!isParent){
			contentCategory2.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(contentCategory2);
		}
		//主键返回
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public void deleteContentCat(Long id) {
		// 根据分类id删除分类
		//判断删除的是否是叶子节点
			//如果是叶子节点，删除节点
				//查看父节点下还是否有其他子节点
					//如果有其他子节点，如果没有其他子节点，则将父节点的isparent改为false
			//如果删除的是父节点
				//递归删除其下面的所有节点
				//查看其父节点下是否还有其他节点，如果没有就将父节点的isparent改为false
		//根据id查询内容分类
		TbContentCategory contentCat = contentCategoryMapper.selectByPrimaryKey(id);
		Long parentId = contentCat.getParentId();
		if(!contentCat.getIsParent()){
			//是叶子节点
			//删除该节点
			contentCategoryMapper.deleteByPrimaryKey(id);
			//查询其父节点下是否有其他节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//判断结合长度是否为0
			if(list!=null && list.size()==0){
				//说明没有其他子节点
				//修改父节点的isparent为false
				TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
				parentNode.setIsParent(false);
				//更新父节点
				contentCategoryMapper.updateByPrimaryKey(parentNode);
			}
			
		}else{
			//不是叶子节点
			//迭代删除该节点下的所有节点
			deleteNode(id);
			
			//查询其父节点下是否有其他节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//判断结合长度是否为0
			if(list!=null && list.size()==0){
				//说明没有其他子节点
				//修改父节点的isparent为false
				TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
				parentNode.setIsParent(false);
				//更新父节点
				contentCategoryMapper.updateByPrimaryKey(parentNode);
			}
		}
		
	}
	
	//递归删除父节点下的所有节点
	public void deleteNode(Long id){
		//查询该节点下的所有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//删除不是父节点的节点
		for (TbContentCategory contentCat : list) {
			if(!contentCat.getIsParent()){
				//是叶子节点
				//删除该节点
				contentCategoryMapper.deleteByPrimaryKey(contentCat.getId());
			}else{
				//是父节点
				deleteNode(contentCat.getId());
			}
			//删除自己
			contentCategoryMapper.deleteByPrimaryKey(id);
			
		}
		
		
	}

	@Override
	public void updateContentCat(Long id, String name) {
		// 更新内容分类
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		
	}

	
	

	
}
