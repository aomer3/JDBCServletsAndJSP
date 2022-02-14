<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body>

	<jsp:useBean id="product" class="com.trainings.jsp.Product">
		<!-- the property="*", is a powerful too, that will set all bind all fields of 
	product to product object using Java Reflections, instead of binding each field
	one field at a time. For reflections to work, the variables in the Product java
	class need to be identical to the input names in the productDetails html file -->
		<jsp:setProperty name="product" property="*" />

	</jsp:useBean>

	Product Details
	<br /> Id:
	<jsp:getProperty property="id" name="product" /> <br/>
	Name:
	<jsp:getProperty property="name" name="product" /> <br/>
	Description:
	<jsp:getProperty property="description" name="product" /> <br/>
	Price:
	<jsp:getProperty property="price" name="product" /> 

</body>
</html>