package com.ideas2it.sessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DataBaseConnection 
 * 
 * @author Riyas
 * @version  2.0 15-03-2021
 */
public class DataBaseConnection {
   static Connection connection = null;
   
   /**
    * Accessing Connection by DAO 
    * 
    * @return connection
    */
   public static Connection getConnection() {

      if(connection != null) { 
          return connection;
      }
      String database = "EmployeeDetails";
      String Username = "root";
      String password = "riyas";
      return getConnection(database, Username, password);
   }
   
   /**
    * Establishing New Connection
    * 
    * @param database
    * @param UserName
    * @param password
    * 
    * @return connection
    */
   private static Connection getConnection(String databaseName, String UserName, String password) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDetails", "root", "riyas");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return connection;
   }
}