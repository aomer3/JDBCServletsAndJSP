package com.trainings.cnp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trainings.cnp.model.Coupon;
import com.trainings.cnp.util.ConnectionUtil;

public class CouponDAO {
	
	//After user creates a coupon, this method will save the coupon into the database
	public void save(Coupon coupon) {
		Connection connection = ConnectionUtil.getConnection();
		System.out.println(connection);
		
		try {
			
			PreparedStatement statement = connection.prepareStatement("insert into coupon (code,discount, exp_date) values(?,?,?)");
			statement.setString(1, coupon.getCode());
			statement.setBigDecimal(2, coupon.getDiscount());
			statement.setString(3,coupon.getExpDate());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Given a code, this method will find and retrieve the coupon in the database 
	public Coupon findByCode(String code) {
		Coupon coupon = new Coupon();
		
		Connection connection = ConnectionUtil.getConnection();
		
		try {
			
			PreparedStatement statement = connection.prepareStatement("select * from coupon where code=?");
			statement.setString(1, code);
			
			ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next()) {
				coupon.setId(resultSet.getInt(1));
				coupon.setCode(resultSet.getString(2));
				coupon.setDiscount(resultSet.getBigDecimal(3));
				coupon.setExpDate(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return coupon;
		
	}

}
