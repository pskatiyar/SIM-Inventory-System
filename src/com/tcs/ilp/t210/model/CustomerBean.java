package com.tcs.ilp.t210.model;

public class CustomerBean {
	private long serviceProviderId;
	private long customerId;
	private String customerName;
	private String customerAddress;
	private String customerDocumentStatus;
	
	//Initialize getters and setters
	
	public long getCustomerId() {
		return customerId;
	}
	public long getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerDocumentStatus() {
		return customerDocumentStatus;
	}
	public void setCustomerDocumentStatus(String customerDocumentStatus) {
		this.customerDocumentStatus = customerDocumentStatus;
	}
	

}
