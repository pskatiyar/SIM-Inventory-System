package com.tcs.ilp.t210.model;

public class BillBean {
	private int billNo;
	private double billAmount;
	private String billStatus;
	private  String paymentMode="NA";
	private int orderId;
	private String billDate;
	private String complaintStatus;
	public BillBean(int billNo, double billAmount, String billStatus,
			String paymentMode, int orderId, String billDate,
			String complaintStatus) {
		super();
		this.billNo = billNo;
		this.billAmount = billAmount;
		this.billStatus = billStatus;
		this.paymentMode = paymentMode;
		this.orderId = orderId;
		this.billDate = billDate;
		this.complaintStatus = complaintStatus;
	}
	public String getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Override
	public String toString() {
		return "BillBean [billAmount=" + billAmount + ", billDate=" + billDate
				+ ", billNo=" + billNo + ", billStatus=" + billStatus
				+ ", orderId=" + orderId + ", paymentMode=" + paymentMode + "]";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public BillBean() {
		super();
	}
	

}
