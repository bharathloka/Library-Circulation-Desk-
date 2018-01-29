<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String error=(String)request.getAttribute("Error");
if(error != null){%>
	<%=error%>
<%}
else{%>
	<h1>Borrower successfully added</h1>
	
<%}
%>
	
	
</body>
</html>