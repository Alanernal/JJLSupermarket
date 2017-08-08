/*
   Class Condition
   Author Mr.Z
   Date 2016.06.02
 */

package net.alily.utils;

import java.math.BigDecimal;

public class Condition {
	//查询类型
	private int selectType;

	//商品
	private String commodityId;
	//标签
	private String brand;
	//主题
	private String store;
	//类型
	private String commodityType;
	//折扣
	private BigDecimal discount;

	//用户
	//用户ID
	private String userId;
	//姓名
	private String name;


	//订单
	//订单编号
	private String orderId;
	//时间段 起始
	private String orderStartTime;
	//时间段 结束
	private String orderEndTime;
	//收件人电话
    private String phone;

	public int getSelectType() {
		return selectType;
	}

	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public String getBrand() {
		return brand;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}

