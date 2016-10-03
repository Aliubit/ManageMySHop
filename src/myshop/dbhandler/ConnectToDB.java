package myshop.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToDB {

	public static  Connection conn;
	
	public ConnectToDB() {
		// TODO Auto-generated constructor stub
		
	}
	
	public int queryHolder(String query){
		
		  conn = null;
	      try {
	         String userName = "root";
	         String password = null;
	         String url = "jdbc:mysql://localhost/myshop";
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	         conn = DriverManager.getConnection(url, userName, password);
	         Statement stmt = conn.createStatement();
	      //   System.out.println(stmt.toString());
	        int result=  stmt.executeUpdate(query); 					// planed to execute all quries through here and create methods in DBQueries
	        												// class.
	         conn.close();
	         System.out.println("Record added.");
	         return result;
	      }
	      catch (Exception e) {
	    	  System.out.println(e.toString());
	         System.out.print("Error inserting record.");
	         return -1;
	      }
		
	}
	
	
	public ResultSet dbRetrival(String query){
		
		  conn = null;
	      try {
	         String userName = "root";
	         String password = null;
	         String url = "jdbc:mysql://localhost/myshop";
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	         conn = DriverManager.getConnection(url, userName, password);
	         Statement stmt = conn.createStatement();
	       
	        ResultSet rset =  stmt.executeQuery(query); 					// planed to execute all quries through here and create methods in DBQueries
	        												// class.
	       //  conn.close();
	         System.out.println("Record added.");
	        
	         return rset;
	      }
	      catch (Exception e) {
	    	  System.out.println(e.toString());
	         System.out.print("Error inserting record.");
	         return null;
	      }
		
	}
	
	
}
