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
<title>Sim Inventory Management System</title>
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
<img src="<%=request.getContextPath() %>/images/Sim logo.JPG" height=128 width=125 align=left style="position:relative; top: 1px"></img>
<h1>SIM INVENTORY SYSTEM</h1>
<h2>A Complete Managment System</h2>
</div>
<div id="header">
<div id="navcontainer">
<ul id="menu">
	<li><a href="<%=request.getContextPath()%>/jsp/Home.jsp" id="current">Home</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/FAQ.jsp">FAQ</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/ContactUs.jsp">Contact Us</a></li>
	<li><a href="<%=request.getContextPath()%>/jsp/AboutUs.jsp">About Us</a></li>
	<li><a href="#">Feedback</a></li>
	<li style="margin-left: 320px"><a href="<%=request.getContextPath()%>/jsp/Login.jsp">Login</a></li>
	
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
	
	</div>
</ul>

</div>
<div id="rightnav">
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
</div>
<div id="content">
Contact Us
</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>

</body>
</html>