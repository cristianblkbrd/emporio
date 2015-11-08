import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class NotificationTask extends TimerTask {
	
	private Connection connection;
	private String result="";
	
	private char delimiter=(char)44;
	
	public NotificationTask(Connection conn){
		connection = conn;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String query = "select * from entradas;";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(query);
	
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData md=rs.getMetaData();
			int numColumns =md.getColumnCount();
			// Fetch column names
			for (int i=1; i<numColumns+1; i++) {
			 String columnName = md.getColumnName(i)+ delimiter;
			 result+=columnName;
			 }
			 
			result=result.substring(0,result.length()-1);
			result+=(char)13;
			 
			int times=result.length();
			 
			for (int i=1; i<times ;i++){
			 result+="-";
			 }
			 
			result+=(char)13;
			 
			// Fetching Rows
			 
			result=result.substring(0,result.length()-1);
			result+=(char)13;
			 
			while (rs.next()) {
			 for (int i=1; i<numColumns+1; i++) {
			 String  output=rs.getString(md.getColumnName(i))+ delimiter;
			 result+=output;
			 }
			result=result.substring(0,result.length()-1);   
			result+=(char)13;
			 }
			
			
			SendEmail sendN = new SendEmail();
			//sendN.setResult(result);
			//sendN.sendNotification();
			//sendN.Send("cristianmmerlo", "m1123581321", "cristian.merlo@globant.com", "Notification", result);
			sendN.sendEmail(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
