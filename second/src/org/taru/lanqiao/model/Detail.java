package org.taru.lanqiao.model;

public class Detail {
	private String detailId;
	private String detailProductId;
	private String detailProductCount;
	private String detailProductUnit;
	private String detailProductPrice;
	private String detailTotalPrice;
	private String detailComment;
	private String detailStatus;
	private String detailOrderId;
	private String detailUserId;
	private String detailDateTime;
	private Product product;
	public String getDetailComment() {
		return detailComment;
	}
	public String getDetailDateTime() {
		return detailDateTime;
	}
	public String getDetailId() {
		return detailId;
	}
	public String getDetailOrderId() {
		return detailOrderId;
	}
	public String getDetailProductCount() {
		return detailProductCount;
	}
	public String getDetailProductId() {
		return detailProductId;
	}
	public String getDetailProductPrice() {
		return detailProductPrice;
	}
	public String getDetailProductUnit() {
		return detailProductUnit;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public String getDetailTotalPrice() {
		return detailTotalPrice;
	}
	public String getDetailUserId() {
		return detailUserId;
	}
	public Product getProduct() {
		return product;
	}
	public void setDetailComment(String detailComment) {
		this.detailComment = detailComment;
	}
	public void setDetailDateTime(String detailDateTime) {
		this.detailDateTime = detailDateTime;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public void setDetailOrderId(String detailOrderId) {
		this.detailOrderId = detailOrderId;
	}
	public void setDetailProductCount(String detailProductCount) {
		this.detailProductCount = detailProductCount;
	}
	public void setDetailProductId(String detailProductId) {
		this.detailProductId = detailProductId;
	}
	public void setDetailProductPrice(String detailProductPrice) {
		this.detailProductPrice = detailProductPrice;
	}
	public void setDetailProductUnit(String detailProductUnit) {
		this.detailProductUnit = detailProductUnit;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public void setDetailTotalPrice(String detailTotalPrice) {
		this.detailTotalPrice = detailTotalPrice;
	}
	public void setDetailUserId(String detailUserId) {
		this.detailUserId = detailUserId;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
