<!--  isErrorPage declares this JSP page as the page that will handle errors -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>

	<p>An error has occurred.</p>
	<!-- We usually don't display the exception details to end user but to show how it would be done  -->
	<%=exception.getMessage()%>

</body>
</html>