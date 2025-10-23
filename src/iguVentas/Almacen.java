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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import IguLogin.PantallaLogin;
import IguTablaUsuarios.CrearTablaUser;
import Logica.CrudAlmacen;

@SuppressWarnings("serial")
public class Almacen extends JFrame {

	JLabel lblMensaje, lblHora, lblCorreo, lblRol, totalLbl;
	JPanel PanelEntrada, PanelInferior, panelBotones;
	JTextField fldProducto, fldStock, EstatusField, FieldProvee, FieldPreVenta, FieldFechIngreso, FldDescripcion;
	JButton btnLogOut, btnRegreso;
	DefaultTableModel modelo;
	JTable tablaTienda;
	JScrollPane scroll;
	JTextArea TxtAreaDescripcion;
	JScrollPane scrollDescripcion ;

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

		JButton btnAdd = new JButton(" ✅ Agregar");
		btnAdd.setBackground(new Color(70, 130, 180)); // Azul acero
		btnAdd.setForeground(Color.YELLOW);// color de la letra
		btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		btnAdd.setMaximumSize(new Dimension(20, 30));
		PantallaLogin.cambioVisualBoton(btnAdd);

		btnRegreso = new JButton("<--Regresar");
		btnRegreso.setBackground(new Color(70, 130, 180)); // Azul acero
		btnRegreso.setForeground(Color.YELLOW);// color de la letra
		btnRegreso.setFont(new Font("Arial", Font.BOLD, 12));
		btnRegreso.setFocusPainted(false);
		btnRegreso.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnRegreso);

		// agregar el panel botones dentro del panel entrada
		panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBotones.add(btnAdd);
		panelBotones.add(btnRegreso);

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
		//PanelEntrada.add(FldDescripcion);
		PanelEntrada.add(scrollDescripcion);

		PanelEntrada.add(panelBotones);

		// Tabla
		modelo = new DefaultTableModel(new String[] {  "Producto", "stock ", "status ", "Costo",
				"Costo Venta", "fecha Ingreso", "Descripción ", "RECIBE" }, 0);
		tablaTienda = new JTable(modelo);
		int poss = 0;
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(30); // índice 3 = columna "fecha cambia el tamaño
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(6); // índice 3 = columna "fecha cambia el tamaño
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(6); // índice 4 = columna correo
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(8); // índice 5 = columna estatus"
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(20); // índice 6 = columna "app
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(60); // índice 6 = columna "rol
		tablaTienda.getColumnModel().getColumn(poss++).setPreferredWidth(100); // índice 6 = columna "rol
		
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
	}
	
	public static  void btnRegresar(JButton tbnRegreso,String correo,String  rol) {
		tbnRegreso.addActionListener(e -> {
			Window ventanaProductos = SwingUtilities.getWindowAncestor(tbnRegreso);
			ventanaProductos.dispose();
			CrearTablaUser tablaUser = new CrearTablaUser();
			tablaUser.creaVentana(correo, rol);
			
		});
	}

	public void  BtnAgregar(JButton btnAgregar, DefaultTableModel modeloTabla) {

		btnAgregar.addActionListener(e -> {
			
			List<String> ListProductos = new ArrayList<>();
			
			if (fldProducto.getText().isEmpty() || fldStock.getText().isEmpty() 
					|| FieldProvee.getText().isEmpty() || TxtAreaDescripcion.getText().isEmpty()) {

				JOptionPane.showMessageDialog(this, "Completa  Todos Los Campos");

			} else {
				TiendaGUI.mostrarMensaje3Seg(lblMensaje);

				String producto = fldProducto.getText();
				String Stock = fldStock.getText();
				String estatus = ("Activo");
				String precioProv  = FieldProvee.getText();
				float precioVen  = (float) (Float.valueOf(precioProv) + (Float.valueOf(precioProv) * 0.28)) ;
				String FechIngreso = lblHora.getText();
				String preVenta = String.valueOf(precioVen);// regresamos  a string
				String descripcion  = TxtAreaDescripcion.getText().toString();
				String recibe = lblCorreo.getText();
				String datosEntrada [] = { producto,Stock,estatus,precioProv,preVenta,FechIngreso,descripcion,recibe};

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
				
				CrudAlmacen.CrudAddAlmacen(ListProductos);
			}
		});
	}

	
	
	
	
	
	
	
}