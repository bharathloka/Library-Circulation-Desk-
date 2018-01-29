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
String error1=(String)request.getAttribute("Error1");
String error2=(String)request.getAttribute("Error2");
String error3=(String)request.getAttribute("Error3");
if(error1 != null){%>
	<%=error1%>
<%}
else if(error2 !=null){%>
	<%=error2%>
	
<%}
else if(error3 !=null){%>
<%=error3%>
<%} 
  else{%>
  <h1>Book checked out</h1>
  <%
  }%>
</body>
</html>