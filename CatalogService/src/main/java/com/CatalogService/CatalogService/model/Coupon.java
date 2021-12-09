package com.CatalogService.CatalogService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Coupon")
public class Coupon {
	@Id
	private String couponId;
	@Field
	private String category;
	@Field
	private int count;
	@Field
	private String offer;
	@Field
	private String companyName;
	
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", category=" + category + ", count=" + count + ", offer=" + offer
				+ ", companyName=" + companyName + "]";
	}
	public Coupon(String couponId, String category, int count, String offer, String companyName) {
		super();
		this.couponId = couponId;
		this.category = category;
		this.count = count;
		this.offer = offer;
		this.companyName = companyName;
	}
}
