package org.taru.lanqiao.model;

public class Order {

	private String orderId;
	private String orderUserId;
	private String orderDatetime;
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getOrderUserId() {
		return orderUserId;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}

}
