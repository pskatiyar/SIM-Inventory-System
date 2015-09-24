package com.tcs.ilp.t210.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.rmi.runtime.NewThreadAction;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.tcs.ilp.t210.dao.CustomerDAO;
import com.tcs.ilp.t210.dao.IMSIGenerationDao;
import com.tcs.ilp.t210.dao.SimDao;
import com.tcs.ilp.t210.model.BillAdminBean;
import com.tcs.ilp.t210.model.BillBean;
import com.tcs.ilp.t210.model.CustomerBean;
import com.tcs.ilp.t210.model.OrderBean;
import com.tcs.ilp.t210.model.OrderBillComplaintBean;
import com.tcs.ilp.t210.model.SimBean;
import com.tcs.ilp.t210.model.UserBean;

/**
 * Servlet implementation class BillProcessorController
 */
public class BillProcessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDao;
	private SimDao simDao;
	private IMSIGenerationDao imsiDao;
	//set the redirect pages
	private static String add="/jsp/AddCustomer.jsp";
	private static String insert = "/jsp/AddSuccessful.jsp";
	private static String edit = "/jsp/EditCustomer.jsp";
	private static String view = "/jsp/ViewCustomer.jsp?page=1";
	private static String editSuccess = "/jsp/EditSuccessful.jsp";
	private static String orderStatus="/jsp/ViewOrderStatus.jsp?page=1";
	private static String displayBillsAd="/jsp/DisplayBillsAd.jsp?page=1";
	private static String displayBills="/jsp/DisplayBills.jsp?page=1";
	private static String viewDetails="/jsp/ViewDetails.jsp";
	private static String viewBills="/jsp/ViewBill.jsp";
	private static String viewRegeneratedBill="/jsp/ViewRegeneratedBill.jsp";
	private static String viewOrderStatus1="/jsp/ViewOrderStatus1.jsp?page=1";
	private static String viewUpdateDetails="/jsp/ViewUpdateDetails.jsp";
	private static String viewOrderDetails="/jsp/ViewOrderDetails.jsp?page=1";
	private static String viewBillDetails="/jsp/ViewBillDetails.jsp";
	private static String noBill="/jsp/NoBill.jsp";
	private static String payBill="/jsp/PayBill.jsp";
	private static String billPaid="/jsp/BillPaid.jsp";
	private static String complaintOpen="/jsp/ComplaintOpen.jsp";
	private static String success="/jsp/Success.jsp";
	private static String errorPage="/jsp/Error.jsp";
	private static String sperrorPage="/jsp/SPError.jsp";
	private static String untaggedlist="/jsp/UntaggedList.jsp?page=1";
	private static String imsi="/jsp/IMSI.jsp?page=1";
	private static String iccidView="/jsp/ICCIDView.jsp?page=1";
	private static String customer="/jsp/Customer.jsp";
	private static String serviceProviderCustomerList="/jsp/ServiceProviderCustomerList.jsp";
	private static String successAction="/jsp/SuccessAction.jsp";
	private static String customerlistUntagged="/jsp/CustomerListUntagged.jsp?page=1";
	private static String noRegeneratedBill="/jsp/NoRegeneration.jsp";
	
	/**
	 * Default constructor. 
	 */
	public BillProcessorController() {
		// TODO Auto-generated constructor stub
		customerDao=new CustomerDAO();
		simDao=new SimDao();
		imsiDao=new IMSIGenerationDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		ArrayList<OrderBean> orderList=new ArrayList<OrderBean>();
		ArrayList<BillBean> billList=new ArrayList<BillBean>();
		OrderBean ordBean=new OrderBean();
		BillBean billObj=new BillBean();
		OrderBillComplaintBean billObj1=new OrderBillComplaintBean(); 
		ArrayList<OrderBillComplaintBean> orderBillComplaintList=new ArrayList<OrderBillComplaintBean>();
		HttpSession session=request.getSession();
		ArrayList<SimBean> listOfSim=new ArrayList<SimBean>();
		//IMSIGenerationDao im=new IMSIGenerationDao();
		String redirect="";
		String action=request.getParameter("option");
		System.out.println("option : "+action);
		//Method to collect the customer details and pass it to the CustomerDAO for adding in database
		if(request.getParameter("option").equalsIgnoreCase("Add a customer"))
				{
				session.setAttribute("serviceproviderid", session.getAttribute("sp"));
				redirect=add;
				}
		else if(request.getParameter("option").equalsIgnoreCase("View Customers"))
		{
			
		UserBean serviceProvider=(UserBean)session.getAttribute("sp");
		long requestedServiceProviderId=serviceProvider.getUserId();
		ArrayList<CustomerBean> customerList=customerDao.viewAllCustomers(requestedServiceProviderId);
		request.setAttribute("customerlist", customerList);
		session.setAttribute("serviceprovider", serviceProvider);
		redirect=view;
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Order Status"))
		{
			UserBean ubean=(UserBean)session.getAttribute("admin");
			int adminId=ubean.getUserId();
			orderList=simDao.viewAllOrderDetails(adminId);
			//out.println(orderList);
			session.setAttribute("oList", orderList);
			redirect=orderStatus;
		}
		else if(request.getParameter("option").equalsIgnoreCase("View bills"))
		{
			UserBean ubean=(UserBean)session.getAttribute("admin");
			ArrayList<BillAdminBean> billAdList=new ArrayList<BillAdminBean>();
			int adminId=ubean.getUserId();
			billAdList=simDao.viewBills(adminId);
			session.setAttribute("billList", billAdList);
			redirect=displayBillsAd;
		
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Order Details"))
		{
		
			
			ArrayList<OrderBean> orderDetail=new ArrayList<OrderBean>();
			int orderId=Integer.parseInt(request.getParameter("view"));
			
			orderDetail=(ArrayList<OrderBean>)session.getAttribute("viewList");
			OrderBean oderObj=simDao.viewOrderDetailsByOrderId(orderDetail,orderId);
			session.setAttribute("orderDetail", oderObj);
			redirect=viewDetails;

		}
		else if(request.getParameter("option").equalsIgnoreCase("Generate Bill"))
		{
		OrderBean orderBill=new OrderBean();
		orderBill=(OrderBean)session.getAttribute("orderDetails");
		if(orderBill.getOrderStatus().equalsIgnoreCase("CREATED"))
		{
		 BillBean billDetails=simDao.billGeneration(orderBill);
		 session.setAttribute("orderDetails", orderBill);
	     session.setAttribute("billDetails",billDetails);
	     System.out.println(billDetails);
	     redirect=viewBills;

		}
		else{
			String error="Sorry, you cannot generate the bill.";
			request.setAttribute("error", error);
			redirect=errorPage;
		}
		}
		else if(request.getParameter("option").equalsIgnoreCase("Regenerate Bill"))
		{
			
			OrderBean orderBill=new OrderBean();
			orderBill=(OrderBean)session.getAttribute("orderDetails");
		 if(orderBill.getOrderStatus().equalsIgnoreCase("BILL GENERATED"))
			{
			BillBean billDetails=simDao.billRegeneration(orderBill);
			 session.setAttribute("orderDetails", orderBill);
		     session.setAttribute("billDetails",billDetails);
		     System.out.println(billDetails);
		     redirect=viewRegeneratedBill;

			}
			
			}
		else if(request.getParameter("option").equalsIgnoreCase("Bill Regeneration"))
		{
			UserBean ubean=(UserBean)session.getAttribute("admin");
			int adminId=ubean.getUserId();
			orderList=simDao.viewRegenerateBill(adminId);
			 if(orderList.size()==0)
			 {
				 redirect=noRegeneratedBill;
			 }
			 else
			 {
			 session.setAttribute("oList", orderList);
			 System.out.println(orderList);
			 redirect=viewOrderStatus1;
			 }
		}
		else if(request.getParameter("option").equalsIgnoreCase("Order Details"))
		{
			ArrayList<OrderBean> orderDetail=new ArrayList<OrderBean>();
			int orderId=Integer.parseInt(request.getParameter("view"));
			
			orderDetail=(ArrayList<OrderBean>)session.getAttribute("viewList");
			OrderBean oderObj=simDao.viewOrderDetailsByOrderId(orderDetail,orderId);
			session.setAttribute("orderDetail", oderObj);
			redirect=viewUpdateDetails;
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Orders"))
		{
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			
			//int spid=(int) session.getAttribute("userdetail");
			int spid=spdetails.getUserId();
			System.out.println(spid);
			orderBillComplaintList=simDao.viewOrderDetailsByServiceProviderId( spid);
			System.out.println(orderBillComplaintList);
			session.setAttribute("oList", orderBillComplaintList);
			if(orderBillComplaintList.size()!=0){
			redirect=viewOrderDetails;
			}
			else{
				String error="Currently there are no orders placed.";
				redirect=sperrorPage;
				request.setAttribute("error", error);
			}
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Order Bill"))
		{
			int orderId=Integer.parseInt(request.getParameter("radio"));
			System.out.println(orderId);
		
			ordBean=simDao.viewOrderStatus(orderId);
			System.out.println(ordBean.getOrderStatus());
			
			if((ordBean.getOrderStatus().equalsIgnoreCase("BILL GENERATED")) || (ordBean.getOrderStatus().equalsIgnoreCase("BILL REGENERATED"))
					|| (ordBean.getOrderStatus().equalsIgnoreCase("PAID")))
			{
			billObj=simDao.viewOrderDetailsForOrder(orderId);
			session.setAttribute("billDetail", billObj);
			redirect=viewBillDetails;
			}
			else
			{
				
				redirect=noBill;
			}
		}
		else if(request.getParameter("option").equalsIgnoreCase("Pay Bill"))
		{	
						
			billObj = (BillBean)session.getAttribute("billDetail");
			int orderId=billObj.getOrderId();
			System.out.println(orderId);
					
			billObj1=simDao.viewOrderBillComplaintDetails(orderId);
			System.out.println(billObj.getBillStatus());
			System.out.println(billObj1.getOrderStatus());
			System.out.println(billObj1.getComplaintStatus());
			
			if((billObj.getBillStatus().equalsIgnoreCase("UNPAID"))&& ((billObj1.getOrderStatus().equalsIgnoreCase("BILL GENERATED")) || (billObj1.getOrderStatus().equalsIgnoreCase("BILL REGENERATED"))) &&
					( (billObj1.getComplaintStatus().equalsIgnoreCase("RESOLVED"))|| (billObj1.getComplaintStatus().equalsIgnoreCase("NA"))))
			{	System.out.println("PAYING BILL");	
			billObj=simDao.viewOrderDetailsForOrder(orderId);
			System.out.println(	billObj);
			session.setAttribute("billDetail", billObj);
			redirect=payBill;
			}
			else if(billObj.getBillStatus().equalsIgnoreCase("PAID"))
			{	System.out.println("PAID");
				redirect=billPaid;
			}

			
			
			else if((billObj1.getComplaintStatus().equalsIgnoreCase("OPEN"))|| (billObj1.getComplaintStatus().equalsIgnoreCase("WORK IN PROGRESS")) ||  (billObj1.getComplaintStatus().equalsIgnoreCase("REOPEN")) )
			{
				redirect=complaintOpen;
			}
		}
		else if(request.getParameter("option").equalsIgnoreCase("Submit"))
		{	
			billObj = (BillBean)session.getAttribute("billDetail");
			String mode=request.getParameter("mode");
			System.out.println(mode);
			int orderId=billObj.getOrderId();
			
			simDao.updateOrderStatus(orderId);
			simDao.updateBillStatus(orderId);
			simDao.updatePaymentMode(orderId,mode);
			redirect=success;
		}
		else if(request.getParameter("option").equals("Add Customer"))
		{
			UserBean serviceProvider=(UserBean)session.getAttribute("serviceprovider");
			long currentserviceProviderId=serviceProvider.getUserId();
			CustomerBean newCustomer=new CustomerBean();
			newCustomer.setServiceProviderId(currentserviceProviderId);
			newCustomer.setCustomerName(request.getParameter("customerName"));
			newCustomer.setCustomerAddress(request.getParameter("customerAddress"));
			newCustomer.setCustomerDocumentStatus(request.getParameter("customerStatus"));
			Long customerId=customerDao.addCustomer(newCustomer);
			redirect=insert;
			session.setAttribute("serviceprovider", serviceProvider);
			request.setAttribute("customer", customerId);
			System.out.println("Customer added successfully");
		}
		//Method to collect the customer Id and pass it to the CustomerDAO for deleting in database
		else if(request.getParameter("option").equals("Delete"))
		{
			String customerId=request.getParameter("customerId");
			UserBean serviceProvider=(UserBean)session.getAttribute("serviceprovider");
			long currentServicrProviderId=serviceProvider.getUserId();
			long deletedCustomerId=Long.parseLong(customerId);
			customerDao.deleteCustomer(deletedCustomerId);
			redirect=view;
			request.setAttribute("customerlist", customerDao.viewAllCustomers(currentServicrProviderId));
			session.setAttribute("serviceproviderid", serviceProvider);
			System.out.println("Customer deleted successfully");
		}
		//Method to collect the customer details and pass it to the CustomerDAO for updating in database
		else if(request.getParameter("option").equals("Edit")){
			session.setAttribute("serviceprovider",session.getAttribute("serviceprovider"));
			request.setAttribute("customer", request.getParameter("customerId"));
			redirect=edit;
		}
		else if(request.getParameter("option").equals("Update"))
		{
			System.out.println("in Customer edit");
			String customerId=(String)request.getParameter("customerId");
			long editedCustomerId=Long.parseLong(customerId);
			CustomerBean currentCustomer=new CustomerBean();
			currentCustomer.setCustomerId(editedCustomerId);
			currentCustomer.setCustomerName(request.getParameter("customerName"));
			currentCustomer.setCustomerAddress(request.getParameter("customerAddress"));
			currentCustomer.setCustomerDocumentStatus(request.getParameter("customerStatus"));
			customerDao.updateCustomer(currentCustomer);
			redirect=editSuccess;
			request.setAttribute("customer", currentCustomer);
			System.out.println("Customer edited successfully");
		}
		else if(request.getParameter("option").equalsIgnoreCase("Ordered SIM Details"))
		{
			System.out.println("in view before");
			System.out.println(request.getParameter("orderid"));
			int orderId=Integer.parseInt(request.getParameter("orderid"));
			UserBean spbean=(UserBean)session.getAttribute("sp");
			int spid=spbean.getUserId();
			System.out.println("in view imsi");
			int flag=imsiDao.checkStatus(orderId, spid);
			if(flag==1)
			{
				session.setAttribute("sp",spbean);
				listOfSim=imsiDao.untaggedSim(orderId);
				if(listOfSim.size()>0)
				{
				session.setAttribute("sp",spbean);
				session.setAttribute("simList",listOfSim);
				redirect=untaggedlist;
				}
				else
				{
					String yes="yes1";
					session.setAttribute("sp",spbean);
					session.setAttribute("orders", orderId);
					session.setAttribute("yes",yes);
					redirect=imsi;
					
				}
				
			}
			else if(flag==-1)
			{
				String yes="yes2";
				session.setAttribute("sp",spbean);
				session.setAttribute("orderid", orderId);
				session.setAttribute("yes",yes);
				redirect=imsi;	
			}
			else if(flag==0)
			{
				String yes="yes3";
				session.setAttribute("sp",spbean);
				session.setAttribute("order", orderId);
				session.setAttribute("yes",yes);
				redirect=imsi;
			}
			
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Orders List"))
		{
			
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			String yes="yes";
			int serviceProviderId=spdetails.getUserId();
			System.out.println(serviceProviderId);
			ArrayList<Integer> orderListByserviceProviderId=imsiDao.getOrderList(serviceProviderId);
			session.setAttribute("orderList", orderListByserviceProviderId);
			session.setAttribute("spid", serviceProviderId);
			session.setAttribute("status",yes);
			redirect=imsi;
		}
		else if(request.getParameter("option").equalsIgnoreCase("Generate IMSI Number"))
		{
			ArrayList<SimBean>untagglist=(ArrayList<SimBean>)session.getAttribute("result");
			boolean flag=imsiDao.taggingIMSINumber(untagglist);
			if(flag)
			{
				String successMessage="IMSI number generated successfully";
				request.setAttribute("success", successMessage);
				redirect=successAction;
			}
			
		}
		else if(request.getParameter("option").equalsIgnoreCase("Available Sims"))
		{
			System.out.println("in controller viewavail");
			
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			String yes="yes";
			int serviceProviderId=spdetails.getUserId();
			System.out.println("in avail"+ serviceProviderId);
			
			
			
			
			
			
			//Long spid=(Long)session.getAttribute("serviceProviderId");
			Long custid=Long.parseLong(request.getParameter("customerid"));
			System.out.println("customer id"+custid);
			int check=imsiDao.checkVerification(custid);
			
			if(check==1)
			{
			ArrayList<SimBean>untaggedListCust=imsiDao.untagged(serviceProviderId);
			session.setAttribute("listiccid",untaggedListCust );
			session.setAttribute("cust", custid);
			System.out.println("after return");
			redirect=iccidView;
			}
			else if(check==0)
			{
				String no="no0";
				request.setAttribute("no",no);
				System.out.println("not verified");
				redirect=customer;
			}
			else if(check==-1)
			{
				String no="no1";
				request.setAttribute("no",no);
				System.out.println("you r not present");
				redirect=customer;
			}
		  
		
			
		}
		else if(request.getParameter("option").equalsIgnoreCase("Assign Sim"))
		{
			long custid=(Long)session.getAttribute("cust");
			Long iccid=Long.parseLong(request.getParameter("radio"));
			System.out.println(custid+"..."+iccid);
			int check=imsiDao.checkVerification(custid);
			  boolean flag=imsiDao.customertag(custid, iccid);
			  if(flag)
			  {
				  String successMessage="SIM assigned successfully. Sim with IMSI No."+iccid+"has been assigned to Customer Id"+custid;
					request.setAttribute("success", successMessage);
					
					redirect=successAction;
			  }
			
			
		}
		else if(request.getParameter("option").equalsIgnoreCase("View Customer List"))
		{
			
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			
			int serviceProviderId=spdetails.getUserId();
			
			
			
			//Long serviceProviderId=Long.parseLong(request.getParameter("serviceproviderid"));
			ArrayList<CustomerBean>serviceProviderList=new ArrayList<CustomerBean>();
			serviceProviderList=imsiDao.getServiceProviderCustomerList(serviceProviderId);
			session.setAttribute("serviceProviderCustomerList", serviceProviderList);
		
			redirect=serviceProviderCustomerList;

		}
		else if(request.getParameter("option").equalsIgnoreCase("Show Customer List"))
		{
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			int serviceProviderId=spdetails.getUserId();
			
			System.out.println("get..."+serviceProviderId);
			//Long serviceProviderId=Long.parseLong(request.getParameter("serviceproviderid"));
			//System.out.println("in servlet"+serviceProviderId);
			ArrayList<CustomerBean>customerUntagged=new ArrayList<CustomerBean>();
			customerUntagged=imsiDao.getCustomerListUntagged(serviceProviderId);
			session.setAttribute("customerListUntagged", customerUntagged);
			session.setAttribute("serviceProviderId",serviceProviderId);
			redirect=customerlistUntagged;
			
		}
		else if(request.getParameter("option").equalsIgnoreCase("Show Orders")){
			int spId=Integer.parseInt(request.getParameter("spId"));
			orderList=simDao.viewSPOrderDetails(spId);
			session.setAttribute("oList", orderList);
			redirect=orderStatus;
		}
		else if(request.getParameter("option").equalsIgnoreCase("Bill Generate")){
			UserBean adminbean=(UserBean)session.getAttribute("admin");
			orderList=simDao.viewGenerateBillOrder(adminbean);
			session.setAttribute("oList", orderList);
			if(orderList.size()!=0){
			redirect=orderStatus;
			}
			else{
				String error="Sorry, there are no orders in CREATED status.";
				redirect=errorPage;
				request.setAttribute("error", error);
			}
		}
		else if(request.getParameter("option").equalsIgnoreCase("View SP bills")){
			UserBean ubean=(UserBean)session.getAttribute("sp");
			int spId=ubean.getUserId();
			billList=simDao.viewBillsBySPId(spId);
			out.println(billList);
			session.setAttribute("billList", billList);
			if(billList.size()!=0){
				redirect=displayBills;
				}
				else{
					String error="Sorry, there are no bills.";
					redirect=sperrorPage;
					request.setAttribute("error", error);
				}
		
		}
		else if(request.getParameter("option").equalsIgnoreCase("Pay SP bills")){
			UserBean spdetails=(UserBean)session.getAttribute("sp");
			int spid=spdetails.getUserId();
			System.out.println(spid);
			orderBillComplaintList=simDao.viewOrderDetailsByServiceProviderId( spid);
			System.out.println(orderBillComplaintList);
			session.setAttribute("oList", orderBillComplaintList);
			if(orderBillComplaintList.size()!=0){
			redirect=viewOrderDetails;
			}
			else{
				String error="Currently there are no orders placed.";
				redirect=sperrorPage;
				request.setAttribute("error", error);
			}
			
		}
		RequestDispatcher rd=request.getRequestDispatcher(redirect);
		rd.forward(request, response);
	}

}
