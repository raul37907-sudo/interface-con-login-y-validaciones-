package iguVentas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.awt.event.ActionListener;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import IguLogin.PantallaLogin;
import IguTablaUsuarios.CrearTablaUser;

@SuppressWarnings("serial")
public class TiendaGUI extends JFrame {

	private JPanel PanelEntrada, PanelInferior;

	private JTextField nombreField, precioField, cantidadField;
	private JTable tablaTienda;
	private DefaultTableModel modelo;
	private JLabel totalLbl, lblHora, lblCorreo, lblRol, lblMensaje;

	private JButton btnLogOut;

	JScrollPane scroll;

	private Map<Integer, Producto> productos = new HashMap<>(); // Integer Objeto (clase envolvente de int) ✅ Sí se
																// puede usar como clave
	private int contadorId = 1;

	public TiendaGUI(String correo, String rol) {
		ComponentesVentas(correo, rol);
	}

//	public enum ModoVista { / para  poder mandar  los  modos  de configuracion 
//		VENTA, ALMACEN
//	}

	public void ComponentesVentas(String correo, String rol) {
		this.setTitle("🛒 Tienda - Gestión y Cobros");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // al precionar el boton se cerrar cierra el program a por
																// completo
		setResizable(false); // 🔒 Desactiva el botón de maximizar
		setLocationRelativeTo(null); // 🎯 Centra la ventana
		setLayout(new BorderLayout());

		lblMensaje = new JLabel("Se Agrego Con Exito");
		lblMensaje.setBackground(new Color(70, 130, 180)); // Azul acero
		lblMensaje.setForeground(Color.BLUE);// color de la letra
		lblMensaje.setFont(new Font("Arial", Font.BOLD, 12));
		lblMensaje.setVisible(false);

		lblHora = new JLabel(obtenerFechaHoraActual());
		lblCorreo = new JLabel(correo);
		lblRol = new JLabel(rol);

		// ⏱️ Timer que actualiza cada segundo
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHora.setText(obtenerFechaHoraActual());
			}
		});
		timer.start();

		// Panel de entrada panel central
		PanelEntrada = new JPanel(new GridLayout(5, 4)); // filas ,columnas
		nombreField = new JTextField();
		precioField = new JTextField();
		cantidadField = new JTextField();

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

		JButton btnCobrar = new JButton(" ✅ Cobrar");
		btnCobrar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnCobrar.setForeground(Color.YELLOW);// color de la letra
		btnCobrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCobrar.setFocusPainted(false);
		btnCobrar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2px
		PantallaLogin.cambioVisualBoton(btnCobrar);

		// 🔹 20 píxeles arriba, 10 izquierda, 10 abajo, 10 derecha
		PanelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		PanelEntrada.add(lblMensaje);
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(btnLogOut);
		PanelEntrada.add(lblRol);
		PanelEntrada.add(lblCorreo);
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(lblHora);
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(new JLabel("                           Producto"));
		PanelEntrada.add(nombreField);
		PanelEntrada.add(new JLabel("                             Precio"));
		PanelEntrada.add(precioField);
		PanelEntrada.add(new JLabel("                           Cantidad"));
		PanelEntrada.add(cantidadField);
		PanelEntrada.add(new JLabel(""));
		PanelEntrada.add(btnAdd);

		// Tabla
		modelo = new DefaultTableModel(new String[] { "ID", "Nombre", "Precio", "Cantidad", "SubTotal" }, 0);
		tablaTienda = new JTable(modelo);
		
		
		// Panel inferio
		scroll = new JScrollPane(tablaTienda);
		
		PanelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		totalLbl = new JLabel("Total : $0.00");
		PanelInferior.add(totalLbl);
		PanelInferior.add(btnCobrar);

		this.add(PanelEntrada, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.add(PanelInferior, BorderLayout.SOUTH);

		btnCobrar(btnCobrar, totalLbl);
		btnAgregar(btnAdd);
		CrearTablaUser. btncerrarSesion (btnLogOut);
	}
	
	
	public void componentesAlmacen(String correo, String rol) {

//		
	}

	public static void mostrarMensaje3Seg(JLabel lblMensaje) {
		lblMensaje.setVisible(true);
		Timer timer = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setVisible(false);
			}
		});
		timer.setRepeats(false);
		timer.start();

	}

	public void btnAgregar(JButton btnAgregar) {
		btnAgregar.addActionListener(e -> {

			mostrarMensaje3Seg(lblMensaje);
			try {

				String nombre = nombreField.getText();
				double precio = Double.parseDouble(precioField.getText());
				int cantidad = Integer.parseInt(cantidadField.getText());

				Producto p = new Producto(nombre, precio, cantidad);
				productos.put(contadorId, p);
				modelo.addRow(new Object[] { contadorId++, nombre, precio, cantidad, p.getSubtotal() });
				actualizartotal();
				limpiarCampos();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "❌ Datos inválidos." + ex.getMessage());
			}
		});
	}

	public void btnCobrar(JButton btnCobro, JLabel totalLbl) {
		btnCobro.addActionListener(e -> {

			double total = actualizartotal();

			String inputPago = JOptionPane.showInputDialog(null,
					"Total a pagar: $" + String.format("%.2f", total) + "\n¿Con cuánto va a pagar?", "Cobro",
					JOptionPane.QUESTION_MESSAGE);

			try {
				double pago = Double.parseDouble(inputPago);
				if (pago < total) {
					JOptionPane.showMessageDialog(null,
							"❌ El monto es insuficiente. Se necesitan al menos $" + totalLbl.getText());
				} else {
					double cambio = pago - total;
					JOptionPane.showMessageDialog(null, "Cargo de ; $ " + String.format("%.2f", total)
							+ "\n✅ Pago recibido: $" + pago + "\nCambio: $" + String.format("%.2f", cambio));
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "❌ Entrada inválida. Ingresa un número." + e1.getMessage());
			}

			modelo.setRowCount(0);
			productos.clear();
			contadorId = 1;
			actualizartotal();
		});
	}

	private double actualizartotal() {
		double total = 0;
		for (int i = 0; i < modelo.getRowCount(); i++) {
			total += (double) modelo.getValueAt(i, 4);
		}
		totalLbl.setText(String.format("Total: $%.2f", total));
		return total;
	}

	private void limpiarCampos() {
		nombreField.setText("");
		precioField.setText("");
		cantidadField.setText("");
	}

	public static String obtenerFechaHoraActual() {
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return ahora.format(formato);
	}

}
