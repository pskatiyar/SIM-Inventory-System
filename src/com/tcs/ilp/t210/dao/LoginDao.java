package com.tcs.ilp.t210.dao;

import java.sql.*;
import java.util.ArrayList;

import com.tcs.ilp.t210.model.UserBean;
import com.tcs.ilp.t210.util.DbConnection;

public class LoginDao {
    static Connection con=null;
	public LoginDao() {
		
    	try {
			con=DbConnection.getConnection();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}  
	public  boolean checkLogin(int id, String password)
	{
	      boolean validUser = false;
	      String userPassword = "";
	      PreparedStatement loginCheck=null;
	      ResultSet loginDetails=null;
	      try {
	    	  
			loginCheck =con.prepareStatement("select password from login where userid = ?");
			
			loginCheck.setInt(1, id);
			
			loginDetails = loginCheck.executeQuery();
			
			while(loginDetails.next())
			{
			    userPassword = loginDetails.getString(1);
			}
			
			if(password.equals(userPassword))
				validUser = true;
			else
				validUser = false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return validUser;
	}
	
	public UserBean findUserType(int username)
	{
		UserBean ubean=new UserBean();
		PreparedStatement userTypeQuery=null;
		ResultSet userDetails =null;
		try {
			userTypeQuery = con.prepareStatement("select USERID,NAME,ROLE from users where userid = ?");
			
			userTypeQuery.setInt(1, username);
			
			userDetails = userTypeQuery.executeQuery();
			
			while(userDetails.next())
			{
				ubean.setUserId(userDetails.getInt(1));
				ubean.setName(userDetails.getString(2));
				ubean.setRole(userDetails.getString(3));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return ubean;
	}
	public ArrayList<UserBean> getSPDetails(int adminId){
		ArrayList<UserBean> spAdminList=new ArrayList<UserBean>();
		PreparedStatement tagSP=null;
		ResultSet spList=null;
		String sql="select t.spid,u.name from tag_sp_admin t inner join users u on u.userid=t.spid where u.role='SERVICE PROVIDER' and t.adminid=?";
		try{
			UserBean ubean=null;
			tagSP=con.prepareStatement(sql);
			tagSP.setLong(1, adminId);
			spList=tagSP.executeQuery();
			while(spList.next()){
				ubean=new UserBean();
				ubean.setUserId(spList.getInt(1));
				ubean.setName(spList.getString(2));
				spAdminList.add(ubean);
			}
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		return spAdminList;
	}
	
}
