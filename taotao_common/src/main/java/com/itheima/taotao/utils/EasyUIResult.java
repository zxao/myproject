package com.itheima.taotao.utils;

import java.io.Serializable;
import java.util.List;

/**
 * easyUIDataGrid对象返回值
 * <p>Title: EasyUIResult</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @version 1.0
 */
public class EasyUIResult implements Serializable{

	private Integer total;
	
	private List<?> rows;
	
	public EasyUIResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public EasyUIResult(long total, List<?> rows) {
		this.total = (int) total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
