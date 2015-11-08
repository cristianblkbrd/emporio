import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultCaret;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;


import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;
import java.awt.Button;

public class EmporioApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmporioApp window = new EmporioApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */

	private static Connection connection = null;

	private String usuario;
	private String tipoUsuario;
	private JTable tablaMovTubos;
	private static JTextField textFieldNroTubo;
	private static JTextField textFieldGas;
	private static JTextField textFieldTamano;
	private static JTextField textFieldPropietario;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnAgregarTubo;
	private JButton btnActualizarTubo;
	private JInternalFrame internalFrameNuevoTubo;
	private JButton btnCancelar;
	private JInternalFrame internalFrameInfoTubo;
	private JMenu mnMovimientos;
	private JMenuItem mntmNuevaEntrada;
	private JMenuItem mntmNuevaSalida;
	private JTextField textFieldEntradaFecha;
	private JTextField textFieldEntradaNroTubo;
	private JTextField textFieldEntradaCliente;
	private JTextField textFieldEntradaComprobante;
	private JInternalFrame InternalFrameNuevaEntrada;
	private JTextField textFieldEmpleado;
	private JTextField textFieldMonto;
	private JComboBox comboBoxMotivo;
	private JComboBox comboBoxFlete;
	private JTextArea textAreaObservaciones;
	private JTextField textFieldBuscarNroTubo;
	private JTextField textFieldBuscarGas;
	private JTextField textFieldBuscarTamano;
	private JTextField textFieldBuscarPropietario;

	private JInternalFrame internalFrameNuevaSalida;
	private JTextField textFieldSalidaNroTubo;
	private JTextField textFieldSalidaCliente;
	private JTextField textFieldSalidaComprobante;
	private JTextField textFieldSalidaFecha;
	private JTextField textFieldSalidaEmpleado;
	private JTextField textFieldSalidaMonto;
	private JTextField textFieldSalidaDevolucion;
	private JComboBox comboBoxSalidaFlete;
	private JTextPane textPaneSalidaObservaciones;
	private JTextField textFieldAcondTubo;
	private JTextField textFieldAcondFecha;
	private JInternalFrame internalFrameNuevoAcond;
	private JTextField textFieldUbicacion;

	private JComboBox comboBoxAcond;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldContrasena;

	private JInternalFrame internalFrameLogin;

	private JInternalFrame internalFrameClientes;
	private JTextField textFieldClientesNombre;

	private JTextField textFieldClientesCuenta;
	private JTextField textFieldClientesDir;
	private JTextField textFieldClientesCod;
	private JComboBox comboBoxUbicacion;
	private JTextField textFieldTelefono;
	private JComboBox comboBoxGrupo;

	private JMenu mnClientes;
	private JMenu mnTubos;
	private JMenu mnUsuarios;
	private JMenu mnNotificaciones;

	JMenuBar menuBar;
	private JMenuItem mntmCambiarContrasea;
	private JMenuItem mntmVerMovimientos;
	private JButton btnBuscar;
	private JLabel lblUbicacin;
	private JTextField textFieldBuscarUbicacion;
	private JLabel lblEnStock;
	private JTextField textFieldBuscarEnStock;
	private JLabel lblLleno;
	private JTextField textFieldBuscarLleno;
	private JLabel lblMovimientos;
	private JLabel lblAcondicionamientos;
	private JTable tableAcond;
	private JTable tableListaTubos;
	private JScrollPane scrollPane_1;
	private JTextField textFieldListarTamano;
	private JTextField textFieldListarGas;
	private JCheckBox chckbxListarEnStock;
	
	private JInternalFrame internalFrameListarTubos;

	public EmporioApp() {
		initialize();

	}

	private void runNotification(){
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 49);
		today.set(Calendar.SECOND, 0);

		// every night at 2am you run your task
		Timer timer = new Timer();
		timer.schedule(new NotificationTask(connection), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
	}


	private void clearDataNuevoTubo(){
		textFieldNroTubo.setText("");
		textFieldGas.setText("");
		textFieldTamano.setText("");
		textFieldPropietario.setText("");

	}

	private void clearDataNuevEntrada(){
		textFieldEntradaFecha.setText("");
		textFieldEntradaComprobante.setText("");
		textFieldEmpleado.setText("");
		textFieldMonto.setText("");
		textAreaObservaciones.setText("");
		textFieldEntradaNroTubo.setText("");
		textFieldEntradaCliente.setText("");

	}

	private void updateTableEntradas(){
		try {
			String query = "select * from entradas;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				tablaMovTubos.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void updateTableTubos(){
		try {
			String query = "select * from tubos;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tablaMovTubos.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	private void fillComboBoxTubos(){

		
	}

	private boolean isTuboAdded(String tubo){
		boolean isThere = false;
		try {
			String query = "SELECT * FROM tubos;";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next() && !isThere)
				if (rs.getString(1).equals(tubo))
					isThere = true;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isThere;
	}

	private boolean isClienteInDB(String cliente){
		boolean isThere = false;
		String cliente1 = cliente.toLowerCase();
		try {
			String query = "SELECT * FROM clientes;";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next() && !isThere)
				if (rs.getString(2).toLowerCase().equals(cliente1))
					isThere = true;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isThere;
	}

	private int getRow(String table){
		String query = "select * from " + table + ";";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next())
				i++;
			//rs.last();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = SqlConnection.dbConnect();

		runNotification();

		frame = new JFrame();
		frame.setBounds(100, 100, 785, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);



		frame.setVisible(true);


		//------------------------------------Menu items------------------------------------

		menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		frame.setJMenuBar(menuBar);

		mnTubos = new JMenu("Tubos");
		mnTubos.setEnabled(false);
		menuBar.add(mnTubos);

		JMenuItem mntmBuscar = new JMenuItem("Ver información de tubo");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//updateTableEntradas();
				internalFrameInfoTubo.setVisible(true);
				internalFrameInfoTubo.setFocusable(true);
			}
		});

		JMenuItem mntmNuevo = new JMenuItem("Nuevo/Modificar tubo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameInfoTubo.setFocusable(false);
				internalFrameNuevoTubo.setVisible(true);
				internalFrameNuevoTubo.setFocusable(true);
				try {
					internalFrameNuevoTubo.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnTubos.add(mntmNuevo);

		mnMovimientos = new JMenu("Movimientos");
		mnTubos.add(mnMovimientos);

		mntmNuevaEntrada = new JMenuItem("Nueva entrada");
		mntmNuevaEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternalFrameNuevaEntrada.setVisible(true);
			}
		});
		mnMovimientos.add(mntmNuevaEntrada);

		mntmNuevaSalida = new JMenuItem("Nueva salida");
		mntmNuevaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameNuevaSalida.setVisible(true);
			}
		});
		mnMovimientos.add(mntmNuevaSalida);

		mntmVerMovimientos = new JMenuItem("Ver movimientos");
		mnMovimientos.add(mntmVerMovimientos);

		JMenuItem mntmNuevoAcondicionamiento = new JMenuItem("Nuevo acondicionamiento");
		mntmNuevoAcondicionamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameNuevoAcond.setVisible(true);
			}
		});
		mnTubos.add(mntmNuevoAcondicionamiento);
				
				JSeparator separator = new JSeparator();
				mnTubos.add(separator);
		
				JMenuItem mntmListar = new JMenuItem("Ver tubos");
				mntmListar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						internalFrameListarTubos.setVisible(true);
					}
				});
				mnTubos.add(mntmListar);
		mnTubos.add(mntmBuscar);

		mnClientes = new JMenu("Clientes");
		mnClientes.setEnabled(false);
		menuBar.add(mnClientes);

		JMenuItem mntmNuevo_1 = new JMenuItem("Nuevo/Modificar");
		mntmNuevo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameClientes.setVisible(true);
			}
		});
		mnClientes.add(mntmNuevo_1);
				
				JSeparator separator_1 = new JSeparator();
				mnClientes.add(separator_1);
		
				JMenuItem mntmBuscar_1 = new JMenuItem("Buscar clientes");
				mnClientes.add(mntmBuscar_1);

		mnNotificaciones = new JMenu("Notificaciones");
		mnNotificaciones.setEnabled(false);
		menuBar.add(mnNotificaciones);

		JMenuItem mntmDeudores = new JMenuItem("Deudores");
		mnNotificaciones.add(mntmDeudores);

		JMenuItem mntmRotacin = new JMenuItem("Rotación");
		mnNotificaciones.add(mntmRotacin);

		JMenuItem mntmPruebaHidraulica = new JMenuItem("Prueba hidraulica");
		mnNotificaciones.add(mntmPruebaHidraulica);

		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setEnabled(false);
		menuBar.add(mnUsuarios);

		JMenuItem mntmCrearmodificar = new JMenuItem("Crear/modificar");
		mntmCrearmodificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipoUsuario.equals("admin")){
					JOptionPane.showMessageDialog(null, "OK!");
				}
				else JOptionPane.showMessageDialog(null, "El usuario no posee permisos para realizar esta operación");
			}
		});
		mnUsuarios.add(mntmCrearmodificar);

		mntmCambiarContrasea = new JMenuItem("Cambiar Contraseña");
		mnUsuarios.add(mntmCambiarContrasea);


		//----------------------------internal frames definitions--------------------------------------

		internalFrameListarTubos = new JInternalFrame("Buscar tubos");
		internalFrameListarTubos.setBounds(6, 10, 600, 600);
		frame.getContentPane().add(internalFrameListarTubos);
		internalFrameListarTubos.getContentPane().setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 216, 564, 311);
		internalFrameListarTubos.getContentPane().add(scrollPane_1);
		
		
		String[] columnNames = {"Nro tubo", "Gas", "Tamaño", "Propietario", "Lleno", "Ubicación"};
        
		tableListaTubos = new JTable();
		scrollPane_1.setViewportView(tableListaTubos);
		
		chckbxListarEnStock = new JCheckBox("En stock");
		chckbxListarEnStock.setBounds(25, 21, 128, 23);
		internalFrameListarTubos.getContentPane().add(chckbxListarEnStock);
		
		JComboBox comboBoxListarEstado = new JComboBox();
		comboBoxListarEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Llenos", "A llenar"}));
		comboBoxListarEstado.setBounds(23, 75, 143, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarEstado);
		
		JCheckBox chckbxListarPruebaHidrulica = new JCheckBox("Prueba hidráulica");
		chckbxListarPruebaHidrulica.setBounds(198, 21, 176, 23);
		internalFrameListarTubos.getContentPane().add(chckbxListarPruebaHidrulica);
		
		JComboBox comboBoxListarPropietario = new JComboBox();
		comboBoxListarPropietario.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Z", "P"}));
		comboBoxListarPropietario.setBounds(198, 75, 135, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarPropietario);
		
		JComboBox comboBoxListarUbicacion = new JComboBox();
		comboBoxListarUbicacion.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Tandil", "Olavarría", "Colectivo"}));
		comboBoxListarUbicacion.setBounds(368, 75, 160, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarUbicacion);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(25, 56, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblEstado);
		
		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(198, 56, 84, 16);
		internalFrameListarTubos.getContentPane().add(lblPropietario);
		
		JLabel lblUbicacin_1 = new JLabel("Ubicación");
		lblUbicacin_1.setBounds(368, 56, 109, 16);
		internalFrameListarTubos.getContentPane().add(lblUbicacin_1);
		
		textFieldListarTamano = new JTextField();
		textFieldListarTamano.setBounds(102, 114, 130, 26);
		internalFrameListarTubos.getContentPane().add(textFieldListarTamano);
		textFieldListarTamano.setColumns(10);
		
		JLabel lblTamao_1 = new JLabel("Tamaño");
		lblTamao_1.setBounds(25, 119, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblTamao_1);
		
		JLabel lblGas_1 = new JLabel("Gas");
		lblGas_1.setBounds(306, 119, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblGas_1);
		
		textFieldListarGas = new JTextField();
		textFieldListarGas.setBounds(364, 114, 130, 26);
		internalFrameListarTubos.getContentPane().add(textFieldListarGas);
		textFieldListarGas.setColumns(10);
		
		JButton buttonListarBuscar = new JButton("Buscar");
		buttonListarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lleno = "";
				String propietario = "";
				String gas = "";
				String tamano = "";
				String stock = "";
				if(textFieldListarGas.getText().equals(""))
					gas = "tipo_gas=" + textFieldListarGas.getText();
				if(textFieldListarTamano.getText().equals(""))
					tamano = "tamanio=" + textFieldListarTamano.getText();
				if(chckbxListarEnStock.isSelected())
					stock = "nro_tubo= (SELECT nro_tubo FROM salidas WHERE fecha_devolucion IS NOT NULL)";
					
				String query = "SELECT * FROM tubos WHERE "+ stock + ";";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
				
				ResultSet rs = pst.executeQuery();
				tableListaTubos.setModel(DbUtils.resultSetToTableModel(rs));
				//tableListaTubos.set
				TableColumnModel tcm = tableListaTubos.getColumnModel();
				tcm.getColumn(0).setHeaderValue("Nro tubo");
				tcm.getColumn(1).setHeaderValue("Gas");
				tcm.getColumn(2).setHeaderValue("Tamaño");
				tcm.getColumn(3).setHeaderValue("Propietario");
				tcm.getColumn(4).setHeaderValue("Lleno");
				tcm.getColumn(5).setHeaderValue("Ubicación");
				pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonListarBuscar.setBounds(204, 175, 117, 29);
		internalFrameListarTubos.getContentPane().add(buttonListarBuscar);
		
		tablaMovTubos = new JTable();
		tableListaTubos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//String selection = comboBoxTubos.getSelectedItem().toString();

				try {
					int row = tableListaTubos.getSelectedRow();
					String nroTubo = (tableListaTubos.getModel().getValueAt(row, 0)).toString();
					internalFrameListarTubos.setVisible(false);
					internalFrameInfoTubo.setVisible(true);
					internalFrameInfoTubo.toFront();
					textFieldBuscarNroTubo.setText(nroTubo);
					btnBuscar.doClick();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		internalFrameInfoTubo = new JInternalFrame("Ver información de tubo");
		internalFrameInfoTubo.setResizable(true);
		internalFrameInfoTubo.setMaximizable(true);
		internalFrameInfoTubo.setBounds(10, 6, 623, 623);
		frame.getContentPane().add(internalFrameInfoTubo);
		internalFrameInfoTubo.getContentPane().setLayout(null);

		internalFrameLogin = new JInternalFrame("Login");
		internalFrameLogin.setBounds(53, 6, 493, 300);
		frame.getContentPane().add(internalFrameLogin);
		internalFrameLogin.getContentPane().setLayout(null);
		internalFrameLogin.setVisible(true);

		internalFrameClientes = new JInternalFrame("Nuevo Cliente");
		internalFrameClientes.setEnabled(false);
		internalFrameClientes.setResizable(true);
		internalFrameClientes.setMaximizable(true);
		internalFrameClientes.setBounds(0, 6, 535, 385);
		frame.getContentPane().add(internalFrameClientes);
		internalFrameClientes.getContentPane().setLayout(null);

		internalFrameNuevoAcond = new JInternalFrame("Nuevo acondicionamiento");
		internalFrameNuevoAcond.setBounds(10, 6, 450, 450);
		frame.getContentPane().add(internalFrameNuevoAcond);
		internalFrameNuevoAcond.getContentPane().setLayout(null);

		internalFrameNuevaSalida = new JInternalFrame("Nueva Salida");
		internalFrameNuevaSalida.setResizable(true);
		internalFrameNuevaSalida.setBounds(10, 6, 623, 450);
		frame.getContentPane().add(internalFrameNuevaSalida);
		internalFrameNuevaSalida.getContentPane().setLayout(null);

		InternalFrameNuevaEntrada = new JInternalFrame("Nueva entrada");
		InternalFrameNuevaEntrada.setEnabled(false);
		InternalFrameNuevaEntrada.setResizable(true);
		InternalFrameNuevaEntrada.setMaximizable(true);
		InternalFrameNuevaEntrada.setBounds(0, 6, 535, 385);
		frame.getContentPane().add(InternalFrameNuevaEntrada);
		InternalFrameNuevaEntrada.getContentPane().setLayout(null);

		internalFrameNuevoTubo = new JInternalFrame("Nuevo/modificar tubo");
		internalFrameNuevoTubo.setMaximizable(true);
		internalFrameNuevoTubo.setResizable(true);
		internalFrameNuevoTubo.setEnabled(false);
		internalFrameNuevoTubo.setBounds(0, 6, 535, 385);
		frame.getContentPane().add(internalFrameNuevoTubo);
		internalFrameNuevoTubo.getContentPane().setLayout(null);


		// --------------------------- components -------------------------------------------

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(225, 65, 215, 26);
		internalFrameLogin.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setBounds(225, 103, 215, 26);
		internalFrameLogin.getContentPane().add(textFieldContrasena);
		textFieldContrasena.setColumns(10);

		JLabel lblUsuarion = new JLabel("Usuario");
		lblUsuarion.setBounds(90, 70, 61, 16);
		internalFrameLogin.getContentPane().add(lblUsuarion);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(90, 108, 92, 16);
		internalFrameLogin.getContentPane().add(lblContrasea);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM usuarios WHERE id_usuario=? AND clave=?;";
				try {
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUsuario.getText());
					pst.setString(2, textFieldContrasena.getText());

					ResultSet rs = pst.executeQuery();

					if (!rs.isBeforeFirst() ) {    
						JOptionPane.showMessageDialog(null, "El usuario y/o contraseña son incorrectos, por favor intenta nuevamente"); 
					} else

					{
						internalFrameLogin.setVisible(false);
						usuario = rs.getString("id_usuario");
						tipoUsuario = rs.getString("tipo");
						menuBar.setEnabled(true);
						mnClientes.setEnabled(true);
						mnNotificaciones.setEnabled(true);
						mnTubos.setEnabled(true);
						mnUsuarios.setEnabled(true);
					}


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnOk.setBounds(190, 166, 117, 29);
		internalFrameLogin.getContentPane().add(btnOk);



		textFieldAcondTubo = new JTextField();
		textFieldAcondTubo.setBounds(199, 25, 130, 26);
		internalFrameNuevoAcond.getContentPane().add(textFieldAcondTubo);
		textFieldAcondTubo.setColumns(10);

		textFieldAcondFecha = new JTextField();
		textFieldAcondFecha.setBounds(199, 108, 130, 26);
		internalFrameNuevoAcond.getContentPane().add(textFieldAcondFecha);
		textFieldAcondFecha.setColumns(10);

		comboBoxAcond = new JComboBox();
		comboBoxAcond.setModel(new DefaultComboBoxModel(new String[] {"Llenado", "Prueba hidráulica", "Cambio de válvula", "Tubo rechazado"}));
		comboBoxAcond.setBounds(199, 63, 130, 27);
		internalFrameNuevoAcond.getContentPane().add(comboBoxAcond);

		JLabel lblNroTubo = new JLabel("Nro tubo");
		lblNroTubo.setBounds(21, 30, 61, 16);
		internalFrameNuevoAcond.getContentPane().add(lblNroTubo);

		JLabel lblAcondicionamiento = new JLabel("Acondicionamiento");
		lblAcondicionamiento.setBounds(21, 67, 130, 16);
		internalFrameNuevoAcond.getContentPane().add(lblAcondicionamiento);

		JLabel lblFecha_1 = new JLabel("Fecha");
		lblFecha_1.setBounds(21, 113, 61, 16);
		internalFrameNuevoAcond.getContentPane().add(lblFecha_1);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO salidas (id_acondicionamiento, fecha, nro_tubo, tipo) VALUES (?,?,?,?);";
					PreparedStatement pst = connection.prepareStatement(query);
					int row = getRow("acondicionamiento") + 1;
					pst.setString(1, String.valueOf(row) );
					pst.setString(2, textFieldAcondFecha.getText());
					pst.setString(3, textFieldAcondTubo.getText());
					pst.setString(4, comboBoxAcond.getSelectedItem().toString());


					pst.execute();
					pst.close();

					if (comboBoxAcond.getSelectedItem().toString().equals("Llenado")){
						String query1 = "UPDATE tubos SET lleno='si' where nro_tubo='"+ textFieldAcondTubo.getText() + "';";
						PreparedStatement pst1 = connection.prepareStatement(query1);
						pst1.execute();
						pst1.close();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				internalFrameNuevoAcond.setVisible(false);
			}
		});
		btnAceptar.setBounds(45, 157, 117, 29);
		internalFrameNuevoAcond.getContentPane().add(btnAceptar);




		JLabel lblNroDeTubo = new JLabel("Nro de tubo");
		lblNroDeTubo.setBounds(23, 31, 109, 16);
		internalFrameNuevaSalida.getContentPane().add(lblNroDeTubo);

		textFieldSalidaNroTubo = new JTextField();
		textFieldSalidaNroTubo.setBounds(169, 26, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaNroTubo);
		textFieldSalidaNroTubo.setColumns(10);

		JLabel lblCliente_1 = new JLabel("Cliente");
		lblCliente_1.setBounds(23, 62, 61, 16);
		internalFrameNuevaSalida.getContentPane().add(lblCliente_1);

		textFieldSalidaCliente = new JTextField();
		textFieldSalidaCliente.setBounds(169, 64, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaCliente);
		textFieldSalidaCliente.setColumns(10);

		JLabel lblNroComprobante_1 = new JLabel("Nro comprobante");
		lblNroComprobante_1.setBounds(23, 104, 134, 16);
		internalFrameNuevaSalida.getContentPane().add(lblNroComprobante_1);

		textFieldSalidaComprobante = new JTextField();
		textFieldSalidaComprobante.setBounds(169, 99, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaComprobante);
		textFieldSalidaComprobante.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(23, 132, 61, 16);
		internalFrameNuevaSalida.getContentPane().add(lblFecha);

		textFieldSalidaFecha = new JTextField();
		textFieldSalidaFecha.setBounds(169, 127, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaFecha);
		textFieldSalidaFecha.setColumns(10);

		JLabel lblEmpleado_1 = new JLabel("Empleado");
		lblEmpleado_1.setBounds(23, 160, 61, 16);
		internalFrameNuevaSalida.getContentPane().add(lblEmpleado_1);

		textFieldSalidaEmpleado = new JTextField();
		textFieldSalidaEmpleado.setBounds(169, 155, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaEmpleado);
		textFieldSalidaEmpleado.setColumns(10);

		JLabel lblFlete_1 = new JLabel("Flete");
		lblFlete_1.setBounds(23, 216, 61, 16);
		internalFrameNuevaSalida.getContentPane().add(lblFlete_1);

		comboBoxSalidaFlete = new JComboBox();
		comboBoxSalidaFlete.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
		comboBoxSalidaFlete.setBounds(169, 212, 130, 27);
		internalFrameNuevaSalida.getContentPane().add(comboBoxSalidaFlete);

		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(23, 244, 61, 16);
		internalFrameNuevaSalida.getContentPane().add(lblMonto);

		textFieldSalidaMonto = new JTextField();
		textFieldSalidaMonto.setBounds(169, 239, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaMonto);
		textFieldSalidaMonto.setColumns(10);

		JLabel lblFechaDevolucin = new JLabel("Fecha devolución");
		lblFechaDevolucin.setBounds(23, 275, 134, 16);
		internalFrameNuevaSalida.getContentPane().add(lblFechaDevolucin);

		textFieldSalidaDevolucion = new JTextField();
		textFieldSalidaDevolucion.setBounds(169, 270, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(textFieldSalidaDevolucion);
		textFieldSalidaDevolucion.setColumns(10);

		JLabel lblObservaciones_1 = new JLabel("Observaciones");
		lblObservaciones_1.setBounds(23, 305, 122, 16);
		internalFrameNuevaSalida.getContentPane().add(lblObservaciones_1);

		textPaneSalidaObservaciones = new JTextPane();
		textPaneSalidaObservaciones.setBounds(169, 308, 130, 65);
		internalFrameNuevaSalida.getContentPane().add(textPaneSalidaObservaciones);

		JButton btnNewButtonSalidaOk = new JButton("");
		btnNewButtonSalidaOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO salidas (id_salida, fecha, nro_comprobante, empleado, flete, monto, observaciones, nro_tubo, cliente) VALUES (?,?,?,?,?,?,?,?,?);";
					PreparedStatement pst = connection.prepareStatement(query);
					int row = getRow("salidas") + 1;
					pst.setString(1, String.valueOf(row) );
					pst.setString(2, textFieldSalidaFecha.getText());
					pst.setString(3, textFieldSalidaComprobante.getText());
					pst.setString(4, textFieldSalidaEmpleado.getText());

					pst.setString(5, comboBoxSalidaFlete.getSelectedItem().toString());
					pst.setString(6, textFieldSalidaMonto.getText());
					pst.setString(7, textPaneSalidaObservaciones.getText());
					pst.setString(8, textFieldSalidaNroTubo.getText());
					pst.setString(9, textFieldSalidaCliente.getText());
					pst.execute();
					pst.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//internalFrameNuevoTubo.dispose();
				//internalFrameNuevoTubo.setVisible(false);

				//clearDataNuevEntrada();
				internalFrameNuevaSalida.setVisible(false);
			}
		});
		btnNewButtonSalidaOk.setIcon(new ImageIcon("/Users/cristianmerlo/zamolo/Ok.png"));
		btnNewButtonSalidaOk.setBounds(417, 26, 61, 29);
		internalFrameNuevaSalida.getContentPane().add(btnNewButtonSalidaOk);

		JButton btnCancelar_2 = new JButton("Cancelar");
		btnCancelar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameNuevaSalida.setVisible(false);
			}
		});
		btnCancelar_2.setBounds(417, 64, 117, 29);
		internalFrameNuevaSalida.getContentPane().add(btnCancelar_2);


		textFieldClientesNombre = new JTextField();
		textFieldClientesNombre.setBounds(166, 18, 134, 28);
		internalFrameClientes.getContentPane().add(textFieldClientesNombre);
		textFieldClientesNombre.setColumns(10);

		JLabel lblClientesNombre = new JLabel("Nombre y apellido");
		lblClientesNombre.setBounds(24, 24, 61, 16);
		internalFrameClientes.getContentPane().add(lblClientesNombre);

		textFieldClientesDir = new JTextField();
		textFieldClientesDir.setBounds(166, 49, 134, 28);
		internalFrameClientes.getContentPane().add(textFieldClientesDir);
		textFieldClientesDir.setColumns(10);

		JLabel lblClientesDir = new JLabel("Dirección");
		lblClientesDir.setBounds(24, 52, 61, 16);
		internalFrameClientes.getContentPane().add(lblClientesDir);

		JLabel lblClientesCod = new JLabel("Código postal");
		lblClientesCod.setBounds(24, 88, 61, 16);
		internalFrameClientes.getContentPane().add(lblClientesCod);

		textFieldClientesCod = new JTextField();		
		textFieldClientesCod.setBounds(166, 85, 134, 28);
		internalFrameClientes.getContentPane().add(textFieldClientesCod);
		textFieldClientesCod.setColumns(10);

		JLabel lblClienteUbicacion = new JLabel("Ubicación");
		lblClienteUbicacion.setBounds(24, 116, 125, 16);
		internalFrameClientes.getContentPane().add(lblClienteUbicacion);

		comboBoxUbicacion = new JComboBox();
		comboBoxUbicacion.setModel(new DefaultComboBoxModel(new String[] {"Tandil", "Olavarria", "Colectivo"}));
		comboBoxUbicacion.setBounds(166, 110, 134, 28);
		internalFrameClientes.getContentPane().add(comboBoxUbicacion);


		JLabel lblTel = new JLabel("Teléfono/Cel");
		lblTel.setBounds(24, 150, 61, 16);
		internalFrameClientes.getContentPane().add(lblTel);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(166, 144, 134, 28);
		internalFrameClientes.getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(24, 179, 61, 16);
		internalFrameClientes.getContentPane().add(lblGrupo);

		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setModel(new DefaultComboBoxModel(new String[] {"1", "4"}));
		comboBoxGrupo.setBounds(166, 175, 134, 27);
		internalFrameClientes.getContentPane().add(comboBoxGrupo);

		JLabel lblCuenta = new JLabel("Nro Cuenta");
		lblCuenta.setBounds(24, 200, 125, 16);
		internalFrameClientes.getContentPane().add(lblCuenta);

		textFieldClientesCuenta = new JTextField();
		textFieldClientesCuenta.setBounds(166, 203, 134, 28);
		internalFrameClientes.getContentPane().add(textFieldClientesCuenta);
		textFieldClientesCuenta.setColumns(10);


		JButton btnAgregarCliente = new JButton("");
		btnAgregarCliente.setIcon(new ImageIcon("/Users/cristianmerlo/Downloads/Ok.png"));
		btnAgregarCliente.setBounds(166, 266, 117, 29);
		internalFrameClientes.getContentPane().add(btnAgregarCliente);

		JButton btnCancelarCliente = new JButton("Cancelar");
		btnCancelarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameClientes.setVisible(false);
				//clearDataNuevoTubo();
			}
		});
		btnCancelarCliente.setBounds(302, 266, 117, 29);
		internalFrameClientes.getContentPane().add(btnCancelarCliente);
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = "Se va a agregar el siguiente cliente \n Nombre: " + textFieldClientesNombre.getText().toString() + "\n Dirección: " + textFieldClientesDir.getText() 
				+ "\n Código postal: " + textFieldClientesCod.getText() + "\n Ubicación: " + comboBoxUbicacion.getSelectedItem().toString() + "\n Teléfono: " + textFieldTelefono.getText() 
				+ "\n Cuenta: " + textFieldClientesCuenta.getText() + "\n Grupo: " + comboBoxGrupo.getSelectedItem().toString();
				int action = JOptionPane.showConfirmDialog(null, message, "Agregar cliente", JOptionPane.OK_CANCEL_OPTION);
				if (action == 0)
				{


					try {
						String query = "INSERT INTO clientes (id_cliente, nombre_y_apellido, direccion, telefono, codigo_postal, ubicacion, id_cuenta, grupo_clientes) VALUES (?,?,?,?,?,?,?,?);";
						PreparedStatement pst = connection.prepareStatement(query);
						int row = getRow("clientes") + 1;
						pst.setString(1, String.valueOf(row) );
						pst.setString(2, textFieldClientesNombre.getText());
						pst.setString(3, textFieldClientesDir.getText());
						pst.setString(4, textFieldTelefono.getText());
						pst.setString(5, textFieldClientesCod.getText());	
						pst.setString(6, comboBoxUbicacion.getSelectedItem().toString());
						pst.setString(7, textFieldClientesCuenta.getText()); //checkear que nro de cuenta sea unica
						pst.setString(8, comboBoxGrupo.getSelectedItem().toString());
						pst.execute();
						pst.close();						

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//}
					//internalFrameNuevoTubo.dispose();
					//internalFrameNuevoTubo.setVisible(false);
					//clearDataNuevoTubo();
					internalFrameClientes.setVisible(false);
					//updateTableTubos();
				}
			}
		});



		textFieldEntradaFecha = new JTextField();
		textFieldEntradaFecha.setBounds(166, 82, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldEntradaFecha);
		textFieldEntradaFecha.setColumns(10);

		JLabel lblEntradaFecha = new JLabel("Fecha");
		lblEntradaFecha.setBounds(24, 88, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblEntradaFecha);

		textFieldEntradaNroTubo = new JTextField();
		textFieldEntradaNroTubo.setBounds(166, 18, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldEntradaNroTubo);
		textFieldEntradaNroTubo.setColumns(10);

		JLabel lblEntradaNroTubo = new JLabel("Nro Tubo");
		lblEntradaNroTubo.setBounds(24, 24, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblEntradaNroTubo);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(24, 52, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblCliente);

		textFieldEntradaCliente = new JTextField();
		textFieldEntradaCliente.setBounds(166, 49, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldEntradaCliente);
		textFieldEntradaCliente.setColumns(10);

		JLabel lblNroComprobante = new JLabel("Nro comprobante");
		lblNroComprobante.setBounds(24, 116, 125, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblNroComprobante);

		textFieldEntradaComprobante = new JTextField();
		textFieldEntradaComprobante.setBounds(166, 110, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldEntradaComprobante);
		textFieldEntradaComprobante.setColumns(10);

		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(24, 150, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblEmpleado);

		textFieldEmpleado = new JTextField();
		textFieldEmpleado.setBounds(166, 144, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldEmpleado);
		textFieldEmpleado.setColumns(10);

		JLabel lblMotivo = new JLabel("Motivo");
		lblMotivo.setBounds(24, 179, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblMotivo);

		comboBoxMotivo = new JComboBox();
		comboBoxMotivo.setModel(new DefaultComboBoxModel(new String[] {"Llenado", "Prueba Hidráulica"}));
		comboBoxMotivo.setBounds(166, 175, 134, 27);
		InternalFrameNuevaEntrada.getContentPane().add(comboBoxMotivo);

		JLabel lblFlete = new JLabel("Flete");
		lblFlete.setBounds(24, 207, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblFlete);

		comboBoxFlete = new JComboBox();
		comboBoxFlete.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
		comboBoxFlete.setBounds(166, 203, 134, 27);
		InternalFrameNuevaEntrada.getContentPane().add(comboBoxFlete);

		JLabel lblMontoTotal = new JLabel("Monto Total");
		lblMontoTotal.setBounds(24, 234, 125, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblMontoTotal);

		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(166, 228, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(textFieldMonto);
		textFieldMonto.setColumns(10);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(24, 261, 100, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblObservaciones);

		textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setLineWrap(true);
		textAreaObservaciones.setBounds(166, 268, 125, 54);
		InternalFrameNuevaEntrada.getContentPane().add(textAreaObservaciones);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if (isTuboAdded(textFieldEntradaNroTubo.getText().toString())){
					String message = "Se va a agregar la siguiente entrada: \n numero de tubo: " + textFieldEntradaNroTubo.getText().toString() + "\n cliente: " + textFieldEntradaCliente.getText() + "\n fecha: " + textFieldEntradaFecha.getText() + "\n empleado: " + textFieldEmpleado.getText() 
					+ "\n motivo: " + comboBoxMotivo.getSelectedItem().toString() + "\n flete: " + comboBoxFlete.getSelectedItem().toString() + "\n monto total: " + textFieldMonto.getText();
					int action = JOptionPane.showConfirmDialog(null, message, "Agregar entrada", JOptionPane.OK_CANCEL_OPTION);
					if (action == 0)
					{


						try {
							String query = "INSERT INTO entradas (id_entrada, fecha, nro_comprobante, empleado, motivo, flete, monto_total, observaciones, nro_tubo, cliente) VALUES (?,?,?,?,?,?,?,?,?,?);";
							PreparedStatement pst = connection.prepareStatement(query);
							int row = getRow("entradas") + 1;
							pst.setString(1, String.valueOf(row) );
							pst.setString(2, textFieldEntradaFecha.getText());
							pst.setString(3, textFieldEntradaComprobante.getText());
							pst.setString(4, textFieldEmpleado.getText());
							pst.setString(5, comboBoxMotivo.getSelectedItem().toString());
							pst.setString(6, comboBoxFlete.getSelectedItem().toString());
							pst.setString(7, textFieldMonto.getText());
							pst.setString(8, textAreaObservaciones.getText());
							pst.setString(9, textFieldEntradaNroTubo.getText());
							pst.setString(10, textFieldEntradaCliente.getText());
							pst.execute();
							pst.close();			

							String llenado = "";
							if (comboBoxMotivo.getSelectedItem().toString() == "llenado")
							{
								String query1 = "UPDATE tubos SET lleno='no' WHERE nro_tubo='" + textFieldEntradaNroTubo.getText() + "';";
								PreparedStatement pst1;

								pst1 = connection.prepareStatement(query1);
								pst1.execute();
								pst1.close();
							}
							String query1 = "UPDATE salidas SET fecha_devolucion='" + textFieldEntradaFecha.getText() + "' WHERE nro_tubo='" + textFieldEntradaNroTubo.getText() + "';";
							PreparedStatement pst1;

							pst1 = connection.prepareStatement(query1);
							pst1.execute();
							pst1.close();		

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//internalFrameNuevoTubo.dispose();
						//internalFrameNuevoTubo.setVisible(false);

						clearDataNuevEntrada();
						InternalFrameNuevaEntrada.setVisible(false);
						//updateTableTubos();
					}
				}else{
					int actionB = JOptionPane.showConfirmDialog(null, "El número de tubo no existe, desea agregarlo?", "Error", JOptionPane.OK_CANCEL_OPTION);
					if (actionB==0){
						InternalFrameNuevaEntrada.setVisible(false);
						internalFrameNuevoTubo.setVisible(true);
						textFieldNroTubo.setText(textFieldEntradaNroTubo.getText());

					}
				}


			}

		});
		btnAgregar.setBounds(327, 19, 117, 29);
		InternalFrameNuevaEntrada.getContentPane().add(btnAgregar);

		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.setBounds(327, 64, 117, 29);
		InternalFrameNuevaEntrada.getContentPane().add(btnCancelar_1);


		textFieldNroTubo = new JTextField();
		textFieldNroTubo.setBounds(145, 19, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldNroTubo);
		textFieldNroTubo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Número de tubo");
		lblNewLabel.setBounds(17, 25, 109, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tipo de gas");
		lblNewLabel_1.setBounds(17, 65, 96, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_1);

		textFieldGas = new JTextField();
		textFieldGas.setBounds(145, 59, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldGas);
		textFieldGas.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tamaño");
		lblNewLabel_2.setBounds(17, 105, 61, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_2);

		textFieldTamano = new JTextField();
		textFieldTamano.setBounds(145, 99, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldTamano);
		textFieldTamano.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Propietario");
		lblNewLabel_3.setBounds(17, 145, 109, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_3);

		textFieldPropietario = new JTextField();
		textFieldPropietario.setBounds(145, 133, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldPropietario);
		textFieldPropietario.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ubicación");
		lblNewLabel_4.setBounds(17, 185, 109, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_4);

		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setBounds(145, 173, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldUbicacion);
		textFieldUbicacion.setColumns(10);

		JButton btnAgregarTubo = new JButton("");
		btnAgregarTubo.setIcon(new ImageIcon("/Users/cristianmerlo/Downloads/Ok.png"));
		btnAgregarTubo.setBounds(162, 210, 117, 29);
		internalFrameNuevoTubo.getContentPane().add(btnAgregarTubo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameNuevoTubo.setVisible(false);
				clearDataNuevoTubo();
			}
		});
		btnCancelar.setBounds(302, 210, 117, 29);
		internalFrameNuevoTubo.getContentPane().add(btnCancelar);
		btnAgregarTubo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (isTuboAdded(textFieldNroTubo.getText())){
					String message = "Se va a modificar el siguiente tubo \n numero de tubo: " + textFieldNroTubo.getText().toString() + "\n Desea continuar?";
					int action = JOptionPane.showConfirmDialog(null, message, "Modificar tubo", JOptionPane.OK_CANCEL_OPTION);
					if (action == 0)
					{
						try {
							String query = "UPDATE tubos SET tipo_gas='" + textFieldGas.getText() +"', tamanio='" + textFieldTamano.getText() +"', propietario='" + textFieldPropietario.getText() +"', ubicacion='" + textFieldUbicacion.getText() + "' WHERE nro_tubo='" + textFieldNroTubo.getText() + "';";
							PreparedStatement pst = connection.prepareStatement(query);

							pst.execute();
							//pst.close();		
							internalFrameNuevoTubo.setVisible(false);
							clearDataNuevoTubo();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else{

					String message = "Se va a agregar el siguiente tubo \n numero de tubo: " + textFieldNroTubo.getText().toString() + "\n gas: " + textFieldGas.getText() + "\n Tamaño: " + textFieldTamano.getText() + "\n propietario: " + textFieldPropietario.getText() + "\n ubicacion: " + textFieldUbicacion.getText();
					int action = JOptionPane.showConfirmDialog(null, message, "Agregar tubo", JOptionPane.OK_CANCEL_OPTION);
					if (action == 0)
					{


						try {
							String query = "INSERT INTO tubos (nro_tubo, tipo_gas, tamanio, propietario, ubicacion) VALUES (?,?,?,?,?);";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, textFieldNroTubo.getText());
							pst.setString(2, textFieldGas.getText());
							pst.setString(3, textFieldTamano.getText());
							pst.setString(4, textFieldPropietario.getText());
							pst.setString(5, textFieldUbicacion.getText());
							pst.execute();
							pst.close();				

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					//internalFrameNuevoTubo.dispose();
					//internalFrameNuevoTubo.setVisible(false);
					clearDataNuevoTubo();
					internalFrameNuevoTubo.setVisible(false);
					//updateTableTubos();
				}
			}
		});





		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 233, 593, 153);
		internalFrameInfoTubo.getContentPane().add(scrollPane);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));

		tablaMovTubos = new JTable();
		
		
		scrollPane.setViewportView(tablaMovTubos);

		btnActualizarTubo = new JButton("Modificar tubo");
		btnActualizarTubo.setBounds(422, 182, 134, 29);
		internalFrameInfoTubo.getContentPane().add(btnActualizarTubo);
		
		
		btnActualizarTubo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNroTubo.setText(textFieldBuscarNroTubo.getText().toString());
				textFieldGas.setText(textFieldBuscarGas.getText().toString());
				textFieldTamano.setText(textFieldBuscarTamano.getText().toString());
				textFieldPropietario.setText(textFieldBuscarPropietario.getText().toString());

				internalFrameInfoTubo.setVisible(false);
				internalFrameNuevoTubo.setVisible(true);


				//updateTableEntradas();
			}
		});


		/*btnActualizarTubos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {

					String selection = new String();
					switch ((String)comboBoxFilter.getSelectedItem()){
					case "Todos": selection = null; break;
					case "Número": selection = "nro_tubo"; break;
					case "Gas": selection = "tipo_gas"; break;
					case "Tamaño": selection = "tamanio"; break;
					case "Propietario": selection = "propietario"; break;

					}
					String query = new String();
					PreparedStatement pst;
					if (selection!=null)
					{
						query = "select * from tubos where " + selection + "=? ";
						pst = connection.prepareStatement(query);
						pst.setString(1, txtBsqueda.getText());
					}
					else {
						query = "select * from tubos";
						pst = connection.prepareStatement(query);
					}
					ResultSet rs = pst.executeQuery();
					tablaTubos.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});*/


		//updateTable();
		try {
			String query = "SELECT * FROM tubos;";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			textFieldBuscarNroTubo = new JTextField();
			textFieldBuscarNroTubo.setBounds(124, 14, 134, 28);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarNroTubo);
			textFieldBuscarNroTubo.setColumns(10);

			JLabel lblNrotubo = new JLabel("NroTubo");
			lblNrotubo.setBounds(20, 20, 61, 16);
			internalFrameInfoTubo.getContentPane().add(lblNrotubo);

			textFieldBuscarGas = new JTextField();
			textFieldBuscarGas.setEnabled(false);
			textFieldBuscarGas.setEditable(false);
			textFieldBuscarGas.setBounds(124, 65, 134, 28);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarGas);
			textFieldBuscarGas.setColumns(10);

			JLabel lblGas = new JLabel("Gas");
			lblGas.setBounds(20, 71, 61, 16);
			internalFrameInfoTubo.getContentPane().add(lblGas);

			textFieldBuscarTamano = new JTextField();
			textFieldBuscarTamano.setEnabled(false);
			textFieldBuscarTamano.setEditable(false);
			textFieldBuscarTamano.setBounds(124, 93, 134, 28);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarTamano);
			textFieldBuscarTamano.setColumns(10);

			JLabel lblTamao = new JLabel("Tamaño");
			lblTamao.setBounds(20, 99, 61, 16);
			internalFrameInfoTubo.getContentPane().add(lblTamao);

			textFieldBuscarPropietario = new JTextField();
			textFieldBuscarPropietario.setEnabled(false);
			textFieldBuscarPropietario.setEditable(false);
			textFieldBuscarPropietario.setBounds(124, 121, 134, 28);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarPropietario);
			textFieldBuscarPropietario.setColumns(10);

			JLabel lblNewLabel_20 = new JLabel("Propietario");
			lblNewLabel_20.setBounds(20, 127, 75, 16);
			internalFrameInfoTubo.getContentPane().add(lblNewLabel_20);

			btnBuscar = new JButton("Ver info");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String query = "select * from tubos where nro_tubo=" + textFieldBuscarNroTubo.getText() + ";";
					PreparedStatement pst;
					try {
						pst = connection.prepareStatement(query);

						ResultSet rs = pst.executeQuery();
						if (!rs.next())
							JOptionPane.showMessageDialog(null, "El número de tubo seleccionado no existe, intente nuevamente");
						else{

							textFieldBuscarGas.setText(rs.getString(2));
							textFieldBuscarTamano.setText(rs.getString(3));
							textFieldBuscarPropietario.setText(rs.getString(4));
							textFieldBuscarUbicacion.setText(rs.getString(6));
							textFieldBuscarLleno.setText(rs.getString(5));

							//ver tubo en tabla
							String query1 = "select nro_tubo, cliente, fecha, nro_comprobante, empleado from entradas where nro_tubo=" + textFieldBuscarNroTubo.getText() + " UNION ALL SELECT nro_tubo, cliente, fecha, nro_comprobante, empleado FROM salidas where nro_tubo=" + textFieldBuscarNroTubo.getText() + ";";
							PreparedStatement pst1 = connection.prepareStatement(query1);
							ResultSet rs1 = pst1.executeQuery();
							
								tablaMovTubos.setModel(DbUtils.resultSetToTableModel(rs1));

							String query2 = "select fecha, tipo from acondicionamientos where nro_tubo=" + textFieldBuscarNroTubo.getText() + ";";
							PreparedStatement pst2 = connection.prepareStatement(query2);
							ResultSet rs2 = pst2.executeQuery();
							while (rs2.next())
								tableAcond.setModel(DbUtils.resultSetToTableModel(rs2));
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				}
			});

			btnBuscar.setBounds(343, 15, 117, 29);
			internalFrameInfoTubo.getContentPane().add(btnBuscar);

			lblUbicacin = new JLabel("Ubicación");
			lblUbicacin.setBounds(303, 71, 97, 16);
			internalFrameInfoTubo.getContentPane().add(lblUbicacin);

			textFieldBuscarUbicacion = new JTextField();
			textFieldBuscarUbicacion.setEnabled(false);
			textFieldBuscarUbicacion.setBounds(426, 66, 130, 26);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarUbicacion);
			textFieldBuscarUbicacion.setColumns(10);

			lblEnStock = new JLabel("En stock?");
			lblEnStock.setBounds(304, 99, 61, 16);
			internalFrameInfoTubo.getContentPane().add(lblEnStock);

			textFieldBuscarEnStock = new JTextField();
			textFieldBuscarEnStock.setEnabled(false);
			textFieldBuscarEnStock.setBounds(426, 94, 130, 26);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarEnStock);
			textFieldBuscarEnStock.setColumns(10);

			lblLleno = new JLabel("Lleno?");
			lblLleno.setBounds(304, 127, 61, 16);
			internalFrameInfoTubo.getContentPane().add(lblLleno);

			textFieldBuscarLleno = new JTextField();
			textFieldBuscarLleno.setEnabled(false);
			textFieldBuscarLleno.setBounds(426, 122, 130, 26);
			internalFrameInfoTubo.getContentPane().add(textFieldBuscarLleno);
			textFieldBuscarLleno.setColumns(10);

			lblMovimientos = new JLabel("Movimientos");
			lblMovimientos.setBounds(20, 205, 117, 16);
			internalFrameInfoTubo.getContentPane().add(lblMovimientos);

			lblAcondicionamientos = new JLabel("Acondicionamientos");
			lblAcondicionamientos.setBounds(16, 398, 145, 16);
			internalFrameInfoTubo.getContentPane().add(lblAcondicionamientos);

			JScrollPane scrollPane4 = new JScrollPane();
			scrollPane4.setBounds(0, 426, 593, 145);
			internalFrameInfoTubo.getContentPane().add(scrollPane4);

			tableAcond = new JTable();
			scrollPane4.setViewportView(tableAcond);
			
			JSeparator separator1 = new JSeparator();
			separator1.setBounds(0, 47, 586, 12);
			internalFrameInfoTubo.getContentPane().add(separator1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
