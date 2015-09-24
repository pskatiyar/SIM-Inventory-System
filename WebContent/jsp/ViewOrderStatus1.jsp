<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
    <%@page import=" javax.servlet.http.HttpSession"%>
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
<title>Order Status</title>
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
<form action="<%=request.getContextPath() %>/BillProcessorController" method="post">
<%
List<OrderBean> viewOrder=new ArrayList<OrderBean>();
viewOrder=(ArrayList<OrderBean>)session.getAttribute("oList");
int numberoforders=0;
numberoforders=viewOrder.size();
int page1= Integer.parseInt(request.getParameter("page"));
int pagecount;
int from=(page1-1)*10;
int to=page1*10;
if(numberoforders%10==0){
pagecount=numberoforders/10;
}
else{
	pagecount=numberoforders/10+1;
}
if(page1==pagecount){
	to=numberoforders;
}
List <OrderBean> orlist=viewOrder.subList(from , to);
Iterator<OrderBean> it=orlist.iterator();
%>
<table border=1>
<tr><td><b>Select</b></td><td><b>Order Id</b></td><td><b>ServiceProvide ID</b></td><td><b>Order Date(YYYY-MM-DD)</b></td><td><b>Order Status</b></td></tr>

<% while(it.hasNext()){
OrderBean s=it.next();%>
<tr><td><input type="radio" name="view" value="<%=s.getOrderId() %>" checked="checked"/></td>
<td><%=s.getOrderId() %></td>
<td><%=s.getSPID() %></td>
<td><%=s.getOrderDate().substring(0,10) %></td>
<td><%=s.getOrderStatus() %></td>
</tr>
<% }%>

</table>
<%session.setAttribute("viewList",viewOrder) ;%>  
<input type="submit" name="option" value="Order Details" />
<button onclick="window.location.href='<%=request.getContextPath() %>/jsp/DisplayBills.jsp?page=1'">Back</button>
</form>
<%if(page1!=1){ %>
<a href="<%=request.getContextPath()%>/jsp/ViewOrderStatus1.jsp?page=<%=page1-1%>">PREVIOUS</a>
<%}
for(int j=1;j<page1;j++){ %>
<a href="<%=request.getContextPath()%>/jsp/ViewOrderStatus1.jsp?page=<%=j %>"><%=j %></a>

<%}%><%=page1 %>
<% 
for(int i=page1+1;i<=pagecount;i++){ %>
<a href="<%=request.getContextPath()%>/jsp/ViewOrderStatus1.jsp?page=<%=i %>"><%=i %></a>
<%} 
if(page1!=pagecount){
%><a href="<%=request.getContextPath()%>/jsp/ViewOrderStatus1.jsp?page=<%=page1+1 %>">NEXT</a><%} %>
</div> 
<div id="footer">
<p>Copyright &copy; T210<a href="#">Contact</a></p>
</div>
</div>
</body>
</html>
