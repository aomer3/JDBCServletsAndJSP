<!-- errorPage specifies which JSP should be used to handle errors -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="errorHandler.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Generator</title>
</head>
<body>
	
	<!-- Typecasting a string to an integer will throw an error, so we can use errorHandler page -->
	<%
	String s = "123abc";

	int num = Integer.parseInt(s);
	%>
</body>
</html>