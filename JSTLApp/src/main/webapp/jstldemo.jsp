<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>C:out Demo</title>
</head>
<body>

<!--  Using core tags -->

Sum of 10 and 9: <c:out value="${10+9}"/> <br/>
<c:set var="testScore" value="${85}" scope="session"/>
Test score is: <c:out value="${testScore}"/> <br/>

<c:if test="${testScore >= 80}">
	<p>Your score is awesome!</p>
</c:if>

<c:choose>
	<c:when test="${testScore>=90}">
	 	A Grade
	</c:when>
	<c:when test="${testScore>=80 && testScore <90}">
	 	B Grade
	</c:when>
	<c:when test="${testScore>=70 && testScore < 80}">
	 	C Grade
	</c:when>
	<c:when test="${testScore>=60 && testScore < 70}">
	 	D Grade
	</c:when>
	<c:otherwise>
		F Grade
	</c:otherwise>
</c:choose>
<br/>

<c:out value= "For loop (standard): "/>
<c:forEach var="i" begin="1" end="3">
	<c:out value="${i}"/>
</c:forEach>
<br/>

<%
List<String> studentNames = new ArrayList<>();
studentNames.add("John");
studentNames.add("Bob");
studentNames.add("Ahmed");
request.setAttribute("studentNames", studentNames);
%>

<c:out value="For Loop (List): "/>
<c:forEach var="studentName" items="${studentNames}">
	<c:out value="${studentName}"/>
</c:forEach>
<br/>

<c:remove var="testScore"/>
After removal, test score is: <c:out value="${testScore}"/>
<br/>

<!-- Formatting -->

<!-- Parsing a string into a number -->
<c:set var="accountBalance" value="123.456"/>
<fmt:parseNumber var="i" type="number" value="${accountBalance}"></fmt:parseNumber>
<p>Amount is: <c:out value="${i}"/></p>

<!-- Formatting a number -->
<c:set var="accountBalance" value="777.4567"/>
<p>Formatted Number 1: <fmt:formatNumber value="${accountBalance}" type="currency"></fmt:formatNumber></p>
<p>Formatted Number 2: <fmt:formatNumber value="${accountBalance}" type="number" maxFractionDigits="2"></fmt:formatNumber></p>
<p>Formatted Number 3: <fmt:formatNumber value="${accountBalance}" type="percent" maxFractionDigits="2"></fmt:formatNumber></p>
<p>Formatted Number 4: <fmt:formatNumber value="${accountBalance}" type="number" pattern="####.##$"></fmt:formatNumber></p>

<!-- Parsing a date -->
<c:set var="myDate" value="12-07-2019"/>
<fmt:parseDate var="parsedDate" value="${myDate}" pattern="MM-dd-yyyy"></fmt:parseDate>
<p>Parsed Date: <c:out value="${parsedDate}"/> </p>
</body>
</html>