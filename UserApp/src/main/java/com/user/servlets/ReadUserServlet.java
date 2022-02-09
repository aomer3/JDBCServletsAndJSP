package com.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */

//1. The hardcoded database connection properties (url, username, password) can be configured in web.xml and passed in
//2. Configuring database connection properties in the web.xml is an alternative to using annotations to do the same thing
//3. No need to add @WebServlet annotation because the url path has already been mapped in the web.xml
//@WebServlet("/readServlet")
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private ResultSet result;
       
	public void init(ServletConfig config) {
		
		try {
			ServletContext context = config.getServletContext();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(context.getInitParameter("dbUrl"),context.getInitParameter("dbUser") ,context.getInitParameter("dbPassword"));
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("select * from user");
			
			//Create static part of website (building the table)
			PrintWriter out = response.getWriter();
			out.print("<table>");
			//Header row and header titles
			out.print("<tr>");
			out.print("<th>");
			out.println("First Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Last Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Email");
			out.print("</th>");
			
			//Create dynamic part of website (getting info from database to popular table)
			while(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String email = result.getString("email");
				String password = result.getString("password");
			
				out.print("<tr>");
				
				out.print("<td>");
				out.print(firstName);
				out.print("</td>");
				
				out.print("<td>");
				out.print(lastName);
				out.print("</td>");
				
				out.print("<td>");
				out.print(email);
				out.print("</td>");
				
				out.print("</tr>");

			}
			out.print("</tr>");
			out.print("</table>");
			
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			connection.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
