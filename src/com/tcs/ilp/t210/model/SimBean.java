package com.tcs.ilp.t210.model;

public class SimBean 
{
	long iccidNumber;
	int orderId; 
	long imsiNumber;
	long customerId; 
	int simRate;
	public long getIccidNumber() {
		return iccidNumber;
	}
	public void setIccidNumber(long iccidNumber) {
		this.iccidNumber = iccidNumber;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public long getImsiNumber() {
		return imsiNumber;
	}
	public void setImsiNumber(long imsiNumber) {
		this.imsiNumber = imsiNumber;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public int getSimRate() {
		return simRate;
	}
	public void setSimRate(int simRate) {
		this.simRate = simRate;
	} 

}
