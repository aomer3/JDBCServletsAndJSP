package com.training.jsp.customtags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


public class ResultHandler extends TagSupport{
	
	Connection con;
	PreparedStatement stmt;
	
	public ResultHandler() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","GoReadMe2021!");
			stmt = con.prepareStatement("select * from user where email=?");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		ServletRequest request = pageContext.getRequest();
		
		String email = request.getParameter("email");
		
		try {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			JspWriter out = pageContext.getOut();
			
			if(rs.next()) {
				out.print("User Details are: <br/>");
				out.print("First Name: " + rs.getString("firstname") + "<br/>");
				out.print("Last Name:" + rs.getString("lastname"));
			}
			else {
				out.print("User not found in the database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_BODY;
	}
	
	@Override
	public void release() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
