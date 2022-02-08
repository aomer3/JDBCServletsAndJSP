package com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
	
	public static void main(String[] args) {
		
		//Create database connection, test connection
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "GoReadMe2021!");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from account");	
			){
			
			System.out.println(connection);
			
			//Inserting a record into the database (use executeUpdate for insert, update, and delete statements)
			//int insertCount = statement.executeUpdate("insert into account values(1, 'smith','jack',10000)");
			//statement.executeUpdate("insert into account values(2, 'williams','philip',15000)");
			//System.out.println(result + " rows were inserted");
			
			//Updating a record into the database 
			//int updateCount = statement.executeUpdate("update account set bal=5000 where accno=1");
			//System.out.println(result + " rows were updated");
			
			//Deleting record from database
			//int deleteCount = statement.executeUpdate("delete from account where accno=1");
			//System.out.println(result + " rows were deleted");
		
		
			//Reading from a database (use executeQuery for read statements)
	
			while(result.next()) {
				Integer accNo = result.getInt("accno");
				String lastName = result.getString("lastname");
				String firstName = result.getString("firstname");
				Integer bal = result.getInt("bal");
				
				System.out.println("Account No: " + accNo);
				System.out.println("First Name: " + firstName);
				System.out.println("Last Name: " + lastName);
				System.out.println("Balance: " + bal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}

}
