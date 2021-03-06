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
import com.trainings.cnp.dao.ProductDAO;
import com.trainings.cnp.model.Coupon;
import com.trainings.cnp.model.Product;

@WebServlet("/products")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CouponDAO couponDAO = new CouponDAO();
	ProductDAO productDAO = new ProductDAO();

    public ProductController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String couponCode = request.getParameter("couponCode");
		Coupon coupon = couponDAO.findByCode(couponCode);
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		
		if(coupon.getDiscount() != null) {
			product.setPrice(new BigDecimal(price).subtract(coupon.getDiscount()));
		} 
		else {
			product.setPrice(new BigDecimal(price));
		}
		
		productDAO.save(product);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Product Created!!</b>");
		out.print("<br/> <a href='/JDBC_CouponAndProductApp'>Home</a>");
	}

}
