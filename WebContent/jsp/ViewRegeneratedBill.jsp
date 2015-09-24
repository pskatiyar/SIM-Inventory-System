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
<title>Regenerated Bill</title>
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
<h4><a href="#">Bill Generation</a></h4>
	<div>
	<ul>
		<li><a href="<%=request.getContextPath() %>/jsp/TaggedSPList.jsp">View Tagged Service Provider</a></li>
		
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Order Status">View Orders</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=Bill Generate">Generate Bill</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View bills">View Bills</a></li>
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=Bill Regeneration">Regenerate Bill</a></li>
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
<%BillBean billDetails=(BillBean)session.getAttribute("billDetails");
OrderBean orderDetails=(OrderBean)session.getAttribute("orderDetails");
%>
<form>
<div style="border: 1px inset #000;">
<table>
<tr><td><h1>Order Invoice</h1></td></tr>
<tr><td><b> Bill No</b></td><td><b>Bill date(YYYY-MM-DD)</b></td><td><b>ServiceProvider ID</b></td><td><b>Quantity</b></td><td><b>IMSI Type</b></td><td><b>Bill Amount</b></td></tr>

<tr><td><%=billDetails.getBillNo() %></td>
<td><%=billDetails.getBillDate().substring(0,10) %></td>
<td><%=orderDetails.getSPID() %></td>
<td><%=orderDetails.getDeliveredQuantity() %></td>
<td><%=orderDetails.getImsiType() %></td>
<td><%=billDetails.getBillAmount()%></td></tr>
</table>
<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/ViewOrderStatus1.jsp?page=1'">Back</button>
</div></form>
</div> 
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
