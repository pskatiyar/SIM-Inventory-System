package com.tcs.ilp.t210.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	static String url="jdbc:oracle:thin:@localhost:1521:xe";
	static String uid="piyush";
	static String pwd="tcstvm";
	static Connection con=null;
	

	public static Connection getConnection() throws ClassNotFoundException
	{
		if(con!=null)
		{
			return con;
		}
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    con=DriverManager.getConnection(url,uid, pwd);
	    
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
		
		}
	}
