package com.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorldServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		//Step 1: Tell the browser what type of content to expect back
		response.setContentType("text/html");
		
		//Step 2: get the writer to be able to write a response to the web browser 
		PrintWriter out = response.getWriter();
		
		//Step 3: write to web browser
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlets World</h1>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	

}
