<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.tcs.ilp.t210.control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="com.tcs.ilp.t210.model.UserBean"%><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/query.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.accordion.js"></script>
<style type="text/css" title="layout" media="screen">
@import url("<%=request.getContextPath()%>/css/style.css");
</style>
<script  type="text/javascript">
<jsp:include page="../js/check_2.js" />
</script>


<title>Add New Customer</title>
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
<%UserBean serviceProvider=(UserBean)session.getAttribute("serviceproviderid"); 
long serviceProviderId=(serviceProvider.getUserId());
%>
<form method="post" action="<%=request.getContextPath() %>/BillProcessorController"
	name="Customer" onSubmit="return Valid();">
<center>
<p><h2><b>Add New Customer</b></h2></p>
</center>
<table cellpadding="10">
	<tr>
		<td>Customer Name<font color="bb0000">*</font></td>
		<td><input type="text" name="customerName" onblur="CustName()" /></td>
		<td id="1" style="color: red"></td>
	</tr>
	<tr>
		<td>Customer Address<font color="bb0000">*</font></td>
		<td><textarea  rows="4" cols="25" name="customerAddress"
			onblur="CustAddress()"></textarea></td>
		<td id="2" style="color: red"></td>
	</tr>
	<tr>
		<td>Document Status</td>
		<td><input type="radio" name="customerStatus" value="Verified"
			/>Verified <input type="radio"
			name="customerStatus" value="Not Verified" checked="checked" />Not Verified</td>
	</tr>
	<tr><%session.setAttribute("serviceprovider",serviceProvider); %>
		<td><input type="submit" name="option" value="Add Customer" /></td>
		<td><input type="reset" value="Reset" onclick="location.reload()" />&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/CustomerHome.jsp'">Back</button></td>
	</tr>
</table>
</form>
<font size="1.8"> Fields marked with asterisk(<font color="bb0000">*</font>)
are mandatory</font></center>
</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
