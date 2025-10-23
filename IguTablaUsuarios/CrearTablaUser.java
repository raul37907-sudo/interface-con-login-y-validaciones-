package IguTablaUsuarios; /*    JFrame es ventana principal (messa)  Jpanel  el lienso  necesita el  fram para ser visible */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import IguLogin.AlertaAnimada;
import IguLogin.PantallaLogin;
import Persistencia.ConexionDBMysql;
import iguVentas.Almacen;

@SuppressWarnings("serial")
public class CrearTablaUser extends JPanel {

	private JFrame ventana;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnCerrarSeción;

	private JComboBox<String> ComboBoxEditor;

	private JTable tablaUser;;

	private JLabel JlbSesion;

	public void creaVentana(String correo, String rol) {

		ventana = new JFrame("Usuarios");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(1000, 300); // ancho , alto
		ventana.setLocationRelativeTo(null);
		ventana.add(TablaUsuarios(correo, rol));
		ventana.setVisible(true);
		configuracion(rol, btnModificar, btnEliminar, btnAgregar, btnCerrarSeción, ComboBoxEditor);
	}

	public Component TablaUsuarios(String correo, String rol) {

		btnModificar = new JButton("Modificar");
		btnEliminar = new JButton("Eliminar");
		btnAgregar = new JButton("Agregar");
		btnCerrarSeción = new JButton("Sign Out");

		btnModificar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnModificar.setForeground(Color.YELLOW);// color de la letra
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));

		btnEliminar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnEliminar.setForeground(Color.YELLOW);// color de la letra
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));

		btnAgregar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnAgregar.setForeground(Color.YELLOW);// color de la letra
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));

		btnCerrarSeción.setBackground(new Color(70, 130, 180)); // Azul acero
		btnCerrarSeción.setForeground(Color.YELLOW);// color de la letra
		btnCerrarSeción.setFont(new Font("Arial", Font.BOLD, 9));

		JlbSesion = new JLabel(correo + " :" + rol);
		JlbSesion.setFont(new Font("Arial", Font.BOLD, 10));
		JlbSesion.setBackground(Color.BLACK);

		ComboBoxEditor = new JComboBox<>(new String[] { "Tabla Usuarios", "Almacen" });
		ComboBoxEditor.setMaximumSize(new Dimension(100, 35));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS)); // alinea vertical,mente

		JlbSesion.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnCerrarSeción.setAlignmentX(Component.RIGHT_ALIGNMENT);

		panelSuperior.add(ComboBoxEditor);

		panelSuperior.add(JlbSesion);
		panelSuperior.add(btnCerrarSeción);

		JPanel contenedorDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		contenedorDerecha.add(panelSuperior);

		JPanel contenedorizquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contenedorizquierdo.add(ComboBoxEditor);

		JPanel panelBotones = new JPanel(new FlowLayout()); // centra los botones
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		// panelBotones.add(btnAgregar);

		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

		// modelo = new DefaultTableModel(new String[] { "ID", "Nombre", "Precio",
		// "Cantidad", "SubTotal" }, 0);
		DefaultTableModel modelo = new DefaultTableModel();
		setLayout(new BorderLayout());

		// Definir columnas
		modelo = new DefaultTableModel();
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido Paterno");
		modelo.addColumn("Apellido Materno");
		modelo.addColumn("Fecha Nacimiento");
		modelo.addColumn("E-mail");
		modelo.addColumn("Estatus");
		modelo.addColumn("App");
		modelo.addColumn("Rol");
		// modelo.addColumn("pass");

		tablaUser = new JTable(modelo);
		tablaUser.getColumnModel().getColumn(0).setPreferredWidth(10); // índice 3 = columna "fecha cambia el tamaño
																		// de// las columnas
		tablaUser.getColumnModel().getColumn(3).setPreferredWidth(65); // índice 3 = columna "fecha cambia el tamaño
																		// de// las columnas
		tablaUser.getColumnModel().getColumn(5).setPreferredWidth(180); // índice 4 = columna correo
		tablaUser.getColumnModel().getColumn(6).setPreferredWidth(30); // índice 5 = columna estatus"
		tablaUser.getColumnModel().getColumn(7).setPreferredWidth(30); // índice 6 = columna "app
		tablaUser.getColumnModel().getColumn(8).setPreferredWidth(40); // índice 6 = columna "rol

		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tablaUser.getColumnCount(); i++) {
			tablaUser.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}

		JScrollPane scroll = new JScrollPane(tablaUser);
		panelContenedor.add(contenedorizquierdo);
		panelContenedor.add(contenedorDerecha);
		panelContenedor.add(scroll); // ✅ Tablaa
		panelContenedor.add(panelBotones); // ✅ Botones

		this.setLayout(new BorderLayout());
		this.add(panelContenedor);

		try {
			// enviamos el modelo a donde se van a cargar los datos de DB
			obtenerUsuarios(modelo);
		} catch (SQLException e) {
			System.out.println("No  se puede enviar  el  modelo " + e.getMessage());
			e.printStackTrace();
		}

		// btnAgregar
		btnCrear(btnAgregar, correo, rol);
		PantallaLogin.cambioVisualBoton(btnAgregar);
		btnModificar(btnModificar, tablaUser, correo, rol);
		PantallaLogin.cambioVisualBoton(btnModificar);
		btnEliminar(btnEliminar, tablaUser, correo, rol);
		PantallaLogin.cambioVisualBoton(btnEliminar);
		btncerrarSesion(btnCerrarSeción);
		comboBoxUserAlamcen(ComboBoxEditor,correo, rol);

		configuracion(rol, btnModificar, btnEliminar, btnAgregar, btnCerrarSeción, ComboBoxEditor);

		return this; // ✅ Devuelve el panel completo con tabla y botones

	}

	public static void comboBoxUserAlamcen(JComboBox<String> ComboBoxEditor, String correo, String rol) {

		ComboBoxEditor.addActionListener(e -> {
			if ("Almacen".equalsIgnoreCase((String) ComboBoxEditor.getSelectedItem())) {

				Window ventana = SwingUtilities.getWindowAncestor(ComboBoxEditor);
				ventana.dispose();
				Almacen ventanaAlmacen = new Almacen(correo, rol);
				ventanaAlmacen.setVisible(true);
			}

		});
	}

	public static void btncerrarSesion(JButton btnCerrarSeción) {

		btnCerrarSeción.addActionListener(e -> {
			Window ventanaLogin = SwingUtilities.getWindowAncestor(btnCerrarSeción);
			ventanaLogin.dispose();
			PantallaLogin login = new PantallaLogin();
			login.setVisible(true);
		});
	}

	public static void btnEliminar(JButton btnEliminar, JTable tablaEnUso, String correo, String Rol) {

		btnEliminar.addActionListener(e -> {

			List<String> ListLLenarForm = new ArrayList<>();

			int filaSeleccionada = tablaEnUso.getSelectedRow();
			if (filaSeleccionada == -1) {
				AlertaAnimada.mostrarAlertaAnimada("Selecciona una  Fila");

			} else {
				CrearVentanaDAO ventanaDao = new CrearVentanaDAO(correo, Rol, "borrar");
				ventanaDao.setVisible(true);

				Window ventana = SwingUtilities.getWindowAncestor(btnEliminar);
				ventana.dispose();

				int pos = 0;
				String id = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String nombre = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String paterno = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String materno = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String fechNac = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String mail = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String estatus = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String app = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String rol = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();

				ListLLenarForm.add(id);
				ListLLenarForm.add(nombre);
				ListLLenarForm.add(paterno);
				ListLLenarForm.add(materno);
				ListLLenarForm.add(fechNac);
				ListLLenarForm.add(mail);
				ListLLenarForm.add(estatus);
				ListLLenarForm.add(app);
				ListLLenarForm.add(rol);
				CrearVentanaDAO.llenarForm(ListLLenarForm);

			}
		});

	}

	public static void btnModificar(JButton btnModificar, JTable tablaEnUso, String correo, String Rol) {

		btnModificar.addActionListener(e -> {
			List<String> ListLLenarForm = new ArrayList<>();

			int filaSeleccionada = tablaEnUso.getSelectedRow();
			if (filaSeleccionada == -1) {
				AlertaAnimada.mostrarAlertaAnimada("Selecciona una  Fila");

			} else {
				CrearVentanaDAO ventanaDao = new CrearVentanaDAO(correo, Rol, "Modificar");
				ventanaDao.setVisible(true);

				Window ventana = SwingUtilities.getWindowAncestor(btnModificar);
				ventana.dispose();

				int pos = 0;
				String id = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String nombre = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String paterno = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String materno = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String fechNac = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String mail = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String estatus = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String app = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();
				String rol = tablaEnUso.getValueAt(filaSeleccionada, pos++).toString();

				ListLLenarForm.add(id);
				ListLLenarForm.add(nombre);
				ListLLenarForm.add(paterno);
				ListLLenarForm.add(materno);
				ListLLenarForm.add(fechNac);
				ListLLenarForm.add(mail);
				ListLLenarForm.add(estatus);
				ListLLenarForm.add(app);
				ListLLenarForm.add(rol);
				CrearVentanaDAO.llenarForm(ListLLenarForm);

			}
		});
	}

	public static void btnCrear(JButton btncrear, String correo, String rol) {
		btncrear.addActionListener(e -> {// simplificacion lamda
			CrearVentanaDAO ventanaDatosUser = new CrearVentanaDAO(correo, rol, "crear");
			ventanaDatosUser.setVisible(true);

			Window ventana = SwingUtilities.getWindowAncestor(btncrear);
			ventana.dispose();
		});

	}

	// tiene que servir el modelo de crear tabla de usuario
	public static void obtenerUsuarios(DefaultTableModel modelo) throws SQLException {
		Connection conectarDB = ConexionDBMysql.getInstancia().getConnection();
		Statement stmtUsuarios = conectarDB.createStatement();
		ResultSet ResultadoRs = stmtUsuarios.executeQuery(" SELECT * FROM Usuario "); // obtiene todos los campos de la
																						// base de datos

		// ResultSet ResultadoRs = stmtUsuarios.executeQuery(" SELECT
		// id_usuario,nombre,apellido_paterno,apellido_materno,fecha_nacimiento,e_mail,estatus_activo,
		// id_app,id_rol,pass FROM Usuario "); // ontenet todos los campos de la base de
		// datos
		while (ResultadoRs.next()) {
			Object[] fila = { ResultadoRs.getString("id_usuario"), // ResultadoRs.getString("pass"),no se agrega ni id
																	// ni pass a la tabla de datos
					ResultadoRs.getString("nombre"), ResultadoRs.getString("apellido_paterno"),
					ResultadoRs.getString("apellido_materno"), ResultadoRs.getString("fecha_nacimiento"),
					ResultadoRs.getString("e_mail"), validarEstatus(ResultadoRs.getInt("estatus_activo")),
					obtenerNombreAppPorId(ResultadoRs.getInt("id_app")),
					obtenerNameRolPorId(ResultadoRs.getInt("id_rol")), };
			modelo.addRow(fila); // ya
		}
		ResultadoRs.close();
		stmtUsuarios.close();
	}

	public static String obtenerNameRolPorId(int idRol) {
		String sql = "SELECT tipo_rol FROM Rol WHERE id_rol = ?";

		try (PreparedStatement stmt = ConexionDBMysql.getInstancia().getConnection().prepareStatement(sql)) {
			stmt.setInt(1, idRol);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getString("tipo_rol");
			}
		} catch (SQLException e) {
			// System.err.println("❌ Error al obtener nombre del rol: " + e.getMessage());
		}

		return "Desconocido";
	}

	public static String obtenerNombreAppPorId(int idApp) {
		String sql = "SELECT nombre FROM Apps WHERE id_app = ?";

		try (PreparedStatement stmt = ConexionDBMysql.getInstancia().getConnection().prepareStatement(sql)) {
			stmt.setInt(1, idApp);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
		}

		return "Desconocido";
	}

	public static String validarEstatus(int status) {
		String estatus = status == 1 ? "Activo" : "Inactivo";
		return estatus;
	}

	public static void configuracion(String rol, JButton btnModificar, JButton btnEliminar, JButton btnAgregar,
			JButton btnCerrarSeción, JComboBox<String> comboBoxEditor) {

		switch (rol) {

		case "Supervisor": // solo puede modificar ciertos campos y dar de alta los productos
			btnEliminar.setVisible(false);

			break;

		case "Encargado": // no puede editar nada mas que ver la info
			comboBoxEditor.setVisible(true);
			btnAgregar.setVisible(false);
			btnEliminar.setVisible(false);
			btnModificar.setVisible(false);
			break;
		case "Empleado": // entra directo a la ventana de ventas
			comboBoxEditor.setVisible(false);
			break;

		}

	}

}
