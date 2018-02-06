package com.xm.service.dao.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class RequestPageVo<T> extends PageParam implements Serializable{
	
	private static final long serialVersionUID = -8976077658413288983L;
	
	protected long total = 0;							//总记录数
	
	protected List<T> rows = Collections.emptyList();  //返回结果 

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
