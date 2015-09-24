package com.tcs.ilp.t210.model;

public class BillAdminBean {
	private int spid=0;
	private int billno=0;
	private double billamount=0;
	private String billstatus=null;
	private String paymentmode=null;
	private int orderid=0;
	private String billdate=null;
	public int getSpid() {
		return spid;
	}
	public void setSpid(int spid) {
		this.spid = spid;
	}
	public int getBillno() {
		return billno;
	}
	public void setBillno(int billno) {
		this.billno = billno;
	}
	public double getBillamount() {
		return billamount;
	}
	public void setBillamount(double billamount) {
		this.billamount = billamount;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
}
