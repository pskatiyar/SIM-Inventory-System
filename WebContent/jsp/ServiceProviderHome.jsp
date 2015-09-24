<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="com.tcs.ilp.t210.model.UserBean"%><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<!--<link rel="stylesheet" href="../css/jquery.ui.all.css">-->
<script src="<%=request.getContextPath() %>/js/jquery-1.5.1.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath() %>/js/query.ui.widget.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.ui.accordion.js"></script>
<!--<link rel="stylesheet" href="../css/demos.css">-->
<title>Welcome to SIM INVENTORY SYSTEM</title>
<style type="text/css" title="layout" media="screen">
@import url("<%=request.getContextPath() %>/css/style.css");
</style>
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
<h2>A Complete Managment System</h2>
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

	<h4><a href="#">View Orders and Pay Bill</a></h4>
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
		<li><a href="<%=request.getContextPath()%>/BillProcessorController?option=View Orders List">IMSI Generation</a></li>
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
<%UserBean currentUser=(UserBean)session.getAttribute("userdetail");
session.setAttribute("sp",currentUser);%>
<h1> Welcome <%=currentUser.getName() %> </h1>
</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
