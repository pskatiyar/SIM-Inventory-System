<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import=" java.util.*"%>
    <%@page import="com.tcs.ilp.t210.model.BillBean"%>
    <%@page import="com.tcs.ilp.t210.dao.SimDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/query.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.accordion.js"></script>
<style type="text/css" title="layout" media="screen">
@import url("<%=request.getContextPath()%>/css/style.css");
</style>

<title>View Bills</title>
<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
</head>
<body>
<div id="container">
<div id="top">
<h1>SIM INVENTORY SYSTEM</h1>
<h2>A Complete Management System</h2>
</div>
<div id="header">
<div id="navcontainer">
<ul id="menu">
	<li><a href="#" id="current">Home</a></li>
	<li><a href="#">FAQ</a></li>
	<li><a href="#">Contact Us</a></li>
	<li><a href="#">About Us</a></li>
	<li><a href="#">Feedback</a></li>
	<li style="margin-left: 500px"><a href="<%=request.getContextPath()%>/jsp/Home.jsp">Logout</a></li>
</ul>


</div>
</div>
<div id="leftnav">
<h1>Features</h1>
<ul>
	<div id="accordion">

	<h4><a href="#">Order Processing</a></h4>
	<div>
	<ul>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Orders">View Orders</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View SP bills">View Bills</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=Pay SP bills">Pay Bills</a></li>
		</ul>
	</div>
	<h4><a href="#">IMSI Generation and Sim Tagging</a></h4>
	<div>
	<ul>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Orders List">Ordered Sim Details</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=Show Customer List">Tag Customer</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Customer List">Customer List</a></li>
	</ul>
	</div>
	<h4><a href="#">Customer Details</a></h4>
	<div>
	<ul>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=Add a customer">Add Customer</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Customers">Edit Customer Details</a></li>
	</ul>
	</div>
	</div>
</ul>

</div>
<div id="rightnav">
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
</div>
<div id="content">
<h1 align="center">Bill Details</h1>
<form action="<%=request.getContextPath() %>/BillProcessorController" method="post">
<%

SimDao dao=new SimDao(); 
String color=null;
List<BillBean> billList=new ArrayList<BillBean>();
billList=(ArrayList<BillBean>)session.getAttribute("billList");
int total=0;
total=billList.size();
if(billList.size()==0){
	out.println("Sorry");
}
else {
int page1= Integer.parseInt(request.getParameter("page"));
int pagecount;
int from=(page1-1)*10;
int to=page1*10;
if(total%10==0){
pagecount=total/10;
}
else{
	pagecount=total/10+1;
}
if(page1==pagecount){
	to=total;
}
List <BillBean> orlist=billList.subList(from , to);
Iterator<BillBean> it=orlist.iterator();
%>
<table border=1 align="center">

<tr>

<td><b>Bill Number</b></td>
<td><b>Bill Date(YYYY-MM-DD)</b></td>
<td><b>Order Id</b></td>
<td><b>Bill Amount</b></td>
<td><b>Bill Status</b></td>
<td><b>Payment Mode</b></td>
</tr>
<% 
while(it.hasNext())
{
BillBean billObj=it.next();
if(dao.checkDateDifference(billObj.getBillNo())==1)
{
	color="yellow";
}
else 
{
	color="white";
}
%>

<tr bgcolor=<%=color %>>
<td><%=billObj.getBillNo() %></td>
<td><%=billObj.getBillDate().substring(0,10) %></td>
<td><%=billObj.getOrderId() %></td>
<td><%=billObj.getBillAmount() %></td>
<td><%=billObj.getBillStatus() %></td>
<td><%=billObj.getPaymentMode() %></td></tr>

<%}%>

</table>
<br/>
<br/>
<center>
<input type="submit" value="Pay Bill"/>  
<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/AdminHome.jsp'">Back</button>
</form>
<%if(page1!=1){ %>
<a href="<%=request.getContextPath()%>/jsp/DisplayBills.jsp?page=<%=page1-1%>">PREVIOUS</a>
<%}
for(int j=1;j<page1;j++){ %>
<a href="<%=request.getContextPath()%>/jsp/DisplayBills.jsp?page=<%=j %>"><%=j %></a>

<%}%><%=page1 %>
<% 
for(int i=page1+1;i<=pagecount;i++){ %>
<a href="<%=request.getContextPath()%>/jsp/DisplayBills.jsp?page=<%=i %>"><%=i %></a>
<%} 
if(page1!=pagecount){
%><a href="<%=request.getContextPath()%>/jsp/DisplayBills.jsp?page=<%=page1+1 %>">NEXT</a><%} }%>
</center>
</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
