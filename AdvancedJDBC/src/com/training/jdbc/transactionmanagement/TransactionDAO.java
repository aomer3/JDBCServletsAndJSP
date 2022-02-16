package com.training.jdbc.transactionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAO {
	static Connection con;
	static Statement stmt;

	
public static void main(String[] args) {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root","GoReadMe2021!");
			stmt = con.createStatement();
			
			//Transaction Management 
			con.setAutoCommit(false);
			
			stmt.executeUpdate("update account set bal=bal-500 where accno=2");
			stmt.executeUpdate("update account set bal=bal+500 where accno=1");
			//either commit both above statements or if there is some exception with either, 
			//commit neither statement
			con.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close(); //close the connection and rollback if there is an exception with either DML statement above
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
	
		
		
		}
}
