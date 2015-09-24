package com.tcs.ilp.t210.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import com.tcs.ilp.t210.model.*;
import com.tcs.ilp.t210.util.*;
public class CustomerDAO {
	private Connection con;

	public CustomerDAO() {
		//Initialize DataBase Connection
		try {
			con=DbConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Add customer to database
	public long addCustomer(CustomerBean currentCustomer)
	{
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			String query1="select CustomerId.nextval from dual";
			ps=con.prepareStatement(query1);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String query="insert into Customer values(?,?,?,?,?)";
				ps=con.prepareStatement(query);
				ps.setLong(1, rs.getLong(1));
				ps.setString(2, currentCustomer.getCustomerName());
				ps.setString(3, currentCustomer.getCustomerAddress());
				ps.setString(4, currentCustomer.getCustomerDocumentStatus());
				ps.setLong(5, currentCustomer.getServiceProviderId());
				ps.executeUpdate();
				return(rs.getLong(1));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}
	//Update customer to database
	public void updateCustomer(CustomerBean currentCustomer)
	{
		PreparedStatement ps=null;
		try{
			String query="update Customer set CustomerName=?,CustomerAddress=?,DocumentStatus=? where CustomerId=?";
			ps=con.prepareStatement(query);
			ps.setString(1, currentCustomer.getCustomerName());
			ps.setString(2, currentCustomer.getCustomerAddress());
			ps.setString(3, currentCustomer.getCustomerDocumentStatus());
			ps.setLong(4, currentCustomer.getCustomerId());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//Delete customer to database
	public void deleteCustomer(long currentCustomerId)
	{
		PreparedStatement ps=null;
		try{
			String query="delete from Customer where CustomerId=?";
			ps=con.prepareStatement(query);
			ps.setLong(1, currentCustomerId);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//Get customer details from customer Id
	public CustomerBean getCustomerById(long currentCustomerId)
	{
		CustomerBean currentCustomer=new CustomerBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try{
			String query="select * from Customer where CustomerId=?";
			ps=con.prepareStatement(query);
			ps.setLong(1, currentCustomerId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				currentCustomer.setCustomerId(rs.getLong(1));
				currentCustomer.setCustomerName(rs.getString(2));
				currentCustomer.setCustomerAddress(rs.getString(3));
				currentCustomer.setCustomerDocumentStatus(rs.getString(4));
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return currentCustomer;
	}
	//Get details of all the customers
	public ArrayList<CustomerBean> viewAllCustomers(long serviceProviderId)
	{
		ArrayList<CustomerBean> customerList=new ArrayList<CustomerBean>();
		CustomerBean currentCustomer=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select * from Customer where spid=?";
			ps=con.prepareStatement(query);
			ps.setLong(1, serviceProviderId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				currentCustomer=new CustomerBean();
				currentCustomer.setCustomerId(rs.getLong(1));
				currentCustomer.setCustomerName(rs.getString(2));
				currentCustomer.setCustomerAddress(rs.getString(3));
				currentCustomer.setCustomerDocumentStatus(rs.getString(4));
				customerList.add(currentCustomer);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}
	
	

}
