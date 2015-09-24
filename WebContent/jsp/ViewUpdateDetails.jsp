<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="com.tcs.ilp.t210.model.OrderBean"%>
    
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
<title>Order Details</title>
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

	<h4><a href="#">User Management</a></h4>
	<div>
	<ul>
		<li><a href="#">Link1</a></li>
		<li><a href="#">Link2</a></li>
		<li><a href="#">Link3</a></li>
		<li><a href="#">Link4</a></li>
		<li><a href="#">Link5</a></li>
	</ul>
	</div>
	<h4><a href="#">Order Processing</a></h4>
	<div>
	<ul>
		<li><a href="#">Link6</a></li>
		<li><a href="#">Link7</a></li>
		<li><a href="#">Link8</a></li>
		<li><a href="#">Link9</a></li>
		<li><a href="#">Link10</a></li>
	</ul>
	</div>
	<h4><a href="#">Bill Generation</a></h4>
	<div>
	<ul>
		<li><a href="<%=request.getContextPath() %>/html/BillHome.html">Bill</a></li>
		<li><a href="<%=request.getContextPath() %>/html/CustomerModule.html">IMSI Generation & Tagging</a></li>
		<li><a href="<%=request.getContextPath() %>/jsp/CustomerHome.jsp">View & Edit Customers</a></li>
		<li><a href="#">Link14</a></li>
		<li><a href="#">Link15</a></li>
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
<%OrderBean orderObj= null;
orderObj=(OrderBean)session.getAttribute("orderDetail"); %>
<div style="border: 1px inset #000;">

<table>
<tr><td><h1>Order Details</h1></td></tr>
<tr><td><b>Order Id:</b></td>
<td><%=orderObj.getOrderId()%></td></tr>
<tr><td><b>ServiceProvider Id:</b></td>
<td><%=orderObj.getSPID() %></td></tr>
<tr><td><b>OrderDate(YYYY-MM-DD):</b></td>
<td><%=orderObj.getOrderDate().substring(0,10) %></td></tr>
<tr><td><b>Quantity:</b></td>
<td><%=orderObj.getQuantity() %></td></tr>
<tr><td><b>Created Quantity:</b></td>
<td><font color="red"><%=orderObj.getDeliveredQuantity() %></font></td></tr><tr><td>Order_Status:</td>
<td><%=orderObj.getOrderStatus() %></td></tr>
<tr><td><b>Manufacturer Id:</b></td>
<td><%=orderObj.getManufacturerId() %></td></tr>
<tr><td><b>Priority:</b></td>
<td><%=orderObj.getPriority() %></td></tr>
<tr><td><b>Location:</b></td>
<td><%=orderObj.getLocation() %></td></tr>
<tr><td><b>IMSI TYPE:</b></td>
<td><%=orderObj.getImsiType() %></td></tr>
<tr><td><b>Subscription Type:</b></td>
<td><%=orderObj.getSubscriptionType() %></td></tr>
</table>
</div> 
<br></br>
<%session.setAttribute("orderDetails",orderObj); %>
<input type="submit" name="option" value="Regenerate Bill"/>
<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/ViewOrderStatus1.jsp?page=1'">Back</button>
</form>
</div> 
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
