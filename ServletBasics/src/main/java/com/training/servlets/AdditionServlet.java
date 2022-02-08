package com.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		//Getting form data from numbers.html, number1 and number2 are the form input names
		//Check if input is null, then parse from String to correct data type if needed
		String requestNumber1 = request.getParameter("number1");
		String requestNumber2 = request.getParameter("number2");
		
		if((requestNumber1 != null) && (requestNumber2 != null)) {
			Long num1 = Long.parseLong(requestNumber1);
			Long num2 = Long.parseLong(requestNumber2);
			
			PrintWriter out = response.getWriter();
			out.write("The result is: " + (num1 + num2));
		}
			
	}

}
