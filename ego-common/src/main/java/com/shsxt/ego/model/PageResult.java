package com.shsxt.ego.model;

import java.io.Serializable;
import java.util.List;

/**
 * 封装datagrid控件需要的数据模型
 * **/
public class PageResult<T> implements Serializable{
	private List<T> rows;
	private Long total;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
