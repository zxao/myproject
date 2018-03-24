package com.itheima.taotao.page;

import java.io.Serializable;
import java.util.List;

public class EsayUIResult implements Serializable{
	
	private long total;
	private List<?> rows;
	public EsayUIResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EsayUIResult(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	

}
