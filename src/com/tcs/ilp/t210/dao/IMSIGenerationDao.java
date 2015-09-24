package com.tcs.ilp.t210.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.t210.model.CustomerBean;
import com.tcs.ilp.t210.model.SimBean;
import com.tcs.ilp.t210.util.DbConnection;

public class IMSIGenerationDao 
{
	Connection connection; 
	public IMSIGenerationDao() {
		super();
		try {
			connection = DbConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int checkStatus(int orderId,int spid)
	{
		System.out.println("in check status");
		PreparedStatement pr=null;
		ResultSet rs=null;
		try {
			pr = connection.prepareStatement("select orderStatus from OrderDetails where orderId=? and SPID=?");
			pr.setInt(1, orderId);
			pr.setInt(2, spid);
			rs=pr.executeQuery();
			if(rs.next())
			{
				if(rs.getString("orderStatus").equalsIgnoreCase("paid"))
				{
					System.out.println(rs.getString(1));
					return 1;
				}
				else
				{
					return 0;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return -1;
		
	}
	public ArrayList<SimBean> untaggedSim(int orderId)
	{
		ArrayList<SimBean> listOfSimWithoutIMSI=new ArrayList<SimBean>();
		PreparedStatement pr=null;
		ResultSet rs=null;
		try {
			pr = connection.prepareStatement("select * from SimDetails where orderId=? and imsiNumber is null");
			pr.setInt(1, orderId);
			rs=pr.executeQuery();
			
			while(rs.next())
			{
				SimBean simBeanObj=new SimBean();
				simBeanObj.setIccidNumber(rs.getLong(1));
				simBeanObj.setOrderId(rs.getInt(2));
				simBeanObj.setImsiNumber(rs.getLong(3));
				simBeanObj.setCustomerId(rs.getInt(4));
				simBeanObj.setSimRate(rs.getInt(5));
				
				listOfSimWithoutIMSI.add(simBeanObj);
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return listOfSimWithoutIMSI;
	}
	public boolean taggingIMSINumber(ArrayList<SimBean> untaggSimList)
	{
		int i=0;
		PreparedStatement pr=null;
		try
		{
			
		System.out.println("in sim tagg");
		  for(SimBean current:untaggSimList)
		   {
			 pr=connection.prepareStatement("update SimDetails set imsiNumber=imsi.nextval where iccid=?");
			 pr.setLong(1, current.getIccidNumber());
			 i=pr.executeUpdate();
		   } 
		  if(i>0)
		  {
			  return true;
		  }
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
		}
	public ArrayList<SimBean> untagged(int spid)
	{
		System.out.println("untagged before");
		ArrayList<SimBean> untaggedListCust=new ArrayList<SimBean>();
		PreparedStatement pr=null;
		ResultSet rs=null;
		SimBean sb=null;
		try {
			pr=connection.prepareStatement("select iccid, IMSINUMBER from SimDetails where orderId in(select orderId from OrderDetails where SPID=?) and customerId is null and IMSINUMBER is not null");
			pr.setInt(1, spid);
			rs=pr.executeQuery();
		while(rs.next())
		{
			sb=new SimBean();
			sb.setIccidNumber(rs.getLong(1));
			sb.setImsiNumber(rs.getLong(2));
			untaggedListCust.add(sb);
			System.out.println(rs.getLong(1));
		}
		System.out.println("after query");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return untaggedListCust;
	}
	public int checkVerification(long custid)
	{
		System.out.println("in dao check verification");
		PreparedStatement pr=null;
		ResultSet rs=null;
		try {
			pr=connection.prepareStatement("select DocumentStatus from Customer where customerId=?");
			pr.setLong(1, custid);
			rs=pr.executeQuery();
			if(rs.next())
			{
			if(rs.getString("DocumentStatus").equalsIgnoreCase("Verified"))
			{
				System.out.println(rs.getString("DocumentStatus"));
				return 1;
			}
			else
			{
				return 0;
			}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public boolean customertag(long custid,long iccid)
	{
		PreparedStatement pr=null;
		try {
			pr=connection.prepareStatement("update SimDetails set customerId=? where imsiNumber=?");
			pr.setLong(1,custid);
			pr.setLong(2, iccid);
			int i=pr.executeUpdate();
			if(i>0)
			{
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<CustomerBean> getServiceProviderCustomerList(int serviceProviderId) 
	{
		
		ArrayList<CustomerBean>serviceProviderCustomerList=new ArrayList<CustomerBean>();
		PreparedStatement pr=null;
		ResultSet rs=null;
		CustomerBean customerBeanObj=null;
		try {
			pr=connection.prepareStatement("select * from Customer where customerId in((select customerId from SimDetails where orderId in(select orderId from OrderDetails where SPID=?) and customerId is not null))");
			pr.setInt(1, serviceProviderId);
			rs=pr.executeQuery();
			while(rs.next())
			{
				customerBeanObj=new CustomerBean();
				customerBeanObj.setCustomerId(rs.getLong(1));
				customerBeanObj.setCustomerName(rs.getString(2));
				customerBeanObj.setCustomerAddress(rs.getString(3));
				serviceProviderCustomerList.add(customerBeanObj);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return serviceProviderCustomerList;
		
	}
	public ArrayList<Integer> getOrderList(int serviceProviderId) 
	{
		ArrayList<Integer>orderList=new ArrayList<Integer>();
		PreparedStatement pr=null;
		ResultSet rs=null;
		try {
			pr=connection.prepareStatement("select orderId from OrderDetails where SPID=?");
			pr.setInt(1, serviceProviderId);
			rs=pr.executeQuery();
			while(rs.next())
			{
				orderList.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return orderList;
	}
	public ArrayList<CustomerBean> getCustomerListUntagged(int serviceProviderId)
	{
		System.out.println("in dao");
		ArrayList<CustomerBean>customerListUntagged=new ArrayList<CustomerBean>();
		PreparedStatement pr=null;
		ResultSet rs=null;
		CustomerBean customerBeabObj=null;
		try {
			pr=connection.prepareStatement("select * from customer where SPID=? and DOCUMENTSTATUS='Verified' and customerid not in(select customerId from SimDetails where orderId in(select orderId from OrderDetails where SPID=? and customerid is not null))");
			pr.setInt(1, serviceProviderId);
			pr.setInt(2, serviceProviderId);
			rs=pr.executeQuery();
			while(rs.next())
			{
				customerBeabObj=new CustomerBean();
				customerBeabObj.setCustomerId(rs.getLong(1));
				customerBeabObj.setCustomerName(rs.getString(2));
				customerBeabObj.setCustomerAddress(rs.getString(3));
				System.out.println(customerBeabObj.getCustomerName());
				customerListUntagged.add(customerBeabObj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return customerListUntagged;
	}

}
