package com.training.jdbc.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class BatchDAO {

	public static void main(String[] args) {
		
	try(
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root","GoReadMe2021!");
		Statement stmt = con.createStatement();
		){
	
		stmt.addBatch("insert into account values(1,'Biden','Joe',200000)");
		stmt.addBatch("insert into account values(2,'Clinton','Hillary',400000)");
		
		int[] result = stmt.executeBatch();
		
		for(int i=0; i < result.length;i++) {
			System.out.println(result[i]);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	
	
	}

}
