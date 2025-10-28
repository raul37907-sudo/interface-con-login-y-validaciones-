package iguVentas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import IguLogin.AlertaAnimada;
import IguLogin.PantallaLogin;
import IguTablaUsuarios.CrearTablaUser;
import Logica.CrudAlmacen;
import Persistencia.ConexionDBMysql;

@SuppressWarnings("serial")
public class Almacen extends JFrame {

	JLabel lblMensaje, lblHora, lblCorreo, lblRol, totalLbl;
	JPanel PanelEntrada, PanelInferior, panelBotones, panel2Botones;
	JTextField fldProducto, fldStock, EstatusField, FieldProvee, FieldPreVenta, FieldFechIngreso, FldDescripcion;
	JButton btnLogOut, btnAdd, btnRegreso, btnBuscar, btnModificar, btnCancelar, btnActualizar;
	DefaultTableModel modelo;
	JTable tablaTienda;
	JScrollPane scroll;
	JTextArea TxtAreaDescripcion;
	JScrollPane scrollDescripcion;

	public Almacen(String correo, String rol) {
		this.setTitle("🛒 Tienda - Almacen");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // al precionar el boton se cerrar cierra el program a por
		setResizable(false); // 🔒 Desactiva el botón de maximizar
		setLocationRelativeTo(null); // 🎯 Centra la ventana
		ComponentesAlmacen(correo, rol);

	}

	public void ComponentesAlmacen(String correo, String rol) {

		lblMensaje = new JLabel("Se Agrego Con Exito");
		lblMensaje.setBackground(new Color(70, 130, 180)); // Azul acero
		lblMensaje.setForeground(Color.BLUE);// color de la letra
		lblMensaje.setFont(new Font("Arial", Font.BOLD, 12));
		lblMensaje.setVisible(false);
		lblHora = new JLabel(TiendaGUI.obtenerFechaHoraActual());
		lblCorreo = new JLabel(correo);
		lblRol = new JLabel(rol);

		// ⏱️ Timer que actualiza cada segundo
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHora.setText(TiendaGUI.obtenerFechaHoraActual());
			}
		});
		timer.start();

		// Panel de entrada panel central
		PanelEntrada = new JPanel(new GridLayout(8, 2, 6, 6)); // 8 filas, 2 columnas, con espacio nombreField = new
																// JTextField();
		fldStock = new JTextField();
		EstatusField = new JTextField();
		FieldProvee = new JTextField();
		FieldPreVenta = new JTextField();
		fldProducto = new JTextField();
		FieldFechIngreso = new JTextField();
		FldDescripcion = new JTextField();
		TxtAreaDescripcion = new JTextArea (1,20);
	    scrollDescripcion = new JScrollPane(TxtAreaDescripcion);

		btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(new Color(70, 130, 180)); // Azul acero
		btnLogOut.setForeground(Color.YELLOW);// color de la letra
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 12));
		btnLogOut.setFocusPainted(false);
		btnLogOut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnLogOut);

		btnAdd = new JButton(" ✅ Agregar");
		btnAdd.setBackground(new Color(70, 130, 180)); // Azul acero
		btnAdd.setForeground(Color.YELLOW);// color de la letra
		btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		btnAdd.setMaximumSize(new Dimension(20, 30));
		PantallaLogin.cambioVisualBoton(btnAdd);

		btnRegreso = new JButton("Regresar");
		btnRegreso.setBackground(new Color(70, 130, 180)); // Azul acero
		btnRegreso.setForeground(Color.YELLOW);// color de la letra
		btnRegreso.setFont(new Font("Arial", Font.BOLD, 12));
		btnRegreso.setFocusPainted(false);
		btnRegreso.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnRegreso);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnBuscar.setForeground(Color.YELLOW);// color de la letra
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnModificar.setForeground(Color.YELLOW);// color de la letra
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setFocusPainted(false);
		btnModificar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnCancelar.setForeground(Color.YELLOW);// color de la letra
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnCancelar);
		btnCancelar.setVisible(false);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnActualizar.setForeground(Color.YELLOW);// color de la letra
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnActualizar);
		btnActualizar.setVisible(false);

		// agregar el panel botones dentro del panel entrada
		panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBotones.add(btnAdd);
		panelBotones.add(btnBuscar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnRegreso);
		
		panel2Botones = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		panel2Botones.add(btnActualizar);
		panel2Botones.add(btnCancelar);
		
		 


		// 🔹 20 píxeles arriba, 10 izquierda, 10 abajo, 10 derecha
		PanelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		PanelEntrada.add(new JLabel(" "));
		PanelEntrada.add(lblMensaje);
		
		PanelEntrada.add(new JLabel("                                                Nombre del  Producto:"));
		PanelEntrada.add(fldProducto);

		PanelEntrada.add(new JLabel("                                                Stock De Entrada:"));
		PanelEntrada.add(fldStock);

		PanelEntrada.add(new JLabel("                                                Precio proveedor:"));
		PanelEntrada.add(FieldProvee);

		PanelEntrada.add(new JLabel("                                                Descripción Producto:"));
		PanelEntrada.add(scrollDescripcion);

		PanelEntrada.add(panelBotones);
		PanelEntrada.add(panel2Botones);

		// Tabla
		modelo = new DefaultTableModel(new String[] { "Id", "Producto", "stock ", "status ", "Costo",
				"Costo Venta", "fecha Ingreso", "Descripción ", "RECIBE" }, 0);
		tablaTienda = new JTable(modelo);
		tablaTienda.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tablaTienda.getColumnCount(); i++) {
		    tablaTienda.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}
		
		int poss = 0;
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(20);
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(110); // índice 3 = columna "fecha cambia el tamaño
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(40); // índice 3 = columna "fecha cambia el tamaño
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(40); // índice 4 = columna correo
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(40); // índice 5 = columna estatus"
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(70); // índice 6 = columna "app
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(130); // fecha de oingreso 
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(180); // índice 6 = columna "rol
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(120); // índice 6 = columna "rol
		
	
		
		scroll = new JScrollPane(tablaTienda);
		JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		panelSuperior.add(lblCorreo);
		panelSuperior.add(lblRol);
		panelSuperior.add(lblHora);
		panelSuperior.add(btnLogOut);
		panelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));

		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.add(PanelEntrada, BorderLayout.SOUTH);

		
		BtnAgregar(btnAdd,modelo);
		btnRegresar(btnRegreso,correo,rol);
		CrearTablaUser. btncerrarSesion (btnLogOut);
		btbBusqueda (btnBuscar,modelo);
		btbmodificarLlenarTextField (btnModificar,tablaTienda);
		btnactualizar(btnActualizar,tablaTienda,modelo);
		btnCancelar(btnCancelar);
		try {
			obtenerProductos(modelo);
		} catch (SQLException e1) {
			System.out.println(" No Tienes Productos Para Mostrar" + e1.getMessage());
		} 
	}

	public void btnCancelar(JButton btnCancelar) {

		btnCancelar.addActionListener(e -> {

			fldProducto.setText("");
			fldStock.setText("");
			FieldProvee.setText("");
			TxtAreaDescripcion.setText("");
			btnActualizar.setVisible(false);
			btnCancelar.setVisible(false);
			btnAdd.setVisible(true);
			btnRegreso.setVisible(true);


		});

	}

	public void btnactualizar(JButton btnActualiza,JTable tablaEnUso,DefaultTableModel modelo) {

		btnActualiza.addActionListener(e -> {

			int filaSeleccionada = tablaEnUso.getSelectedRow();
			if (filaSeleccionada == -1) {

				AlertaAnimada.mostrarAlertaAnimada("Selecciona una  Fila");
			} else {
				
				String producto = fldProducto.getText();
				String stock = fldStock.getText();
				String precioProv = FieldProvee.getText();
				float precioVen = (float) (Float.valueOf(precioProv) + (Float.valueOf(precioProv) * 0.28));
				String preVenta = String.valueOf(precioVen);// regresamos a string
				String fechaModifica = lblHora.getText();
				String descripcion = TxtAreaDescripcion.getText().toString();
				String userModofica = lblCorreo.getText();
				String rolModofica = lblRol.getText();
				String id = tablaEnUso.getValueAt(filaSeleccionada, 0).toString(); //1 producto

				String datosEntrada[] = { producto, stock, precioProv, preVenta, descripcion,
						userModofica, rolModofica,fechaModifica,id };
				
				CrudAlmacen.modificarAlmacen(datosEntrada,modelo);
				
				fldProducto.setText(producto);
				fldStock.setText(stock);
				FieldProvee.setText(precioProv);
				TxtAreaDescripcion.setText(descripcion);

			}
			
			fldProducto.setText("");
			fldStock.setText("");
			FieldProvee.setText("");
			TxtAreaDescripcion.setText("");
			btnActualizar.setVisible(false);
			btnCancelar.setVisible(false);
			btnRegreso.setVisible(true);
			btnAdd.setVisible(true);

		});

	}

	public void btbmodificarLlenarTextField(JButton btnModificar, JTable tablaEnUso) {

		btnModificar.addActionListener(e -> {
			int filaSeleccionada = tablaEnUso.getSelectedRow();
			if (filaSeleccionada == -1) {

				AlertaAnimada.mostrarAlertaAnimada("Selecciona una  Fila");
			} else {
				String producto = tablaEnUso.getValueAt(filaSeleccionada, 1).toString(); //1 producto
				String stock = tablaEnUso.getValueAt(filaSeleccionada, 2).toString();//2 stock
				String costoCompra = tablaEnUso.getValueAt(filaSeleccionada,4).toString();//4 costo compra
				String descripcion = tablaEnUso.getValueAt(filaSeleccionada,7).toString(); //7 descripción 

				
				
				fldProducto.setText(producto);
				fldStock.setText(stock);
				FieldProvee.setText(costoCompra);
				TxtAreaDescripcion.setText(descripcion);

				
				btnActualizar.setVisible(true);
				btnCancelar.setVisible(true);
				btnAdd.setVisible(false);
				btnRegreso.setVisible(false);

			}

		});
	}

	public static void btbBusqueda(JButton btnBuscar, DefaultTableModel modelo) {
		btnBuscar.addActionListener(e -> {
			String buscarProducto = JOptionPane.showInputDialog("Ingresa Nombre Del Producto");

			if (buscarProducto != null && !buscarProducto.trim().isEmpty()) {
				List<Object[]> resultados = CrudAlmacen.CrudBuscarEnAlmacen(buscarProducto);
				modelo.setRowCount(0); // Limpia la tabla

				if (!resultados.isEmpty()) {
					for (Object[] fila : resultados) {
						modelo.addRow(fila);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se encontraron productos similares.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No se ingresó ningún nombre.");
			}
		});

	}

	public static void obtenerProductos(DefaultTableModel modelo) throws SQLException {
		Connection conectarDB = ConexionDBMysql.getInstancia().getConnection();
		Statement stmtUsuarios = conectarDB.createStatement();
		ResultSet ResultadoRs = stmtUsuarios.executeQuery(" SELECT * FROM Producto "); // obtiene todos los campos de la

		while (ResultadoRs.next()) {
			Object[] fila = {

					ResultadoRs.getString("id_Producto"), // ResultadoRs.getString("pass"),no se agrega ni id
					ResultadoRs.getString("producto"), ResultadoRs.getInt("stock"), ResultadoRs.getString("estatus"),
					ResultadoRs.getBigDecimal("precio_Proveedor"), ResultadoRs.getBigDecimal("Precio_Venta"),
					ResultadoRs.getTimestamp("fecha_Ingreso"), ResultadoRs.getString("Descripción"),
					ResultadoRs.getString("Usuario_recibe") };
			modelo.addRow(fila); // ya
		}
		ResultadoRs.close();
		stmtUsuarios.close();
	}

	public static void btnRegresar(JButton tbnRegreso, String correo, String rol) {
		tbnRegreso.addActionListener(e -> {
			Window ventanaProductos = SwingUtilities.getWindowAncestor(tbnRegreso);
			ventanaProductos.dispose();
			CrearTablaUser tablaUser = new CrearTablaUser();
			tablaUser.creaVentana(correo, rol);

		});
	}

	public void BtnAgregar(JButton btnAgregar, DefaultTableModel modeloTabla) {

		btnAgregar.addActionListener(e -> {

			List<String> ListProductos = new ArrayList<>();

			if (fldProducto.getText().isEmpty() || fldStock.getText().isEmpty() || FieldProvee.getText().isEmpty()
					|| TxtAreaDescripcion.getText().isEmpty()) {

				JOptionPane.showMessageDialog(this, "Completa  Todos Los Campos");

			} else {
				TiendaGUI.mostrarMensaje3Seg(lblMensaje);

				String producto = fldProducto.getText();
				String Stock = fldStock.getText();
				String estatus = ("1"); // "Activo" no funciona
				String precioProv = FieldProvee.getText();
				float precioVen = (float) (Float.valueOf(precioProv) + (Float.valueOf(precioProv) * 0.28));
				String preVenta = String.valueOf(precioVen);// regresamos a string
				String FechIngreso = lblHora.getText();
				String descripcion = TxtAreaDescripcion.getText().toString();
				String recibe = lblCorreo.getText();
				String puesto_recibe = lblRol.getText();
				String datosEntrada[] = { producto, Stock, estatus, precioProv, preVenta, FechIngreso, descripcion,
						recibe, puesto_recibe };

				modeloTabla.addRow(datosEntrada);
				fldProducto.setText("");
				fldStock.setText("");
				EstatusField.setText("");
				FieldProvee.setText("");
				FieldPreVenta.setText("");
				TxtAreaDescripcion.setText("");

				ListProductos.add(producto);
				ListProductos.add(Stock);
				ListProductos.add(estatus);
				ListProductos.add(precioProv);
				ListProductos.add(preVenta);
				ListProductos.add(FechIngreso);
				ListProductos.add(descripcion);
				ListProductos.add(recibe);
				ListProductos.add(puesto_recibe);

				CrudAlmacen.CrudAddAlmacen(ListProductos);
			}
		});
	}

}