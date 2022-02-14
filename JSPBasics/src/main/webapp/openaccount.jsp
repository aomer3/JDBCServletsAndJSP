<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%!Connection con;
	PreparedStatement ps;

	public void jspInit() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "GoReadMe2021!");
			ps = con.prepareStatement("insert into account value(?,?,?,?)");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void jspDestroy() {
		try {
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}%>


<!-- Whatever code is put into the below scriptlet is placed inside the JSP service method -->
<%
int accno = Integer.parseInt(request.getParameter("accno"));
String lastName = request.getParameter("lastname");
String firstName = request.getParameter("firstname");
int bal = Integer.parseInt(request.getParameter("bal"));

ps.setInt(1, accno);
ps.setString(2, lastName);
ps.setString(3, firstName);
ps.setInt(4, bal);

ps.executeUpdate();
%>

<!--  Returns back to html page once data has been added to the database -->
<%@ include file="openaccount.html" %>