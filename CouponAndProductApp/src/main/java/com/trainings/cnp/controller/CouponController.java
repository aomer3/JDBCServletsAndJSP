package com.trainings.cnp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trainings.cnp.dao.CouponDAO;
import com.trainings.cnp.model.Coupon;

/**
  * Servlet implementation class CouponController
 */
@WebServlet("/coupons")
public class CouponController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponDAO dao = new CouponDAO();

	public CouponController() {
		super();
	}

	//The same doPost method will handle both 1. create coupon and 2. find coupon
	//This method will determine which method to select (create or find) based on 
	//a hidden html field (action) in the create and find coupon JSPs
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if(action.equals("create")) {
			createCoupon(request, response);
		} 
		else if (action.equals("find")){
			findCoupon(request, response);
		}
	}

	private void createCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Grab user input from view to create a coupon and create a Coupon object to
		// hold them
		// The variable names in getParamter must match the variable names in the html
		// form
		String couponCode = request.getParameter("couponCode");
		String discount = request.getParameter("discount");
		String expirationDate = request.getParameter("expirationDate");

		Coupon coupon = new Coupon();
		coupon.setCode(couponCode);
		coupon.setDiscount(new BigDecimal(discount)); // BigDecimal constructor will convert String to BigDecimal
		coupon.setExpDate(expirationDate);
		
		//Save the coupon to the database
		dao.save(coupon);
		
		//Send a response back to the user 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Coupon Created!!</b>");
		//in the href, using the project name is the same as using index.html to go back to the Home page
		out.print("<br/> <a href='/CouponAndProductApp'>Home</a>");
	}
	
	
	
	//This method will find a coupon in the database after receiving a coupon code 
	private void findCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String couponCode = request.getParameter("couponCode");
		Coupon coupon = dao.findByCode(couponCode);
		
		//Send a response back to the user 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Coupon Details:</b>");
		out.print(coupon);
		
		//in the href, using the project name is the same as using index.html to go back to the Home page
		out.print("<br/> <a href='/JDBC_CouponAndProductApp'>Home</a>");
	}

}
