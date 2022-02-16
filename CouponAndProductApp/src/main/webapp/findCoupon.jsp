<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Coupon</title>
</head>
<body>

<h1>Find a Coupon</h1>

<!--  form action is mapped to the /coupons controller -->
<form action="coupons" method="post">

<!-- <pre> tag is preformatted text that will preserve spaces and line breaks -->
<pre>

Coupon Code: <input type="text" name="couponCode"/>
<input type="hidden" name="action" value="find"/>
<input type="submit" value="Save"/>

</pre>
</form>

</body>
</html>