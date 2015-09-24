<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--<link rel="stylesheet" href="../css/jquery.ui.all.css">-->
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/query.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.accordion.js"></script>
<!--<link rel="stylesheet" href="../css/demos.css">-->
<title>Three column liquid</title>
<style type="text/css" title="layout" media="screen">
@import url("<%=request.getContextPath()%>/css/style.css");
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
<img src="../images/Sim logo.JPG" height=128 width=125 align=left style="position:relative; top: 1px"></img>
<h1>SIM INVENTORY SYSTEM</h1>
<h2>A Complete Managment System</h2>
</div>
<div id="header">
<div id="navcontainer">
<ul id="menu">
	<li><a href="<%=request.getContextPath()%>/jsp/Home.jsp" id="current">Home</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/FAQ.jsp">FAQ</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/ContactUs.jsp">Contact Us</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/aboutus.jsp">About Us</a></li>
	<li><a href="#">Feedback</a></li>
	
	<li style="float: right;"><a href="#">Login</a></li>
	
</ul>


</div>
</div>

<div id="rightnav">
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
</div>
<div id="content">

The Shireburn Inventory Management System (SIMS) delivers a comprehensive range
 of features and benefits for companies large and small to allow them to maximise 
 their inventory operations. Unmatched functionality, ease of use by way of the 
 tabbed user interface and fully customisable, SIMS enables maximum benefit of 
 company systems, while offering flexibility for the inevitable changes in such 
 a fast paced environment.

SIMS is a modular solution allowing organisations to implement just the modules they
 require for their business need, to manage: Stock Control, Invoicing/Stock Issues, 
 POS , Sales Order processing, Purchase Order Processing, Receipts & Costs, Work-in-Progress, 
 Parts Explosion/Bills of material and Stock Take.

</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>

</body>
</html>