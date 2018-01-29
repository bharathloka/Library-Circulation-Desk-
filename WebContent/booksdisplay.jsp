<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, controller.Booksearch"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
</head>
<body>
<form name ="Library" action="LibraryController1" method="post">
<%
 ArrayList<Booksearch> bookList=(ArrayList<Booksearch>)request.getAttribute("Books");
if(bookList.size()>0){
	%>
	<div align="center">
	<table border=2 cellpadding=2>
	<tr>
	<th>Book Name</th>
	<th>ISBN</th>
	<th>Authors</th>
	<th>Availability</th>
	
	</tr>
	

	
	<%for(Booksearch book:bookList){
	//request.setAttribute("project",project);
	%>
	<tr>
	<td><%=book.getTitle() %></td>
	<td><%=book.getIsbn() %></td>
	<td><%=book.getAuthor() %></td>
	<td><%=book.getavailability() %></td>
	</tr>

<%
}
}
%>
</table>
</div>
</form>
</body>
</html>