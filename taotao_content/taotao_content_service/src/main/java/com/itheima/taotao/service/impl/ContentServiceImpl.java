package com.itheima.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.taotao.jedis.JedisClientCluster;
import com.itheima.taotao.mapper.TbContentMapper;
import com.itheima.taotao.pojo.TbContent;
import com.itheima.taotao.pojo.TbContentExample;
import com.itheima.taotao.pojo.TbContentExample.Criteria;
import com.itheima.taotao.service.ContentService;
import com.itheima.taotao.utils.EasyUIResult;
import com.itheima.taotao.utils.JsonUtils;
import com.itheima.taotao.utils.TaotaoResult;



@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private JedisClientCluster jedisClientCluster;
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EasyUIResult getContentList(Long categoryId, int page, int rows) {
		// 本机分类ID分页查询content
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), list);
		return easyUIResult;
	}

	@Override
	public TaotaoResult insertContent(TbContent content) {
	
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		//清空对应的缓存数据
		try{
			jedisClientCluster.hdel("CONTENT_KEY", content.getCategoryId().toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentListByCid(Long cid) {
		//查询缓存
		//判断缓存中是否有数据，如果有数据直接返回数据
		//基本原则：添加缓存不能影响正常的业务代码
		try{
			String jsonResult = jedisClientCluster.hget("CONTENT_KEY",cid + "");
			//判断得到的结果集是否为空
			if(StringUtils.isNotBlank(jsonResult)){
				//将json数据转为java对象返回
				List<TbContent> list = JsonUtils.jsonToList(jsonResult, TbContent.class);
				return list;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		//将查询的数据存入缓存
		try{
			String json = JsonUtils.objectToJson(list);
			jedisClientCluster.hset("CONTENT_KEY", cid + "", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

	
}
