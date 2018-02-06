package com.xm.service.dao.util;

import java.io.Serializable;

/**
 * bootstrap-table分页插件参数
 * @author Administrator
 *
 */
public class PageParam implements Serializable{
	private static final long serialVersionUID = 8284601775894821174L;

	private String order = "asc";
	
	private int offset = 0;
	
	private int limit = 10;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
