package com.trainings.servlets.sm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SourceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	//SourceServlet retrieves username from request, sets the username into the session, then 
	//creates a hyperlink that will pass the username to the targetServlet
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//Retreiving cookies used during this session
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName());
				System.out.println(cookies[i].getValue());
			}
		}
		//Rarely do we create cookies manually, but as an example, you would do it like this
		response.addCookie(new Cookie("securityToken","12345"));
		
		
		String userName = request.getParameter("userName");
		HttpSession session = request.getSession();
		session.setAttribute("user", userName);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//URL Rewriting 
		String url = "targetServlet?sessionId=123";
		out.print("<a href='"+url+"'>Click here to get the username </a>");
		
	}

}
