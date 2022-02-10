package com.trainings.interservletcommunication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "GoReadMe2021!");
			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("select * from user where email='" + userName + "' and password='" + password + "' ");
			
			//In the successful case that an email and password combination is entered by user and retrieved from the 
			//database, the loginServlet will forward the user request and the database response to the homeServlet 
			//And if bad credentials are entered, the program will take you back to the login.html page 
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("homeServlet");
			
			if(result.next()) {
				request.setAttribute("message", "Welcome to Interservlet Communication " + userName);
				requestDispatcher.forward(request, response);
			} 
			else {
				 requestDispatcher = request.getRequestDispatcher("login.html");
				 requestDispatcher.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
