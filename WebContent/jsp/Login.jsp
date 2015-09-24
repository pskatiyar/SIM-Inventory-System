<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<!--<link rel="stylesheet" href="/Three_Column_Fluid/jquery.ui.all.css">-->
<script src="<%=request.getContextPath() %>/css/jquery-1.5.1.js"></script>
<script src="<%=request.getContextPath() %>/css/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath() %>/css/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath() %>/css/jquery.ui.accordion.js"></script>
<!--<link rel="stylesheet" href="/Three_Column_Fluid/demos.css">-->
<title>SIM INVENTORY SYSTEM</title>
<style type="text/css" title="layout" media="screen">
@import url("<%=request.getContextPath() %>/css/style.css");
</style>
<script  type="text/javascript">
<jsp:include page="../js/logincheck.js" />
</script>
<script >
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
	<li><a href="http://localhost:8080/SIMInventroyManagementSystem/Login.jsp" id="current">Home</a></li>
	<li><a href="http://localhost:8080/SIMInventroyManagementSystem/FAQ.jsp">FAQ</a></li>
	<li><a href="http://localhost:8080/SIMInventroyManagementSystem/Contact_Us.jsp">Contact Us</a></li>
	<li><a href="http://localhost:8080/SIMInventroyManagementSystem/About_Us.jsp">About Us</a></li>
	<li><a href="http://localhost:8080/SIMInventroyManagementSystem/Feedack.jsp">FeedBack</a></li>
</ul>
</div>
</div>
<div id="leftnav">
<ul>
	</ul>

</div>
<div id="rightnav">
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
<div class="product_wrapper"></div>
</div>

<div id="content">
<h2>User Login</h2>
<form name=myform action="<%=request.getContextPath() %>/Login" method="post" onsubmit="return validate();" onreset="return validreset();">
<table border="0" align="center" height="100" style='table-layout:fixed'>
<tr>
<th>UserId:</th>
<td><input type=text name="un" maxlength="6" onblur="return validate()"></td>
<td id="erruserid" width=200></td>
</tr>
<tr>
<th>Password</th>
<td><input type=password name="pw" onkeypress="capLock(event)" onblur="return validate()" ></td>
<td id="errpassword" width=200></td>
</tr>
</table>
<table align="center">
<tr>

<th><input type="submit" value=Login name=option></input></th>
<th><input type="reset"  name=Reset></th>
<input type="hidden" name="action" value="login">
</tr>
</table>
<!--<a href="ServiceProviderRegister.html">if you are not register click here</a>
-->

<% String msg = (String) session.getAttribute("error");
if (msg != null) {
    %><p style="color:red"><%= msg %></p><%
} session.removeAttribute("error"); %>

</form>


</div>


<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>