import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.proteanit.sql.DbUtils;
import workers.LoginWorker;

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
import java.util.Vector;
import java.util.concurrent.ExecutionException;
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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

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
	private JComboBox comboBoxEntradaCliente;
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

	private JInternalFrame internalFrameListarTubos;
	private JComboBox comboBoxListarEstado;
	private JComboBox comboBoxListarPropietario;
	private JComboBox comboBoxListarCiudad;
	private JComboBox comboBoxNuevoTubo;
	private JComboBox comboBoxListarUbicacion;
	private JLabel lblNmeroAlternativo;
	private JTextField textFieldNroAltern;
	private JTextPane textPaneAcondObservaciones;
	private JInternalFrame internalFrameVerMov;
	private JLabel lblFecha_2;
	private JTextField textFieldMovFecha;
	private JTextField textFieldMovEmpleado;
	private JLabel lblEmpleado_2;
	private JLabel lblMotivo_1;
	private JComboBox comboBoxMovMotivo;
	private JLabel lblFlete_2;
	private JComboBox comboBoxMovFlete;
	private JLabel lblNroTubo_1;
	private JTextField textFieldMovTubo;
	private JLabel lblCliente_2;
	private JComboBox comboBoxMovCliente;
	private JTable tableMovMov;
	private JScrollPane scrollPane_2;
	private JButton btnBuscarMov;
	private JComboBox comboBoxMovTipo;
	private JLabel lblTipo;
	private JDatePickerImpl datePickerMov;
	private JDatePickerImpl datePickerAcond;
	private JDatePickerImpl datePickerEntrada;
	private JDatePickerImpl datePickerSalida;
	private JComboBox comboBoxSalidaCliente;
	private JInternalFrame internalFrameClientesBuscar;
	private JComboBox comboBoxCliBusqClientes;
	private JLabel lblCliente_3;
	private JLabel lblUbicacin_2;
	private JComboBox comboBoxCliBusqUbicacion;
	private JLabel lblGrupo_1;
	private JComboBox comboBoxCliBusqGrupo;
	private JTable tableCliBusq;
	private JButton btnBuscarCliBusq;
	private JScrollPane scrollPane_3;
	
	public EmporioApp() {
		initialize();

	}

	private void runNotification() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 49);
		today.set(Calendar.SECOND, 0);

		// every night at 2am you run your task
		Timer timer = new Timer();
		timer.schedule(new NotificationTask(connection), today.getTime(),
				TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100
																	// =
																	// 8640000ms
	}

	private void clearDataNuevoTubo() {
		textFieldNroTubo.setText("");
		textFieldGas.setText("");
		textFieldTamano.setText("");
		textFieldPropietario.setText("");

	}

	private void clearDataNuevEntrada() {
		textFieldEntradaComprobante.setText("");
		textFieldEmpleado.setText("");
		textFieldMonto.setText("");
		textAreaObservaciones.setText("");
		textFieldEntradaNroTubo.setText("");

	}

	private void updateTableEntradas() {
		try {
			String query = "select * from entradas;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				tablaMovTubos.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void updateTableTubos() {
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

	private void fillComboBoxTubos() {

	}

	private boolean isTuboAdded(String tubo) {
		boolean isThere = false;
		try {
			String query = "SELECT * FROM tubos;";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next() && !isThere)
				if (rs.getString(1).equals(tubo))
					isThere = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isThere;
	}

	private boolean isClienteInDB(String cliente) {
		boolean isThere = false;
		String cliente1 = cliente.toLowerCase();
		try {
			String query = "SELECT * FROM clientes;";
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next() && !isThere)
				if (rs.getString(2).toLowerCase().equals(cliente1))
					isThere = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isThere;
	}

	private int getRow(String table) {
		String query = "select * from " + table + ";";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			int i = 0;
			while (rs.next())
				i++;
			// rs.last();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	private void getClientes(JComboBox combo){
		String query = "select id_cliente, nombre_y_apellido from clientes;";
		try {
			PreparedStatement pst = connection.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			List<String> list = new Vector<String>();
			while (rs.next())
				list.add(rs.getString(2) + ", " + rs.getString(1));
				//combo.addItem(rs.getString(2) + ", " + rs.getString(1));
			Collections.sort(list);
			int i=0;
			while(i < list.size()){
				combo.addItem(list.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

		// ------------------------------------Menu
		// items------------------------------------

		menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		frame.setJMenuBar(menuBar);

		mnTubos = new JMenu("Tubos");
		mnTubos.setEnabled(false);
		menuBar.add(mnTubos);

		JMenuItem mntmBuscar = new JMenuItem("Ver información de tubo");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrameInfoTubo.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				internalFrameInfoTubo.setVisible(true);
				internalFrameInfoTubo.toFront();
			}
		});

		JMenuItem mntmNuevo = new JMenuItem("Nuevo/Modificar tubo");

		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrameNuevoTubo.setClosed(false);
				} catch (PropertyVetoException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				internalFrameNuevoTubo.setVisible(true);
				internalFrameNuevoTubo.toFront();

			}
		});
		mnTubos.add(mntmNuevo);

		mnMovimientos = new JMenu("Movimientos");
		mnTubos.add(mnMovimientos);

		mntmNuevaEntrada = new JMenuItem("Nueva entrada");
		mntmNuevaEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					InternalFrameNuevaEntrada.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InternalFrameNuevaEntrada.setVisible(true);
				InternalFrameNuevaEntrada.toFront();
			}
		});
		mnMovimientos.add(mntmNuevaEntrada);

		mntmNuevaSalida = new JMenuItem("Nueva salida");
		mntmNuevaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					internalFrameNuevaSalida.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				internalFrameNuevaSalida.setVisible(true);
				internalFrameNuevaSalida.toFront();
			}
		});
		mnMovimientos.add(mntmNuevaSalida);

		mntmVerMovimientos = new JMenuItem("Ver movimientos");
		mntmVerMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				internalFrameVerMov.setVisible(true);
				internalFrameVerMov.toFront();
			}
		});
		mnMovimientos.add(mntmVerMovimientos);

		JMenuItem mntmNuevoAcondicionamiento = new JMenuItem("Nuevo acondicionamiento");
		mntmNuevoAcondicionamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrameNuevoAcond.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				internalFrameNuevoAcond.setVisible(true);
				internalFrameNuevoAcond.toFront();
			}
		});
		mnTubos.add(mntmNuevoAcondicionamiento);

		JSeparator separator = new JSeparator();
		mnTubos.add(separator);

		JMenuItem mntmListar = new JMenuItem("Ver tubos");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrameListarTubos.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				internalFrameListarTubos.setVisible(true);
				internalFrameListarTubos.toFront();
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
		mntmBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrameClientesBuscar.setClosed(false);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				internalFrameClientesBuscar.setVisible(true);
				internalFrameListarTubos.toFront();
			}
		});
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
				if (tipoUsuario.equals("admin")) {
					JOptionPane.showMessageDialog(null, "OK!");
				} else
					JOptionPane.showMessageDialog(null, "El usuario no posee permisos para realizar esta operación");
			}
		});
		mnUsuarios.add(mntmCrearmodificar);

		mntmCambiarContrasea = new JMenuItem("Cambiar Contraseña");
		mnUsuarios.add(mntmCambiarContrasea);

		// ----------------------------internal frames
		// definitions--------------------------------------
		
		internalFrameClientesBuscar = new JInternalFrame("Buscar clientes");
		internalFrameClientesBuscar.setClosable(true);
		try {
			internalFrameClientesBuscar.setClosed(true);
		} catch (PropertyVetoException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		internalFrameClientesBuscar.setBounds(10, 10, 691, 443);
		frame.getContentPane().add(internalFrameClientesBuscar);
		internalFrameClientesBuscar.getContentPane().setLayout(null);
		
		comboBoxCliBusqClientes = new JComboBox();
		comboBoxCliBusqClientes.setModel(new DefaultComboBoxModel(new String[] {"Todos"}));
		comboBoxCliBusqClientes.setBounds(67, 30, 145, 27);
		internalFrameClientesBuscar.getContentPane().add(comboBoxCliBusqClientes);
		getClientes(comboBoxCliBusqClientes);
		
		lblCliente_3 = new JLabel("Cliente");
		lblCliente_3.setBounds(6, 34, 61, 16);
		internalFrameClientesBuscar.getContentPane().add(lblCliente_3);
		
		lblUbicacin_2 = new JLabel("Ubicación");
		lblUbicacin_2.setBounds(224, 34, 97, 16);
		internalFrameClientesBuscar.getContentPane().add(lblUbicacin_2);
		
		comboBoxCliBusqUbicacion = new JComboBox();
		comboBoxCliBusqUbicacion.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Tandil", "Olavarría", "Colectivo"}));
		comboBoxCliBusqUbicacion.setBounds(301, 30, 145, 27);
		internalFrameClientesBuscar.getContentPane().add(comboBoxCliBusqUbicacion);
		
		lblGrupo_1 = new JLabel("Grupo");
		lblGrupo_1.setBounds(473, 34, 61, 16);
		internalFrameClientesBuscar.getContentPane().add(lblGrupo_1);
		
		comboBoxCliBusqGrupo = new JComboBox();
		comboBoxCliBusqGrupo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "1", "4"}));
		comboBoxCliBusqGrupo.setBounds(532, 30, 101, 27);
		internalFrameClientesBuscar.getContentPane().add(comboBoxCliBusqGrupo);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 114, 655, 277);
		internalFrameClientesBuscar.getContentPane().add(scrollPane_3);
		
		tableCliBusq = new JTable();
		scrollPane_3.setViewportView(tableCliBusq);
		
		btnBuscarCliBusq = new JButton("Buscar");
		btnBuscarCliBusq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cliente = "";
				String ubicacion = "";
				String grupo = "";
				
				List<String> list = new ArrayList<String>();

				if (!comboBoxCliBusqClientes.getSelectedItem().equals("Todos")) {
					String str = comboBoxCliBusqClientes.getSelectedItem().toString();
					cliente = "id_cliente= '" + str.substring(str.indexOf(", ")+2) + "'";
					list.add(cliente);
				}

				if (!comboBoxCliBusqUbicacion.getSelectedItem().equals("Todas")) {
					ubicacion = "ubicacion= '" + comboBoxCliBusqUbicacion.getSelectedItem().toString() + "'";
					list.add(ubicacion);
				}

				if (!comboBoxCliBusqGrupo.getSelectedItem().equals("Todos")) {
					
					grupo = "grupo_clientes= '" + comboBoxCliBusqGrupo.getSelectedItem().toString() + "'";
					list.add(grupo);
				}

				

				String subq = "";
				String query = "";
				if (list.size() != 0) {
					subq = list.get(0);
					for (int i = 1; i < list.size(); i++)
						subq = subq + " AND " + list.get(i);
					query = "SELECT * FROM clientes WHERE " + subq + ";";
				} else
					query = "SELECT * FROM clientes;";

				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					tableCliBusq.setModel(DbUtils.resultSetToTableModel(rs));
					// tableListaTubos.set
					
					 TableColumnModel tcm = tableCliBusq.getColumnModel();
					 tcm.getColumn(0).setHeaderValue("Nro CLiente");
					 tcm.getColumn(1).setHeaderValue("Nombre");
					 tcm.getColumn(2).setHeaderValue("Dirección");
					 tcm.getColumn(3).setHeaderValue("Teléfono");
					 tcm.getColumn(4).setHeaderValue("Cod Postal");
					 tcm.getColumn(5).setHeaderValue("Ubicación");
					 tcm.getColumn(6).setHeaderValue("Nro Cuenta");
					 tcm.getColumn(7).setHeaderValue("Grupo");
					 
					pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnBuscarCliBusq.setBounds(516, 73, 117, 29);
		internalFrameClientesBuscar.getContentPane().add(btnBuscarCliBusq);
		

		internalFrameVerMov = new JInternalFrame("Ver movimientos");
		internalFrameVerMov.setResizable(true);
		internalFrameVerMov.setClosable(true);
		try {
			internalFrameVerMov.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameVerMov.setBounds(10, 6, 744, 461);
		frame.getContentPane().add(internalFrameVerMov);
		internalFrameVerMov.getContentPane().setLayout(null);

		lblFecha_2 = new JLabel("Fecha");
		lblFecha_2.setBounds(483, 40, 61, 16);
		internalFrameVerMov.getContentPane().add(lblFecha_2);

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelMov = new JDatePanelImpl(model, p);
		datePickerMov = new JDatePickerImpl(datePanelMov, new DateLabelFormatter());
		datePickerMov.setBounds(529, 30, 185, 26);
		internalFrameVerMov.getContentPane().add(datePickerMov);

		textFieldMovEmpleado = new JTextField();
		textFieldMovEmpleado.setBounds(349, 30, 130, 26);
		internalFrameVerMov.getContentPane().add(textFieldMovEmpleado);
		textFieldMovEmpleado.setColumns(10);

		lblEmpleado_2 = new JLabel("Empleado");
		lblEmpleado_2.setBounds(276, 35, 61, 16);
		internalFrameVerMov.getContentPane().add(lblEmpleado_2);

		lblMotivo_1 = new JLabel("Motivo");
		lblMotivo_1.setBounds(34, 66, 61, 16);
		internalFrameVerMov.getContentPane().add(lblMotivo_1);

		comboBoxMovMotivo = new JComboBox();
		comboBoxMovMotivo.setModel(new DefaultComboBoxModel(
				new String[] { "Todos", "Llenado", "Prueba hidráulica", "Cambio de válvula", "Tubo rechazado" }));
		comboBoxMovMotivo.setBounds(107, 62, 130, 27);
		internalFrameVerMov.getContentPane().add(comboBoxMovMotivo);

		lblFlete_2 = new JLabel("Flete");
		lblFlete_2.setBounds(276, 66, 61, 16);
		internalFrameVerMov.getContentPane().add(lblFlete_2);

		comboBoxMovFlete = new JComboBox();
		comboBoxMovFlete.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Si", "No" }));
		comboBoxMovFlete.setBounds(349, 62, 130, 27);
		internalFrameVerMov.getContentPane().add(comboBoxMovFlete);

		lblNroTubo_1 = new JLabel("Nro tubo");
		lblNroTubo_1.setBounds(34, 97, 61, 16);
		internalFrameVerMov.getContentPane().add(lblNroTubo_1);

		textFieldMovTubo = new JTextField();
		textFieldMovTubo.setBounds(107, 92, 130, 26);
		internalFrameVerMov.getContentPane().add(textFieldMovTubo);
		textFieldMovTubo.setColumns(10);

		lblCliente_2 = new JLabel("Cliente");
		lblCliente_2.setBounds(529, 66, 61, 16);
		internalFrameVerMov.getContentPane().add(lblCliente_2);

		comboBoxMovCliente = new JComboBox();
		comboBoxMovCliente.setModel(new DefaultComboBoxModel(new String[] { "Todos" }));
		comboBoxMovCliente.setBounds(584, 63, 130, 27);
		internalFrameVerMov.getContentPane().add(comboBoxMovCliente);
		getClientes(comboBoxMovCliente);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 155, 708, 254);
		internalFrameVerMov.getContentPane().add(scrollPane_2);

		tableMovMov = new JTable();
		scrollPane_2.setViewportView(tableMovMov);

		btnBuscarMov = new JButton("Buscar");
		btnBuscarMov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String empleado = "";
				String fecha = "";
				String flete = "";
				String motivo = "";
				String cliente = "";
				String tubo = "";
				String tabla = "";
				List<String> list = new ArrayList<String>();

				if (!textFieldMovEmpleado.getText().equals("")) {
					empleado = "empleado= '" + textFieldMovEmpleado.getText() + "'";
					list.add(empleado);
				}

				if (!datePickerMov.getModel().getValue().toString().equals("")) {
					fecha = "fecha= '" + datePickerMov.getModel().getValue().toString() + "'";
					list.add(fecha);
				}

				if (!comboBoxMovFlete.getSelectedItem().equals("Todos")) {
					flete = "flete= '" + comboBoxMovFlete.getSelectedItem() + "'";
					list.add(flete);
				}

				if (!comboBoxMovMotivo.getSelectedItem().equals("Todos")) {
					motivo = "motivo= '" + comboBoxMovMotivo.getSelectedItem().toString() + "'";
					list.add(motivo);
				}

				if (!comboBoxMovCliente.getSelectedItem().equals("Todos")) {
					String str = comboBoxMovCliente.getSelectedItem().toString();
					cliente = "cliente= '" + str.substring(str.indexOf(", ")+2) + "'";
					list.add(cliente);
				}

				if (!textFieldMovTubo.getText().equals("")) {
					tubo = "nro_tubo= '" + textFieldMovTubo.getText() + "'";
					list.add(tubo);
				}

				if (comboBoxMovTipo.getSelectedItem().equals("Entrada"))
					tabla = "entradas";
				else
					tabla = "salidas";

				String subq = "";
				String query = "";
				if (list.size() != 0) {
					subq = list.get(0);
					for (int i = 1; i < list.size(); i++)
						subq = subq + " AND " + list.get(i);
					query = "SELECT * FROM " + tabla + " WHERE " + subq + ";";
				} else
					query = "SELECT * FROM " + tabla + ";";

				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					tableMovMov.setModel(DbUtils.resultSetToTableModel(rs));
					// tableListaTubos.set
					/*
					 * TableColumnModel tcm = tableListaTubos.getColumnModel();
					 * tcm.getColumn(0).setHeaderValue("Nro tubo");
					 * tcm.getColumn(1).setHeaderValue("Gas");
					 * tcm.getColumn(2).setHeaderValue("Tamaño");
					 * tcm.getColumn(3).setHeaderValue("Propietario");
					 * tcm.getColumn(4).setHeaderValue("Lleno");
					 * tcm.getColumn(5).setHeaderValue("Ubicación");
					 */
					pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnBuscarMov.setBounds(388, 114, 117, 29);
		internalFrameVerMov.getContentPane().add(btnBuscarMov);

		comboBoxMovTipo = new JComboBox();
		comboBoxMovTipo.setModel(new DefaultComboBoxModel(new String[] { "Entrada", "Salida" }));
		comboBoxMovTipo.setBounds(107, 31, 130, 27);
		internalFrameVerMov.getContentPane().add(comboBoxMovTipo);

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(34, 35, 61, 16);
		internalFrameVerMov.getContentPane().add(lblTipo);

		internalFrameNuevoAcond = new JInternalFrame("Nuevo acondicionamiento");
		internalFrameNuevoAcond.setResizable(true);
		internalFrameNuevoAcond.setClosable(true);
		try {
			internalFrameNuevoAcond.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameNuevoAcond.setBounds(10, 6, 450, 340);
		frame.getContentPane().add(internalFrameNuevoAcond);
		internalFrameNuevoAcond.getContentPane().setLayout(null);

		internalFrameListarTubos = new JInternalFrame("Buscar tubos");
		try {
			internalFrameListarTubos.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameListarTubos.setResizable(true);
		internalFrameListarTubos.setClosable(true);
		internalFrameListarTubos.setBounds(10, 0, 663, 600);
		frame.getContentPane().add(internalFrameListarTubos);
		internalFrameListarTubos.getContentPane().setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 216, 627, 311);
		internalFrameListarTubos.getContentPane().add(scrollPane_1);

		String[] columnNames = { "Nro tubo", "Gas", "Tamaño", "Propietario", "Lleno", "Ubicación" };

		tableListaTubos = new JTable();
		scrollPane_1.setViewportView(tableListaTubos);

		comboBoxListarEstado = new JComboBox();
		comboBoxListarEstado.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Si", "No" }));
		comboBoxListarEstado.setBounds(463, 48, 143, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarEstado);

		comboBoxListarPropietario = new JComboBox();
		comboBoxListarPropietario.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Z", "P" }));
		comboBoxListarPropietario.setBounds(270, 48, 135, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarPropietario);

		comboBoxListarCiudad = new JComboBox();
		comboBoxListarCiudad.setEnabled(false);
		comboBoxListarCiudad
				.setModel(new DefaultComboBoxModel(new String[] { "Todas", "Tandil", "Olavarría", "Colectivo" }));
		comboBoxListarCiudad.setBounds(29, 74, 160, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarCiudad);

		JLabel lblEstado = new JLabel("Llenado");
		lblEstado.setBounds(471, 20, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblEstado);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(282, 20, 84, 16);
		internalFrameListarTubos.getContentPane().add(lblPropietario);

		JLabel lblUbicacin_1 = new JLabel("Ubicación");
		lblUbicacin_1.setBounds(38, 20, 109, 16);
		internalFrameListarTubos.getContentPane().add(lblUbicacin_1);

		textFieldListarTamano = new JTextField();
		textFieldListarTamano.setBounds(180, 118, 130, 26);
		internalFrameListarTubos.getContentPane().add(textFieldListarTamano);
		textFieldListarTamano.setColumns(10);

		JLabel lblTamao_1 = new JLabel("Tamaño");
		lblTamao_1.setBounds(86, 123, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblTamao_1);

		JLabel lblGas_1 = new JLabel("Gas");
		lblGas_1.setBounds(372, 123, 61, 16);
		internalFrameListarTubos.getContentPane().add(lblGas_1);

		textFieldListarGas = new JTextField();
		textFieldListarGas.setBounds(424, 118, 130, 26);
		internalFrameListarTubos.getContentPane().add(textFieldListarGas);
		textFieldListarGas.setColumns(10);

		JButton buttonListarBuscar = new JButton("Buscar");
		buttonListarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ph = "";
				String lleno = "";
				String propietario = "";
				String gas = "";
				String tamano = "";
				String ubicacion = "";
				String ciudad = "";
				String stock = "";
				List<String> list = new ArrayList<String>();

				if (!comboBoxListarEstado.getSelectedItem().equals("Todos")) {
					switch (comboBoxListarEstado.getSelectedItem().toString()) {
					case "Si":
						lleno = "lleno= 'si'";
						break;
					case "No":
						lleno = "lleno = 'no'";
						break;
					}
					list.add(lleno);
				}

				if (!comboBoxListarPropietario.getSelectedItem().equals("Todos")) {
					propietario = "propietario= '" + comboBoxListarPropietario.getSelectedItem().toString() + "'";
					list.add(propietario);
				}

				if (!comboBoxListarUbicacion.getSelectedItem().equals("Todas")) {
					ubicacion = "ubicacion= '" + comboBoxListarUbicacion.getSelectedItem() + "'";

					list.add(ubicacion);
					/*
					 * if
					 * (comboBoxListarUbicacion.getSelectedItem().equals("Stock"
					 * )){ stock =
					 * "nro_tubo= (SELECT nro_tubo FROM salidas WHERE fecha_devolucion IS NOT NULL)"
					 * ; list.add(stock);
					 * 
					 * } else if
					 * (comboBoxListarUbicacion.getSelectedItem().equals(
					 * "Cliente")){ stock =
					 * "nro_tubo= (SELECT nro_tubo FROM salidas WHERE fecha_devolucion IS NOT NULL)"
					 * ; list.add(stock);
					 * 
					 * }
					 */

				}

				if (!textFieldListarGas.getText().equals("")) {
					gas = "tipo_gas= '" + textFieldListarGas.getText() + "'";
					list.add(gas);
				}

				if (!textFieldListarTamano.getText().equals("")) {
					tamano = "tamanio= '" + textFieldListarTamano.getText() + "'";
					list.add(tamano);
				}

				String subq = "";
				String query = "";
				if (list.size() != 0) {
					subq = list.get(0);
					for (int i = 1; i < list.size(); i++)
						subq = subq + " AND " + list.get(i);
					query = "SELECT * FROM tubos WHERE " + subq + ";";
				} else
					query = "SELECT * FROM tubos;";

				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					tableListaTubos.setModel(DbUtils.resultSetToTableModel(rs));
					// tableListaTubos.set
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
		buttonListarBuscar.setBounds(249, 175, 117, 29);
		internalFrameListarTubos.getContentPane().add(buttonListarBuscar);

		comboBoxListarUbicacion = new JComboBox();
		comboBoxListarUbicacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (comboBoxListarUbicacion.getSelectedItem().equals("Prueba hidráulica"))
					comboBoxListarCiudad.setEnabled(true);
			}
		});
		comboBoxListarUbicacion
				.setModel(new DefaultComboBoxModel(new String[] { "Todas", "Stock", "Prueba hidráulica", "Cliente" }));
		comboBoxListarUbicacion.setBounds(29, 48, 160, 27);
		internalFrameListarTubos.getContentPane().add(comboBoxListarUbicacion);

		tablaMovTubos = new JTable();
		tableListaTubos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// String selection =
				// comboBoxTubos.getSelectedItem().toString();

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
		try {
			internalFrameInfoTubo.setClosed(true);
		} catch (PropertyVetoException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		internalFrameInfoTubo.setClosable(true);
		internalFrameInfoTubo.setResizable(true);
		internalFrameInfoTubo.setMaximizable(true);
		internalFrameInfoTubo.setBounds(10, 6, 623, 623);
		frame.getContentPane().add(internalFrameInfoTubo);
		internalFrameInfoTubo.getContentPane().setLayout(null);

		internalFrameLogin = new JInternalFrame("Login");
		try {
			internalFrameLogin.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		internalFrameLogin.setBounds(10, 6, 493, 300);
		frame.getContentPane().add(internalFrameLogin);
		internalFrameLogin.getContentPane().setLayout(null);
		internalFrameLogin.setVisible(true);

		internalFrameClientes = new JInternalFrame("Nuevo Cliente");
		internalFrameClientes.setClosable(true);
		try {
			internalFrameClientes.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameClientes.setEnabled(false);
		internalFrameClientes.setResizable(true);
		internalFrameClientes.setMaximizable(true);
		internalFrameClientes.setBounds(0, 6, 535, 385);
		frame.getContentPane().add(internalFrameClientes);
		internalFrameClientes.getContentPane().setLayout(null);

		internalFrameNuevaSalida = new JInternalFrame("Nueva Salida");
		internalFrameNuevaSalida.setClosable(true);
		try {
			internalFrameNuevaSalida.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameNuevaSalida.setResizable(true);
		internalFrameNuevaSalida.setBounds(10, 6, 623, 450);
		frame.getContentPane().add(internalFrameNuevaSalida);
		internalFrameNuevaSalida.getContentPane().setLayout(null);

		InternalFrameNuevaEntrada = new JInternalFrame("Nueva entrada");
		InternalFrameNuevaEntrada.setClosable(true);
		try {
			InternalFrameNuevaEntrada.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InternalFrameNuevaEntrada.setEnabled(false);
		InternalFrameNuevaEntrada.setResizable(true);
		InternalFrameNuevaEntrada.setMaximizable(true);
		InternalFrameNuevaEntrada.setBounds(0, 6, 535, 385);
		frame.getContentPane().add(InternalFrameNuevaEntrada);
		InternalFrameNuevaEntrada.getContentPane().setLayout(null);

		internalFrameNuevoTubo = new JInternalFrame("Nuevo/modificar tubo");
		internalFrameNuevoTubo.setClosable(true);
		try {
			internalFrameNuevoTubo.setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		internalFrameNuevoTubo.setMaximizable(true);
		internalFrameNuevoTubo.setResizable(true);
		internalFrameNuevoTubo.setEnabled(false);
		internalFrameNuevoTubo.setBounds(10, 6, 663, 282);
		frame.getContentPane().add(internalFrameNuevoTubo);
		internalFrameNuevoTubo.getContentPane().setLayout(null);

		// --------------------------- components ---------------------------------

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
				String user = textFieldUsuario.getText();
				String pass = textFieldContrasena.getText();
				try {
					LoginWorker loginWorker = new LoginWorker(connection, user, pass);
					loginWorker.execute();
					Boolean ok = loginWorker.get();

					if (!ok)
						JOptionPane.showMessageDialog(null,
								"El usuario y/o contraseña son incorrectos, por favor intenta nuevamente");
					else {
						internalFrameLogin.dispose();
						tipoUsuario = loginWorker.getTipo();
						usuario = loginWorker.getUsuario();
						menuBar.setEnabled(true);
						mnClientes.setEnabled(true);
						mnNotificaciones.setEnabled(true);
						mnTubos.setEnabled(true);
						mnUsuarios.setEnabled(true);
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
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

		/*
		 * textFieldAcondFecha = new JTextField();
		 * textFieldAcondFecha.setBounds(199, 108, 130, 26);
		 * internalFrameNuevoAcond.getContentPane().add(textFieldAcondFecha);
		 * textFieldAcondFecha.setColumns(10);
		 */

		// SqlDateModel model = new SqlDateModel();
		/*
		 * Properties p = new Properties(); p.put("text.today", "Today");
		 * p.put("text.month", "Month"); p.put("text.year", "Year");
		 */
		// JDatePanelImpl datePanelMov = new JDatePanelImpl(model, p);
		datePickerAcond = new JDatePickerImpl(datePanelMov, new DateLabelFormatter());
		datePickerAcond.setBounds(199, 108, 130, 26);
		internalFrameNuevoAcond.getContentPane().add(datePickerAcond);

		comboBoxAcond = new JComboBox();
		comboBoxAcond.setModel(new DefaultComboBoxModel(
				new String[] { "Llenado", "Prueba hidráulica", "Cambio de válvula", "Tubo rechazado" }));
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
					String query = "INSERT INTO acondicionamientos (id_acond, fecha, nro_tubo, tipo, observaciones) VALUES (?,?,?,?,?);";
					PreparedStatement pst = connection.prepareStatement(query);
					int row = getRow("acondicionamientos") + 1;
					pst.setString(1, String.valueOf(row));
					pst.setString(2, datePickerAcond.getModel().getValue().toString());
					pst.setString(3, textFieldAcondTubo.getText());
					pst.setString(4, comboBoxAcond.getSelectedItem().toString());
					pst.setString(5, textPaneAcondObservaciones.getText());

					pst.execute();
					pst.close();

					if (comboBoxAcond.getSelectedItem().toString().toLowerCase().equals("llenado")) {
						String query1 = "UPDATE tubos SET lleno='si' where nro_tubo='" + textFieldAcondTubo.getText()
								+ "';";
						PreparedStatement pst1 = connection.prepareStatement(query1);
						pst1.execute();
						pst1.close();
					}

					if (comboBoxAcond.getSelectedItem().toString().toLowerCase().equals("prueba hidráulica")) {
						query = "UPDATE tubos SET lleno='no', ubicacion='ph' where nro_tubo='"
								+ textFieldAcondTubo.getText() + "';";
						PreparedStatement pst1 = connection.prepareStatement(query);
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
		btnAceptar.setBounds(199, 246, 117, 29);
		internalFrameNuevoAcond.getContentPane().add(btnAceptar);

		textPaneAcondObservaciones = new JTextPane();
		textPaneAcondObservaciones.setBounds(199, 146, 130, 55);
		internalFrameNuevoAcond.getContentPane().add(textPaneAcondObservaciones);

		JLabel lblObservaciones_2 = new JLabel("Observaciones");
		lblObservaciones_2.setBounds(21, 168, 99, 16);
		internalFrameNuevoAcond.getContentPane().add(lblObservaciones_2);

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

		comboBoxSalidaCliente = new JComboBox();
		comboBoxSalidaCliente.setBounds(169, 64, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(comboBoxSalidaCliente);
		getClientes(comboBoxSalidaCliente);

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

		/*
		 * textFieldSalidaFecha = new JTextField();
		 * textFieldSalidaFecha.setBounds(169, 127, 130, 26);
		 * internalFrameNuevaSalida.getContentPane().add(textFieldSalidaFecha);
		 * textFieldSalidaFecha.setColumns(10);
		 */

		datePickerSalida = new JDatePickerImpl(datePanelMov, new DateLabelFormatter());
		datePickerSalida.setBounds(169, 127, 130, 26);
		internalFrameNuevaSalida.getContentPane().add(datePickerSalida);

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
		comboBoxSalidaFlete.setModel(new DefaultComboBoxModel(new String[] { "Si", "No" }));
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
					String cliente = comboBoxSalidaCliente.getSelectedItem().toString();
					pst.setString(1, String.valueOf(row));
					pst.setString(2, datePickerSalida.getModel().getValue().toString());
					pst.setString(3, textFieldSalidaComprobante.getText());
					pst.setString(4, textFieldSalidaEmpleado.getText());

					pst.setString(5, comboBoxSalidaFlete.getSelectedItem().toString());
					pst.setString(6, textFieldSalidaMonto.getText());
					pst.setString(7, textPaneSalidaObservaciones.getText());
					pst.setString(8, textFieldSalidaNroTubo.getText());
					pst.setString(9, cliente.substring(cliente.indexOf(", ")+2));
					pst.execute();
					pst.close();

					query = "UPDATE tubos SET lleno='no', ubicacion='cliente' where nro_tubo='"
							+ textFieldSalidaNroTubo.getText() + "';";
					PreparedStatement pst1 = connection.prepareStatement(query);
					pst1.execute();
					pst1.close();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// internalFrameNuevoTubo.dispose();
				// internalFrameNuevoTubo.setVisible(false);

				// clearDataNuevEntrada();
				internalFrameNuevaSalida.setVisible(false);
			}
		});
		btnNewButtonSalidaOk.setIcon(new ImageIcon("/Users/cristianmerlo/zamolo/Ok.png"));
		btnNewButtonSalidaOk.setBounds(417, 26, 61, 29);
		internalFrameNuevaSalida.getContentPane().add(btnNewButtonSalidaOk);

		JButton btnCancelar_2 = new JButton("Cancelar");
		btnCancelar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// internalFrameNuevaSalida.setVisible(false);
				internalFrameNuevaSalida.dispose();
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
		comboBoxUbicacion.setModel(new DefaultComboBoxModel(new String[] { "Tandil", "Olavarria", "Colectivo" }));
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
		comboBoxGrupo.setModel(new DefaultComboBoxModel(new String[] { "1", "4" }));
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
				// clearDataNuevoTubo();
			}
		});
		btnCancelarCliente.setBounds(302, 266, 117, 29);
		internalFrameClientes.getContentPane().add(btnCancelarCliente);
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = "Se va a agregar el siguiente cliente \n Nombre: "
						+ textFieldClientesNombre.getText().toString() + "\n Dirección: "
						+ textFieldClientesDir.getText() + "\n Código postal: " + textFieldClientesCod.getText()
						+ "\n Ubicación: " + comboBoxUbicacion.getSelectedItem().toString() + "\n Teléfono: "
						+ textFieldTelefono.getText() + "\n Cuenta: " + textFieldClientesCuenta.getText() + "\n Grupo: "
						+ comboBoxGrupo.getSelectedItem().toString();
				int action = JOptionPane.showConfirmDialog(null, message, "Agregar cliente",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == 0) {

					try {
						String query = "INSERT INTO clientes (id_cliente, nombre_y_apellido, direccion, telefono, codigo_postal, ubicacion, id_cuenta, grupo_clientes) VALUES (?,?,?,?,?,?,?,?);";
						PreparedStatement pst = connection.prepareStatement(query);
						int row = getRow("clientes") + 1;
						pst.setString(1, String.valueOf(row));
						pst.setString(2, textFieldClientesNombre.getText());
						pst.setString(3, textFieldClientesDir.getText());
						pst.setString(4, textFieldTelefono.getText());
						pst.setString(5, textFieldClientesCod.getText());
						pst.setString(6, comboBoxUbicacion.getSelectedItem().toString());
						pst.setString(7, textFieldClientesCuenta.getText()); // checkear
																				// que
																				// nro
																				// de
																				// cuenta
																				// sea
																				// unica
						pst.setString(8, comboBoxGrupo.getSelectedItem().toString());
						pst.execute();
						pst.close();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// }
					// internalFrameNuevoTubo.dispose();
					// internalFrameNuevoTubo.setVisible(false);
					// clearDataNuevoTubo();
					internalFrameClientes.setVisible(false);
					// updateTableTubos();
				}
			}
		});

		/*
		 * textFieldEntradaFecha = new JTextField();
		 * textFieldEntradaFecha.setBounds(166, 82, 134, 28);
		 * InternalFrameNuevaEntrada.getContentPane().add(textFieldEntradaFecha)
		 * ; textFieldEntradaFecha.setColumns(10);
		 */

		datePickerEntrada = new JDatePickerImpl(datePanelMov, new DateLabelFormatter());
		datePickerEntrada.setBounds(166, 82, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(datePickerEntrada);

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

		comboBoxEntradaCliente = new JComboBox();
		comboBoxEntradaCliente.setBounds(166, 49, 134, 28);
		InternalFrameNuevaEntrada.getContentPane().add(comboBoxEntradaCliente);
		getClientes(comboBoxEntradaCliente);
		

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
		comboBoxMotivo.setModel(new DefaultComboBoxModel(new String[] { "Llenado", "Prueba Hidráulica" }));
		comboBoxMotivo.setBounds(166, 175, 134, 27);
		InternalFrameNuevaEntrada.getContentPane().add(comboBoxMotivo);

		JLabel lblFlete = new JLabel("Flete");
		lblFlete.setBounds(24, 207, 61, 16);
		InternalFrameNuevaEntrada.getContentPane().add(lblFlete);

		comboBoxFlete = new JComboBox();
		comboBoxFlete.setModel(new DefaultComboBoxModel(new String[] { "Si", "No" }));
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

				if (isTuboAdded(textFieldEntradaNroTubo.getText().toString())) {
					String message = "Se va a agregar la siguiente entrada: \n numero de tubo: "
							+ textFieldEntradaNroTubo.getText().toString() + "\n cliente: "
							+ comboBoxEntradaCliente.getSelectedItem().toString() + "\n fecha: "
							+ datePickerEntrada.getModel().getValue().toString() + "\n empleado: "
							+ textFieldEmpleado.getText() + "\n motivo: " + comboBoxMotivo.getSelectedItem().toString()
							+ "\n flete: " + comboBoxFlete.getSelectedItem().toString() + "\n monto total: "
							+ textFieldMonto.getText();
					int action = JOptionPane.showConfirmDialog(null, message, "Agregar entrada",
							JOptionPane.OK_CANCEL_OPTION);
					if (action == 0) {
						try {
							String query = "INSERT INTO entradas (id_entrada, fecha, nro_comprobante, empleado, motivo, flete, monto_total, observaciones, nro_tubo, cliente) VALUES (?,?,?,?,?,?,?,?,?,?);";
							PreparedStatement pst = connection.prepareStatement(query);
							int row = getRow("entradas") + 1;
							String cliente = comboBoxEntradaCliente.getSelectedItem().toString();
							pst.setString(1, String.valueOf(row));
							pst.setString(2, datePickerEntrada.getModel().getValue().toString());
							pst.setString(3, textFieldEntradaComprobante.getText());
							pst.setString(4, textFieldEmpleado.getText());
							pst.setString(5, comboBoxMotivo.getSelectedItem().toString());
							pst.setString(6, comboBoxFlete.getSelectedItem().toString());
							pst.setString(7, textFieldMonto.getText());
							pst.setString(8, textAreaObservaciones.getText());
							pst.setString(9, textFieldEntradaNroTubo.getText());
							pst.setString(10, cliente.substring(cliente.indexOf(", ")+2));
							pst.execute();
							pst.close();

							String llenado = "";
							if (comboBoxMotivo.getSelectedItem().toString() == "llenado") {
								String query1 = "UPDATE tubos SET lleno='no' WHERE nro_tubo='"
										+ textFieldEntradaNroTubo.getText() + "';";
								PreparedStatement pst1;

								pst1 = connection.prepareStatement(query1);
								pst1.execute();
								pst1.close();
							}
							String query1 = "UPDATE salidas SET fecha_devolucion='"
									+ datePickerEntrada.getModel().getValue().toString() + "' WHERE nro_tubo='"
									+ textFieldEntradaNroTubo.getText() + "';";
							PreparedStatement pst1;

							pst1 = connection.prepareStatement(query1);
							pst1.execute();
							pst1.close();

							query = "UPDATE tubos SET lleno='no', ubicacion='stock' where nro_tubo='"
									+ textFieldEntradaNroTubo.getText() + "';";
							PreparedStatement pst2 = connection.prepareStatement(query);
							pst2.execute();
							pst2.close();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						clearDataNuevEntrada();
						InternalFrameNuevaEntrada.setVisible(false);
						// updateTableTubos();
					}
				} else {
					int actionB = JOptionPane.showConfirmDialog(null, "El número de tubo no existe, desea agregarlo?",
							"Error", JOptionPane.OK_CANCEL_OPTION);
					if (actionB == 0) {
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
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternalFrameNuevaEntrada.dispose();
			}
		});

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
		lblNewLabel_1.setBounds(344, 25, 96, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_1);

		textFieldGas = new JTextField();
		textFieldGas.setBounds(452, 19, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldGas);
		textFieldGas.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tamaño");
		lblNewLabel_2.setBounds(28, 65, 61, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_2);

		textFieldTamano = new JTextField();
		textFieldTamano.setBounds(145, 59, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldTamano);
		textFieldTamano.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Propietario");
		lblNewLabel_3.setBounds(344, 65, 109, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_3);

		textFieldPropietario = new JTextField();
		textFieldPropietario.setBounds(452, 59, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(textFieldPropietario);
		textFieldPropietario.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ubicación");
		lblNewLabel_4.setBounds(28, 105, 109, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNewLabel_4);

		comboBoxNuevoTubo = new JComboBox();
		comboBoxNuevoTubo.setModel(new DefaultComboBoxModel(new String[] { "Stock", "Prueba Hidráulica", "Cliente" }));
		// textFieldUbicacion = new JTextField();
		comboBoxNuevoTubo.setBounds(145, 100, 134, 28);
		internalFrameNuevoTubo.getContentPane().add(comboBoxNuevoTubo);
		// comboBoxNuevoTubo.setColumns(10);

		JButton btnAgregarTubo = new JButton("");
		btnAgregarTubo.setIcon(new ImageIcon("/Users/cristianmerlo/Downloads/Ok.png"));
		btnAgregarTubo.setBounds(174, 183, 117, 29);
		internalFrameNuevoTubo.getContentPane().add(btnAgregarTubo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameNuevoTubo.setVisible(false);
				clearDataNuevoTubo();
			}
		});
		btnCancelar.setBounds(309, 183, 117, 29);
		internalFrameNuevoTubo.getContentPane().add(btnCancelar);

		lblNmeroAlternativo = new JLabel("Número alternativo");
		lblNmeroAlternativo.setBounds(327, 105, 126, 16);
		internalFrameNuevoTubo.getContentPane().add(lblNmeroAlternativo);

		textFieldNroAltern = new JTextField();
		textFieldNroAltern.setBounds(452, 100, 134, 26);
		internalFrameNuevoTubo.getContentPane().add(textFieldNroAltern);
		textFieldNroAltern.setColumns(10);
		btnAgregarTubo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (isTuboAdded(textFieldNroTubo.getText())) {
					String message = "Se va a modificar el siguiente tubo \n numero de tubo: "
							+ textFieldNroTubo.getText().toString() + "\n Desea continuar?";
					int action = JOptionPane.showConfirmDialog(null, message, "Modificar tubo",
							JOptionPane.OK_CANCEL_OPTION);
					if (action == 0) {
						try {
							String query = "UPDATE tubos SET tipo_gas='" + textFieldGas.getText() + "', tamanio='"
									+ textFieldTamano.getText() + "', propietario='" + textFieldPropietario.getText()
									+ "', ubicacion='" + comboBoxNuevoTubo.getSelectedItem().toString()
									+ ", nro_altern='" + textFieldNroAltern.getText() + "' WHERE nro_tubo='"
									+ textFieldNroTubo.getText() + "';";
							PreparedStatement pst = connection.prepareStatement(query);

							pst.execute();
							pst.close();
							internalFrameNuevoTubo.dispose();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {

					String message = "Se va a agregar el siguiente tubo \n numero de tubo: "
							+ textFieldNroTubo.getText().toString() + "\n gas: " + textFieldGas.getText()
							+ "\n Tamaño: " + textFieldTamano.getText() + "\n propietario: "
							+ textFieldPropietario.getText() + "\n ubicacion: "
							+ comboBoxNuevoTubo.getSelectedItem().toString() + "\n nro alternativo: "
							+ textFieldNroAltern.getText();

					int action = JOptionPane.showConfirmDialog(null, message, "Agregar tubo",
							JOptionPane.OK_CANCEL_OPTION);
					if (action == 0) {

						try {
							String query = "INSERT INTO tubos (nro_tubo, tipo_gas, tamanio, propietario, ubicacion) VALUES (?,?,?,?,?,?);";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, textFieldNroTubo.getText());
							pst.setString(2, textFieldGas.getText());
							pst.setString(3, textFieldTamano.getText());
							pst.setString(4, textFieldPropietario.getText());
							pst.setString(5, comboBoxNuevoTubo.getSelectedItem().toString());
							pst.setString(6, ", nro_altern='" + textFieldNroAltern.getText());
							pst.execute();
							pst.close();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					internalFrameNuevoTubo.dispose();
					// updateTableTubos();
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

				// updateTableEntradas();
			}
		});

		/*
		 * btnActualizarTubos.addActionListener(new ActionListener() { public
		 * void actionPerformed(ActionEvent e) { try {
		 * 
		 * String selection = new String(); switch
		 * ((String)comboBoxFilter.getSelectedItem()){ case "Todos": selection =
		 * null; break; case "Número": selection = "nro_tubo"; break; case
		 * "Gas": selection = "tipo_gas"; break; case "Tamaño": selection =
		 * "tamanio"; break; case "Propietario": selection = "propietario";
		 * break;
		 * 
		 * } String query = new String(); PreparedStatement pst; if
		 * (selection!=null) { query = "select * from tubos where " + selection
		 * + "=? "; pst = connection.prepareStatement(query); pst.setString(1,
		 * txtBsqueda.getText()); } else { query = "select * from tubos"; pst =
		 * connection.prepareStatement(query); } ResultSet rs =
		 * pst.executeQuery();
		 * tablaTubos.setModel(DbUtils.resultSetToTableModel(rs)); } catch
		 * (Exception e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } } });
		 */

		// updateTable();
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
							JOptionPane.showMessageDialog(null,
									"El número de tubo seleccionado no existe, intente nuevamente");
						else {

							textFieldBuscarGas.setText(rs.getString(2));
							textFieldBuscarTamano.setText(rs.getString(3));
							textFieldBuscarPropietario.setText(rs.getString(4));
							textFieldBuscarUbicacion.setText(rs.getString(6));
							textFieldBuscarLleno.setText(rs.getString(5));

							// ver tubo en tabla
							String query1 = "select nro_tubo, cliente, fecha, nro_comprobante, empleado from entradas where nro_tubo="
									+ textFieldBuscarNroTubo.getText()
									+ " UNION ALL SELECT nro_tubo, cliente, fecha, nro_comprobante, empleado FROM salidas where nro_tubo="
									+ textFieldBuscarNroTubo.getText() + ";";
							PreparedStatement pst1 = connection.prepareStatement(query1);
							ResultSet rs1 = pst1.executeQuery();

							tablaMovTubos.setModel(DbUtils.resultSetToTableModel(rs1));

							String query2 = "select fecha, tipo from acondicionamientos where nro_tubo="
									+ textFieldBuscarNroTubo.getText() + ";";
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
