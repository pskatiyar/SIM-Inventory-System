package com.tcs.ilp.t210.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tcs.ilp.t210.model.BillAdminBean;
import com.tcs.ilp.t210.model.BillBean;
import com.tcs.ilp.t210.model.OrderBean;
import com.tcs.ilp.t210.model.OrderBillComplaintBean;
import com.tcs.ilp.t210.model.UserBean;
import com.tcs.ilp.t210.util.DbConnection;

public class SimDao {
	
	Connection con=null;
	OrderBean orderObj=null;
	BillBean billObj=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	public SimDao() {
		super();
		try {
			con=DbConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<OrderBean> viewAllOrderDetails(int adminId)
	{
		ArrayList<OrderBean> orderList=new ArrayList<OrderBean>();
		String sql="select * from OrderDetails where spid in(select spId from tag_sp_admin where adminid=?)";
		
		
		try{
			
			 ps=con.prepareStatement(sql);
			 ps.setInt(1, adminId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				orderObj=new OrderBean();
				orderObj.setOrderId(rs.getInt(1));
				orderObj.setSPID(rs.getInt(2));
				orderObj.setOrderDate(rs.getString(3));
				orderObj.setQuantity(rs.getInt(4));
				orderObj.setOrderStatus(rs.getString(5));
				orderObj.setManufacturerId(rs.getInt(6));
				orderObj.setPriority(rs.getString(7));
				orderObj.setLocation(rs.getString(8));
				orderObj.setImsiType(rs.getString(9));
				orderObj.setSubscriptionType(rs.getString(10));
				orderObj.setDeliveredQuantity(rs.getInt(11));
				orderList.add(orderObj);
			
			}
		
		
		}
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return orderList;
	}
	
	
	public OrderBean viewOrderDetailsByOrderId(ArrayList<OrderBean> orderDetail,int orderId)
	{
		
		String sql="select * from OrderDetails where ORDERID=?";
		
		try{
			
			 	ps=con.prepareStatement(sql);
			 	ps.setInt(1, orderId);
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					orderObj=new OrderBean();
					orderObj.setOrderId(rs.getInt(1));
					orderObj.setSPID(rs.getInt(2));
					orderObj.setOrderDate(rs.getString(3));
					orderObj.setQuantity(rs.getInt(4));
					orderObj.setOrderStatus(rs.getString(5));
					orderObj.setManufacturerId(rs.getInt(6));
					orderObj.setPriority(rs.getString(7));
					orderObj.setLocation(rs.getString(8));
					orderObj.setImsiType(rs.getString(9));
					orderObj.setSubscriptionType(rs.getString(10));
					orderObj.setDeliveredQuantity(rs.getInt(11));
				}
			}	

			catch(SQLException d)
			{
			d.printStackTrace();
			}
			finally{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
				
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				}
		return orderObj;
	
	}
	
	
	public BillBean billGeneration(OrderBean orderObj)
	{

		 PreparedStatement ps1=null;
		 PreparedStatement ps2=null;

		double singleCost=50,bill=0;
		double dualCost=70;
		
		System.out.println("generating");
		if(orderObj.getImsiType().equalsIgnoreCase("single"))
		{
			
			bill=singleCost*orderObj.getQuantity();
			
		}
		else
		{
			bill=dualCost*orderObj.getQuantity();	
			
		}
		try{
			System.out.println("generating");
			 String sql="update OrderDetails set  ORDERSTATUS='BILL GENERATED' WHERE ORDERID=?";
			 String sql1="insert into BILL (BILLNO,BILLAMOUNT,BILLSTATUS,ORDERID)VALUES(BILLNO.NEXTVAL,?,'UNPAID',?)";
			 String sql2="select * from BILL where ORDERID=?";
			
			 ps=con.prepareStatement(sql);
			 ps.setInt(1, orderObj.getOrderId());
			 ps.executeUpdate();
			
			 ps1=con.prepareStatement(sql1);
			 ps1.setDouble(1, bill);
			 ps1.setInt(2, orderObj.getOrderId());
			 ps1.executeUpdate();
			
			 ps2=con.prepareStatement(sql2);
			 ps2.setInt(1, orderObj.getOrderId());
			System.out.println(bill);
			rs=ps2.executeQuery();
			while(rs.next())
			{
				 billObj=new BillBean();
				 billObj.setBillNo(rs.getInt(1));
				 billObj.setBillDate(rs.getString(2));
				 billObj.setBillAmount(rs.getDouble(3));
				 billObj.setBillStatus(rs.getString(4));
				 billObj.setPaymentMode(rs.getString(5));
				 billObj.setOrderId(rs.getInt(6));
				 
				
			}
		}
		
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
				if(ps1!=null)
				{
					ps1.close();
			
				}
				if(ps2!=null)
				{
					ps2.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		
			return billObj;
		}
	
	
	
	
	public BillBean billRegeneration(OrderBean orderBill)
	{

		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;

		double singleCost=50,bill=0;
		double dualCost=70;
		BillBean b=null;
		if(orderBill.getImsiType().equalsIgnoreCase("single"))
		{
		
		bill=singleCost*orderBill.getDeliveredQuantity();
		System.out.println(bill);
		}
		else
		{
		bill=dualCost*orderBill.getDeliveredQuantity();	
		
		}
		try{
			String sql="update OrderDetails set  ORDERSTATUS='BILL REGENERATED' WHERE ORDERID=?";
			String sql1="delete from BILL where orderid=?";
			String sql2="insert into BILL (BILLNO,BILLAMOUNT,BILLSTATUS,ORDERID)VALUES(BILLNO.NEXTVAL,?,'UNPAID',?)";
			String sql3="select * from BILL where ORDERID=?";
		
	
			ps=con.prepareStatement(sql);
			ps.setInt(1, orderBill.getOrderId());
			ps.executeUpdate();
		
			ps1=con.prepareStatement(sql1);
			ps1.setInt(1, orderBill.getOrderId());
			ps1.executeUpdate();
			System.out.println(bill);
			 ps2=con.prepareStatement(sql2);
			ps2.setDouble(1, bill);
			ps2.setInt(2, orderBill.getOrderId());
			ps2.executeUpdate();
		
			 ps3=con.prepareStatement(sql3);
			ps3.setInt(1, orderBill.getOrderId());
			
			rs=ps3.executeQuery();
		
			while(rs.next())
				{
					billObj=new BillBean();
					billObj.setBillNo(rs.getInt(1));
					billObj.setBillDate(rs.getString(2));
					billObj.setBillAmount(rs.getDouble(3));
					billObj.setBillStatus(rs.getString(4));
					billObj.setPaymentMode(rs.getString(5));
					billObj.setOrderId(rs.getInt(6));
				
		
				}
			}

		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
				if(ps1!=null)
				{
					ps1.close();
			
				}
				if(ps2!=null)
				{
					ps2.close();
			
				}
				if(ps3!=null)
				{
					ps3.close();
			
				}
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}

		return billObj;
	}
	
	public ArrayList<BillAdminBean> viewBills(int adminId)
	{
		ArrayList<BillAdminBean> billAdList=new ArrayList<BillAdminBean>();
		BillAdminBean billAd=null;
		//String sql1=" select b.billno,b.billamount,b.billstatus,b.paymentmode,b.orderid,b.billdate,c.complaintstatus from Bill b left join  complaintdetails c on b.orderid=c.orderid where b.orderid in(select orderid from orderdetails where spid in(select spid from tag_sp_admin where adminid=?))";
		String sql=" select o.spid,b.billno,b.billamount,b.billstatus,b.paymentmode,b.orderid,b.billdate from Bill b left join  orderdetails o on b.orderid=o.orderid where spid in(select spid from tag_sp_admin where adminid=?)";
		try{
				ps=con.prepareStatement(sql);
			 	ps.setInt(1, adminId);
				rs=ps.executeQuery();
				while(rs.next())
				{
					billAd=new BillAdminBean();
					billAd.setSpid(rs.getInt(1));
					billAd.setBillno(rs.getInt(2));
					billAd.setBillamount(rs.getDouble(3));
					billAd.setBillstatus(rs.getString(4));
					billAd.setPaymentmode(rs.getString(5));
					billAd.setOrderid(rs.getInt(6));
					billAd.setBilldate(rs.getString(7));
					billAdList.add(billAd);
				
				}
		
			}
			catch(SQLException d)
			{
			d.printStackTrace();
			}
			finally{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
				
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				}
			
		return billAdList;
	}
	
	
	public ArrayList<OrderBean> viewRegenerateBill(int adminId)
	
	{
		ArrayList<OrderBean> orderList=new ArrayList<OrderBean>();
	
		String sql="select * from OrderDetails where QUANTITYAFTERCREATION<QUANTITY and ORDERSTATUS='BILL GENERATED' and spid in(select spid from tag_sp_admin where adminid=?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
		
			ps=con.prepareStatement(sql);
			ps.setInt(1, adminId);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				orderObj=new OrderBean();
				orderObj.setOrderId(rs.getInt(1));
				orderObj.setSPID(rs.getInt(2));
				orderObj.setOrderDate(rs.getString(3));
				orderObj.setQuantity(rs.getInt(4));
				orderObj.setOrderStatus(rs.getString(5));
				orderObj.setManufacturerId(rs.getInt(6));
				orderObj.setPriority(rs.getString(7));
				orderObj.setLocation(rs.getString(8));
				orderObj.setImsiType(rs.getString(9));
				orderObj.setSubscriptionType(rs.getString(10));
				orderObj.setDeliveredQuantity(rs.getInt(11));
				orderList.add(orderObj);
			}
		}

			catch(SQLException d)
			{
				d.printStackTrace();
			}
			finally{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
				
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				}
			
			return orderList;
	}
	public int checkDateDifference(int billNo)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String sql="(select b.BILLNO from BILL b where (sysdate-BILLDATE)>7 and billstatus='UNPAID' and orderid not in (select orderid from complaintdetails))UNION"+
"(select b.billno  from bill b where (sysdate-BILLDATE)>7 and b.billstatus='UNPAID' and orderid in (select orderid from complaintdetails c where b.orderid=c.orderid and c.complaintstatus='RESOLVED' and (sysdate-c.resolveddate)>7))";	
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getInt(1)==billNo)
					return 1;
			}
			
	}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}


		return 0;
		
	}
	public ArrayList<OrderBillComplaintBean> viewOrderDetailsByServiceProviderId(int spid)
	{
		ArrayList<OrderBillComplaintBean> orderBillComplaintList=new ArrayList<OrderBillComplaintBean>();
		
		String sql="SELECT * FROM OrderDetails od LEFT JOIN BILL b ON od.ORDERID=b.ORDERID LEFT JOIN ComplaintDetails cd ON od.ORDERID=cd.ORDERID  where SPID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
		
			ps=con.prepareStatement(sql);
			ps.setInt(1,spid );
			rs=ps.executeQuery();
			
		while(rs.next())
		{
			OrderBillComplaintBean orderObj=new OrderBillComplaintBean();
			
			System.out.println(rs.getString(5));
			orderObj.setOrderId(rs.getInt(1));
			orderObj.setSPID(rs.getInt(2));
			orderObj.setOrderDate(rs.getString(3));
			orderObj.setQuantity(rs.getInt(4));
			orderObj.setOrderStatus(rs.getString(5));
			orderObj.setManufacturerId(rs.getInt(6));
			orderObj.setPriority(rs.getString(7));
			orderObj.setLocation(rs.getString(8));
			orderObj.setImsiType(rs.getString(9));
			orderObj.setSubscriptionType(rs.getString(10));
			orderObj.setBillNo(rs.getInt(12));
			
			if(rs.getString(15)!=null)
			{
				orderObj.setBillStatus(rs.getString(15));
			}
			else
			{
				orderObj.setBillStatus("NA");
			}
			
			if(rs.getString(22)!=null)
			{
				orderObj.setComplaintStatus(rs.getString(22));
			}
			else
			{
				orderObj.setComplaintStatus("NA");
			}
			orderBillComplaintList.add(orderObj);
			
		}
		
		
	}
		
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return orderBillComplaintList;
}
	
	public OrderBean viewOrderStatus(int orderId)
	{
		orderObj=new OrderBean();
		String sql="select * from OrderDetails where ORDERID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			
			ps=con.prepareStatement(sql);
			ps.setInt(1,orderId);
			rs=ps.executeQuery();
			
				while(rs.next())
				{
					
					orderObj.setOrderStatus(rs.getString(5));
				}
		}	
		
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return orderObj;
	
	}
	
	public BillBean viewOrderDetailsForOrder(int orderId)
	{
		billObj=new BillBean();
		
		String sql="select * from BILL where ORDERID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			
			ps=con.prepareStatement(sql);
			ps.setInt(1,orderId);
			rs=ps.executeQuery();
				while(rs.next())
				{
					billObj.setBillNo(rs.getInt(1));
					billObj.setBillDate(rs.getString(2));
					billObj.setBillAmount(rs.getDouble(3));
					billObj.setBillStatus(rs.getString(4));
					billObj.setPaymentMode(rs.getString(5));
					billObj.setOrderId(rs.getInt(6));
					
				}
					System.out.println(billObj.getBillStatus());
		}	
	
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return billObj;
	
	}
	
	
	public OrderBillComplaintBean viewOrderBillComplaintDetails(int orderId)
	{
		OrderBillComplaintBean objBean1= new OrderBillComplaintBean();
		String sql=" SELECT  ORDERSTATUS,COMPLAINTSTATUS FROM  OrderDetails od  LEFT JOIN ComplaintDetails cd ON od.ORDERID=cd.ORDERID WHERE od.ORDERID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			 ps=con.prepareStatement(sql);
			 ps.setInt(1,orderId);
			 rs=ps.executeQuery();
				
				while(rs.next())
				{
					objBean1.setOrderStatus(rs.getString(1));
					if(rs.getString(2)!=null)
					{
						objBean1.setComplaintStatus(rs.getString(2));
					}
					else
					{
						objBean1.setComplaintStatus("NA");
					}
				}
		}	
		
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return objBean1;
	}
	
	public void updateBillStatus(int orderId)
	{
		String sql="update BILL set BILLSTATUS ='PAID' where ORDERID=?";
		PreparedStatement ps=null;
		try{
				ps=con.prepareStatement(sql);
				ps.setInt(1,orderId);
				int b=ps.executeUpdate();
				System.out.println(b);
				if(b!=0)
				{
					System.out.println("Bill Status Updated");
				}
		}
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
	}
	
	public void updateOrderStatus( int orderId)
	{
		String sql="update OrderDetails set  ORDERSTATUS='PAID'  where ORDERID=?";
		PreparedStatement ps=null;
		
		
		try{
		
			 ps=con.prepareStatement(sql);
			 ps.setInt(1,orderId);
			 int b=ps.executeUpdate();
				
			 if(b!=0)
				{
					System.out.println("Order Status Updated");
				}
				
			}
		
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
	}
	
	public void updatePaymentMode(int orderId, String mode)
	{
		String sql="update BILL set PAYMENTMODE  =? where ORDERID=?";
		PreparedStatement ps=null;
		
		
		try{
			
			ps=con.prepareStatement(sql);
			ps.setString(1,mode);
			ps.setInt(2,orderId);
			int b=ps.executeUpdate();
			
			if(b!=0)
				{
					System.out.println("Payment mode Updated");
				}
			}
	
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally{
			try
			{
				
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
	}


	public int checkComplainDateDifference(OrderBillComplaintBean currentBean)
	{	
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			String sql="(select b.BILLNO from BILL b where (sysdate-BILLDATE)>7 and billstatus='UNPAID' and orderid not in (select c.orderid from complaintdetails c inner join OrderDetails o on c.orderid=o.orderid where o.spid=?))UNION"+
"(select b.billno  from bill b where (sysdate-BILLDATE)>7 and b.billstatus='UNPAID' and orderid in (select orderid from complaintdetails c where b.orderid=c.orderid and c.complaintstatus='RESOLVED' and (sysdate-c.resolveddate)>7))";	
			ps=con.prepareStatement(sql);
			ps.setInt(1, currentBean.getSPID());
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getInt(1)==currentBean.getBillNo())
					return 1;
			}
			
	}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}

		return 0;
		
	}
	public ArrayList<OrderBean> viewSPOrderDetails(int spId)
	{
		ArrayList<OrderBean> orderList=new ArrayList<OrderBean>();
		String sql="select * from OrderDetails where spId=?";
		
		
		try{
			
			 ps=con.prepareStatement(sql);
			 ps.setInt(1, spId);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				orderObj=new OrderBean();
				orderObj.setOrderId(rs.getInt(1));
				orderObj.setSPID(rs.getInt(2));
				orderObj.setOrderDate(rs.getString(3));
				orderObj.setQuantity(rs.getInt(4));
				orderObj.setOrderStatus(rs.getString(5));
				orderObj.setManufacturerId(rs.getInt(6));
				orderObj.setPriority(rs.getString(7));
				orderObj.setLocation(rs.getString(8));
				orderObj.setImsiType(rs.getString(9));
				orderObj.setSubscriptionType(rs.getString(10));
				orderObj.setDeliveredQuantity(rs.getInt(11));
				orderList.add(orderObj);
			
			 }
		
		
		}
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return orderList;
	}
	public ArrayList<OrderBean> viewGenerateBillOrder(UserBean adminbean)
	{
		ArrayList<OrderBean> orderList=new ArrayList<OrderBean>();
		String sql="select * from OrderDetails where orderstatus='CREATED' and spid in(select spId from tag_sp_admin where adminid=?)";
		try{
			
			 ps=con.prepareStatement(sql);
			 ps.setInt(1, adminbean.getUserId());
			rs=ps.executeQuery();
			while(rs.next())
			{
				orderObj=new OrderBean();
				orderObj.setOrderId(rs.getInt(1));
				orderObj.setSPID(rs.getInt(2));
				orderObj.setOrderDate(rs.getString(3));
				orderObj.setQuantity(rs.getInt(4));
				orderObj.setOrderStatus(rs.getString(5));
				orderObj.setManufacturerId(rs.getInt(6));
				orderObj.setPriority(rs.getString(7));
				orderObj.setLocation(rs.getString(8));
				orderObj.setImsiType(rs.getString(9));
				orderObj.setSubscriptionType(rs.getString(10));
				orderObj.setDeliveredQuantity(rs.getInt(11));
				orderList.add(orderObj);
			
			}
		
		
		}
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		
		finally{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
			
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			}
		return orderList;
	}
	public ArrayList<BillBean> viewBillsBySPId(int spId)
	{
		ArrayList<BillBean> billList=new ArrayList<BillBean>();
		String sql=" select b.billno,b.billamount,b.billstatus,b.paymentmode,b.orderid,b.billdate,c.complaintstatus from Bill b left join  complaintdetails c on b.orderid=c.orderid where b.orderid in(select orderid from orderdetails where spid=?)";
				
		try{
				ps=con.prepareStatement(sql);
			 	ps.setInt(1, spId);
				rs=ps.executeQuery();
				while(rs.next())
				{
					billObj=new BillBean();
					billObj.setBillNo(rs.getInt(1));
					billObj.setBillAmount(rs.getDouble(2));
					billObj.setBillStatus(rs.getString(3));
					billObj.setPaymentMode(rs.getString(4));
					billObj.setOrderId(rs.getInt(5));
					billObj.setBillDate(rs.getString(6));
					billObj.setComplaintStatus(rs.getString(7));
					billList.add(billObj);
				
				}
		
			}
			catch(SQLException d)
			{
			d.printStackTrace();
			}
			finally{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
				
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				}
			
		return billList;
	}
	
	public ArrayList<BillBean> viewSPPayBills(int spId){
		ArrayList<BillBean> billList=new ArrayList<BillBean>();
		String sql="select b.billno,b.billamount,b.billstatus,b.paymentmode,b.orderid,b.billdate,c.complaintstatus from Bill b left join  complaintdetails c on b.orderid=c.orderid  where b.billstatus='UNPAID' AND b.orderid in(select orderid from orderdetails where spid=?)";
				
		try{
				ps=con.prepareStatement(sql);
			 	ps.setInt(1, spId);
				rs=ps.executeQuery();
				System.out.println(spId);
				while(rs.next())
				{
					billObj=new BillBean();
					billObj.setBillNo(rs.getInt(1));
					billObj.setBillAmount(rs.getDouble(2));
					billObj.setBillStatus(rs.getString(3));
					billObj.setPaymentMode(rs.getString(4));
					billObj.setOrderId(rs.getInt(5));
					billObj.setBillDate(rs.getString(6));
					billObj.setComplaintStatus(rs.getString(7));
					billList.add(billObj);
				
				}
		
			}
			catch(SQLException d)
			{
			d.printStackTrace();
			}
			finally{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
				
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				}
			
		return billList;
		
	}

}


