<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script  type="text/javascript">
<jsp:include page="/js/check_1.js" />
</script>
<title>Bill Payment</title>
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
	<li style="float: right;"><a href="#">Logout</a></li>
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
<form name="payBill" action="<%=request.getContextPath() %>/BillProcessorController" method="post" onSubmit="return Valid();">
<%BillBean billObj=new BillBean();
billObj=(BillBean)session.getAttribute("billDetail");
System.out.println(	billObj);%>
<h1>Bill Payment form</h1>
<table>
<tr>
	<td>Bill NO.:</td>
	<td><%=billObj.getBillNo()%></td>
</tr>
<tr>
	<td>Bill Date:<font size="1">(YYYY-MM-DD)</font></td>
	<td><%=billObj.getBillDate().substring(0,10)%></td>
</tr>
<tr>
	<td>Bill Amount:</td>
	<td>Rs.<%=billObj.getBillAmount()%></td>
</tr>
<tr>
	<td>Payment Mode:</td>
	<td><input type="radio" name="mode" value="Credit Card" checked="checked"/>Credit Card<input type="radio" name="mode"  value="Debit Card"/>Debit Card</td>
</tr>
<tr>
	<td>Card Type:</td>
	<td><input type="radio" name="cardType" checked="checked"/>Maestro<input type="radio" name="cardType"/>Visa<input type="radio" name="cardType"/>Mastercard<br></td>
</tr>
<tr>
	<td>Name On Card:<font color="bb0000">*</font></td>
	<td><input type="text" name="cardOwner" onblur="cardOwnerName()"/></td>
	<td id="1" style="color: red">
	</td>
</tr>
<tr>
	<td>Card Number:<font color="bb0000">*</font></td>
	<td><input type="text" name="cardNo" onblur="cardNo()"/></td>
	<td id="2" style="color: red">
	</td>
</tr>
<tr>
	<td>Card Expiry Date:<font color="bb0000">*</font></td>
	<td><SELECT NAME="month" onblur="dateExpiry()">
    		<OPTION VALUE="no" SELECTED>-MM-
   			<%for(int i=1;i<=12;i++){ %>
    		<OPTION><%=i %>
   			<%} %>
		</SELECT>
		<SELECT NAME="year" onblur="dateExpiry()">
    		<OPTION VALUE="no" SELECTED>-YY-
   			<%for(int i=13;i<=52;i++){ %>
    		<OPTION><%=i %>
   			<%} %>
		</SELECT>
	</td>
	<td id="3" style="color: red">
	</td>
</tr>
<tr>
	<td>3 Digit CVV Number:<font color="bb0000">*</font></td>
	<td><input type="password" name="cvvNo" onblur="cvvNo()"/></td>
	<td id="4" style="color: red">
	</td>
</tr>
</table><br>
<input type="submit" name="option" value="Submit" />
<% session.setAttribute("billDetail",billObj);%>
</form>
<font size="3"> Fields marked with asterisk(<font color="bb0000">*</font>)
are mandatory</font>
</div>
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
