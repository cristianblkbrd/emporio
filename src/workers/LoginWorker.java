package workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class LoginWorker extends SwingWorker<Boolean, String> {
	
	private final String user;
	private final String pass;
	private final Connection conn;
	private String tipo;
	private String usuario;
	
	
	
	public LoginWorker(Connection conn, String user, String pass){
		this.user = user;
		this.pass = pass;
		this.conn = conn;
		tipo = "";
		usuario = "";
		
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		
		String query = "SELECT * FROM usuarios WHERE id_usuario=? AND clave=?;";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user);
			pst.setString(2, pass);

			ResultSet rs = pst.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				pst.close();
				return false;
				
			} else{
				usuario = rs.getString("id_usuario");
				tipo = rs.getString("tipo");
				pst.close();
				return true;
			}
			


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String getTipo(){
		return tipo;
		
	}
	
	public String getUsuario(){
		return usuario;
	}

}
