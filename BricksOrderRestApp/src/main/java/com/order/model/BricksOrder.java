package com.order.model;

import java.io.Serializable;
import java.util.Date;

public class BricksOrder implements Serializable {
	
	private String orderRefNo;
	private Integer orderQty;
	private String Status;
	private Date createdDate;
	
	public String getOrderRefNo() {
		return orderRefNo;
	}
	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
