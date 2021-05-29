package utility;

import java.sql.Connection;
import java.sql.DriverManager;

import exception.ConnectionUtilityException;



public class JDBCUtility{
private static String URL="jdbc:mysql://localhost:3306/BookAuthor";
private static String USERNAME="root";
private static String PASSWORD="hitperson@123";

  public static Connection getConnection()throws ConnectionUtilityException{
	  Connection con=null;
	  try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
	  }
	  catch (Exception e) {
	throw new ConnectionUtilityException("Connectivity fail....");
	}
	return con;
  }

}
