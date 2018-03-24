package com.itheima.taotao.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.taotao.mapper.TbItemMapper;
import com.itheima.taotao.pojo.TbItem;
import com.itheima.taotao.pojo.TbItemExample;
/**
 * mybatis的分页插件PageHelper插件测试
 * @author ZXAO
 *
 */
public class PageHelperTest {
	
	
	@Test
	public void testPageHelper() {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		
		//设置分页
		PageHelper.startPage(1, 30);
		
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		System.out.println("结果集中的记录数:" + list.size());
		System.out.println("总记录数:" + pageInfo.getTotal());
		System.out.println("总页数:" + pageInfo.getPages());
		System.out.println("当前页码:" + pageInfo.getPageNum());
		
	}
	
}
