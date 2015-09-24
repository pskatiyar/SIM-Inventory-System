<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tag Customer</title>
</head>
<body>
<form name="form1" action="<%=request.getContextPath()%>/BillProcessorController" method="post" onsubmit="return validate()">
<table>
<tr></tr>
<tr>
<td>Service Provider Id:</td>
<td><input type="text" name="serviceproviderid"></input></td>
</tr>
<tr>
<td></td>
<td><input type="submit" name="option" value="Show Customer List"></input></td>
</tr>
</table>
</form>

</body>
</html>