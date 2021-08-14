package ConnectionManager;


import java.sql.*;



public class ConnectionManager {

   static Connection con;
   static String url;
         
   public static Connection getConnection()
   {
     
      try
      {
         String url = "jdbc:postgresql://database-2.c23jiwlalyxf.us-east-2.rds.amazonaws.com:5432/aws"; 
         // assuming "DataSource" is your DataSource name

         Class.forName("org.postgresql.Driver");
         
         try
         {            	
            con = DriverManager.getConnection(url,"postgres","pizza1234"); 
             								
         // assuming your SQL Server's	username is "username"               
         // and password is "password"
              
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}
