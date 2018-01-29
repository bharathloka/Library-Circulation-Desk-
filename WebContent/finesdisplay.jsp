<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, controller.finesentries"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
</head>
<body>
<div align="center">
<form name ="Library" action="example" method="get">
<%
 ArrayList<finesentries> finesresult=(ArrayList<finesentries>)request.getAttribute("fines");
if(finesresult.size()>0){
	%>
	<table border=2 cellpadding=2>
	<tr>
	<th>Borrower_id</th>
	<th>Fined Amount</th>
	<th>Paid_Status</th>
	
	
	</tr>
	

	
	<%for(finesentries fine:finesresult){
	
	%>
	<tr>
	<td><input type="text" name="card_id" value="<%=fine.getcard_id() %>"></td>
	<td><%=fine.getfine_amt() %></td>
	<td><%=fine.getpaid() %></td>
	
	</tr>

<%
}
}
%>

</table>

<input type="radio" name="paid" value="yes">paid now
<input type="radio" name="paid" value="no">not paid
 <input type="hidden" name="reference" value="updatefine">
<input type="submit"  value="updatefine">

</form>
</div>
</body>
</html>