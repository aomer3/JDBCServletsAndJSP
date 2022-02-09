package com.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */
//Instead of hardcoding properties (like DB connection properties) in the init method, they can configured during the 
//instantiation of the servlet phase, and then be passed into the init method using annotations and ServletConfig
@WebServlet(urlPatterns = "/addServlet"
			//Do not these since db properties config is through web.xml
			//, initParams = {
		    //@WebInitParam(name="dbUrl",value="jdbc:mysql://localhost/mydb"),
		    //@WebInitParam(name="dbUser",value="root"), 
		    //@WebInitParam(name="dbPassword",value="GoReadMe2021!")}
)
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
	public void init(ServletConfig config) {
		
		try {
			ServletContext context = config.getServletContext();
			
			//Listing out context parameter names and values 
			Enumeration<String> parameterNames = context.getInitParameterNames();
			
			while(parameterNames.hasMoreElements()) {
				String eachName = parameterNames.nextElement();
				System.out.println("Paramter Name: " +eachName);
				System.out.println("Paramter value: " + context.getInitParameter(eachName));
			}
			
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into user values('"+ firstName + "','"+ lastName +"','"+ email +"','"+ password +"')");
			
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.print("<h1>User Created</h1>");
			} else {
				out.print("<h1>Error Creating User</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
