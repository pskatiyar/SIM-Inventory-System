<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.tcs.ilp.t210.model.OrderBean"%>
    <%@page import="com.tcs.ilp.t210.model.BillBean"%>
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
<title>View Details</title>
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
<form action="<%=request.getContextPath() %>/BillProcessorController" method="post">
<%BillBean billObj=new BillBean();
billObj=(BillBean)session.getAttribute("billDetail"); %>
<div style="border: 1px inset #000;">

<table>
<tr><td><h1>Bill Details</h1></td></tr>
<tr><td><b>Bill No:</b></td>
<td><%=billObj.getBillNo()%></td></tr>
<tr><td><b>Bill Amount:</b></td>
<td><%= billObj.getBillAmount()%></td></tr>
<tr><td><b>Bill Date(YYYY-MM-DD):</b></td>
<td><%=billObj.getBillDate().substring(0,10)%></td></tr>
<tr><td><b>Order ID:</b></td>
<td><%=billObj.getOrderId() %></td></tr>
<tr><td><b>Bill Status:</b></td>
<td><%=billObj.getBillStatus() %></td></tr>

</table>
</div> 
<br></br>
<%session.setAttribute("billDetails",billObj); %>
<input type="submit" name="option" value="Pay Bill"/>
<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/ViewOrderDetails.jsp?page=1'">Back</button>
</form>

</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
