import java.sql.*;
import javax.swing.*;
public class SqlConnection {
	

	
	static final String JDBC_DRIVER = "org.sqlite.JDBC";  
	   static final String DB_URL = "jdbc:sqlite:/Users/cristianmerlo/zamolo/database.db";
	  // static final String DB_URL = "jdbc:sqlite:./database.db";
	   
	//  Database credentials
	   //static final String USER = "username";
	   //static final String PASS = "password";
	
	public static Connection dbConnect(){
		Connection conn = null;
		//Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL);
		      JOptionPane.showMessageDialog(null, "Conexion exitosa con base de datos");

		      return conn;
		      //STEP 4: Execute a query
		     /* 
		      String sql;
		      sql = "SELECT id, first, last, age FROM Employees";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         int age = rs.getInt("age");
		         String first = rs.getString("first");
		         String last = rs.getString("last");

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Age: " + age);
		         System.out.print(", First: " + first);
		         System.out.println(", Last: " + last);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();*/
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      return null;
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      return null;
		   }/*finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		*/
	}

}
