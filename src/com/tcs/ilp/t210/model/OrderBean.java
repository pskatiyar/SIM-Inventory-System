package com.tcs.ilp.t210.model;


public class OrderBean {
	private int orderId;
	private int SPID;
	private int deliveredQuantity;
	public int getDeliveredQuantity() {
		return deliveredQuantity;
	}
	public void setDeliveredQuantity(int deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}
	private String orderDate;
	private int quantity;
	private String orderStatus;
	private int manufacturerId;
	private String priority;
	private String location; 
	private String imsiType;
	private String subscriptionType;
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setSPID(int sPID) {
		SPID = sPID;
	}
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public OrderBean(int orderId, int sPID, String orderDate, int quantity,
			String orderStatus, int manufacturerId, String priority,
			String location, String imsiType, String subscriptionType) {
		super();
		this.orderId = orderId;
		SPID = sPID;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
		this.manufacturerId = manufacturerId;
		this.priority = priority;
		this.location = location;
		this.imsiType = imsiType;
		this.subscriptionType = subscriptionType;
	}
	public int getOrderId() {
		return orderId;
	}
	public int getSPID() {
		return SPID;
	}
	public int getManufacturerId() {
		return manufacturerId;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImsiType() {
		return imsiType;
	}
	public void setImsiType(String imsiType) {
		this.imsiType = imsiType;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	
	@Override
	public String toString() {
		return "OrderBean [SPID=" + SPID + ", imsiType=" + imsiType
				+ ", location=" + location + ", manufacturerId="
				+ manufacturerId + ", orderDate=" + orderDate + ", orderId="
				+ orderId + ", orderStatus=" + orderStatus + ", priority="
				+ priority + ", quantity=" + quantity + ", subscriptionType="
				+ subscriptionType + "]";
	}
	public OrderBean() {
		super();
	} 
}
