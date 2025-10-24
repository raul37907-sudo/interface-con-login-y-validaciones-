/*  si  las funciones o  variables son  staticas pertenecen  a la  clase
si no   son  estatico s pertenecen  a la  instancia 

Característica	    static (de clase)	   			 No static (de instancia)
Pertenece a	         La clase	             	     Cada objeto creado
Acceso				Clase.metodo()					 objeto.metodo()
Memoria				Se crea una sola vez			 Se crea por cada instancia
Ideal para		  constantes, lógica común			 Datos únicos por objeto
Ejemplo típico	 Math.pow(), System.out.println()	 persona.getNombre()

*/

package IguTablaUsuarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import IguLogin.PantallaLogin;
import IguLogin.TextJFieldPlaceholder;
import Logica.CrudUserDAO;
import Persistencia.ConexionDBMysql;

@SuppressWarnings("serial")
public class CrearVentanaDAO extends JFrame {

//	private static JTextField correoField;
	private JButton  btnModificar, btnBorrar, btnCrearLogin; //btnCrear
	private JButton btnRegresar, tbnBackLogin;

	private JLabel passlbl, pass2lbl, estatusLbl, mailLbl;

	private static JTextField nombreField;
	private static JTextField PaternoField;
	private static JTextField maternoField;
	private static JTextField fechaField;
	private static JTextField mailField;
	private static JTextField passField;
	private static JTextField pass2Field;

	private static JLabel mensajeGeneralLbl, LblAuxiliar, appLbl, rollbl;

	private static JComboBox<String> comboApp;
	private static JComboBox<String> comboEstatus;
	private static JComboBox<String> comboRol;
	private static JPanel panel;

	public CrearVentanaDAO(String correo, String rol, String modoDeTabla) { // el metodo hereda directamente de l a
																			// clase heredando asi mismo JFrame.
		// permite logica personalizada ,
		// metodos propios
//		JFrame ventanaDao = new JFrame ("U S U A R I O");     como metodo  public static void () // modularidad baja  reutilizacion  limitada
//		ventanaDao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ventanaDao.setSize(350,500);//  ancho ,  alto  
//		ventanaDao.setLocationRelativeTo(null);
//		ventanaDao.setVisible(true);

		this.setTitle("U S U A R I O   -- DAO --"); //
		this.setSize(400, 700); // ancho , largo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// boton cerrar finaliza el programa
		this.setLocationRelativeTo(null); // Centra la ventana
		this.setResizable(false);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // coloca los componentes uno abajo de otro

		panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50)); // agrega un margen interno (padding) ARRIBA ,

		// IZQUIERDA , ABAJO , DERECHA
		panel.setBackground(new Color(140, 240, 255));
		// panel.setBackground(Color.blue);

		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setFont(new Font("Arial", Font.BOLD, 14));
		nombreLbl.setBackground(Color.BLACK);

		JLabel paternoLbl = new JLabel("Ingresa Apellido  Paterno:");
		paternoLbl.setFont(new Font("Arial", Font.BOLD, 14));
		paternoLbl.setBackground(Color.BLACK);

		JLabel maternoLbl = new JLabel("Ingresa Apellido  Materno:");
		maternoLbl.setFont(new Font("Arial", Font.BOLD, 14));
		maternoLbl.setBackground(Color.BLACK);

		JLabel fechNaciLbl = new JLabel("Fecha  de Nacimiento:");
		fechNaciLbl.setFont(new Font("Arial", Font.BOLD, 14));
		fechNaciLbl.setBackground(Color.BLACK);

		mailLbl = new JLabel("E-Mail:");
		mailLbl.setFont(new Font("Arial", Font.BOLD, 14));
		mailLbl.setBackground(Color.BLACK);

		estatusLbl = new JLabel("Estatus:");
		estatusLbl.setFont(new Font("Arial", Font.BOLD, 14));
		estatusLbl.setBackground(Color.BLACK);

		appLbl = new JLabel("App:");
		appLbl.setFont(new Font("Arial", Font.BOLD, 14));
		appLbl.setBackground(Color.BLACK);

		rollbl = new JLabel("Rol:");
		rollbl.setFont(new Font("Arial", Font.BOLD, 14));
		rollbl.setBackground(Color.BLACK);

		LblAuxiliar = new JLabel();
		LblAuxiliar.setFont(new Font("Arial", Font.BOLD, 14));

		passlbl = new JLabel("Contraseña:");
		passlbl.setFont(new Font("Arial", Font.BOLD, 14));

		pass2lbl = new JLabel("Confirmar Contraseña:");
		pass2lbl.setFont(new Font("Arial", Font.BOLD, 14));
		pass2lbl.setBackground(Color.BLACK);

		mensajeGeneralLbl = new JLabel(" Verificación");
		mensajeGeneralLbl.setFont(new Font("Arial", Font.BOLD, 15));

		nombreField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(nombreField, "Ingresa Nombre.");
		nombreField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		nombreField.setForeground(Color.black);

		PaternoField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(PaternoField, "Ingresa Apellido  Paterno.");
		PaternoField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		PaternoField.setForeground(Color.black);

		maternoField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(maternoField, "Ingresa Apellido  Materno.");
		maternoField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		maternoField.setForeground(Color.black);

		fechaField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(fechaField, " Ingresa AAAA-MM-DD");
		fechaField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		fechaField.setForeground(Color.black);

		mailField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(mailField, "Ingresa  E-Mail");
		mailField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		mailField.setForeground(Color.black);

		passField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(passField, "Ingresa Contraseña");
		passField.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		passField.setForeground(Color.black);

		pass2Field = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(pass2Field, "Confirma Contraseña");
		pass2Field.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		pass2Field.setMaximumSize(new Dimension(600, 25)); // ancho y alto del botón
		pass2Field.setForeground(Color.black);

//		btnCrear = new JButton("Crear Usuario ");
//		btnCrear.setBackground(new Color(70, 130, 180)); // Azul acero
//		btnCrear.setForeground(Color.YELLOW);// color de la letra
//		btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
//		btnCrear.setFocusPainted(false); // Swing dibuja un borde punteado o resaltado alrededor de él. Esto se llama //
											// "focus painting". desavilita ese borde

		btnModificar = new JButton(" Guardar Cambios");
		btnModificar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnModificar.setForeground(Color.YELLOW);// color de la letra
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		btnModificar.setFocusPainted(false);

		btnBorrar = new JButton("Borrar Usuario");
		btnBorrar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnBorrar.setForeground(Color.YELLOW);// color de la letra
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnBorrar.setFocusPainted(false);

		btnRegresar = new JButton(" 🛠️  🧠 Cancelar");
		btnRegresar.setBackground(new Color(70, 130, 180)); // Azul acero
		btnRegresar.setForeground(Color.YELLOW);// color de la letra
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setFocusPainted(false);

		btnCrearLogin = new JButton(" 🛠️ CREAR 🧠 ");
		btnCrearLogin.setBackground(new Color(70, 130, 180)); // Azul acero
		btnCrearLogin.setForeground(Color.YELLOW);// color de la letra
		btnCrearLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnCrearLogin.setFocusPainted(false);

		tbnBackLogin = new JButton(" 🛠️ Regresar a login 🧠 ");
		tbnBackLogin.setBackground(new Color(70, 130, 180)); // Azul acero
		tbnBackLogin.setForeground(Color.YELLOW);// color de la letra
		tbnBackLogin.setFont(new Font("Arial", Font.BOLD, 14));
		tbnBackLogin.setFocusPainted(false);

//		PantallaLogin.cambioVisualBoton(btnCrear);
		PantallaLogin.cambioVisualBoton(btnModificar);
		PantallaLogin.cambioVisualBoton(btnBorrar);
		PantallaLogin.cambioVisualBoton(btnRegresar);
		PantallaLogin.cambioVisualBoton(btnCrearLogin);
		PantallaLogin.cambioVisualBoton(tbnBackLogin);

		// redirige el foco
		JLabel focoInicial = new JLabel();
		focoInicial.setFocusable(true); // Asegúrate de que pueda recibir foco
		panel.add(focoInicial); // Agrega al contenedor
		focoInicial.requestFocusInWindow(); // Redirige el foco

		// AGREGAR COMÓNENTES
		panel.add(LblAuxiliar);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(nombreLbl);
		panel.add(nombreField);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(paternoLbl);
		panel.add(PaternoField);
		panel.add(maternoLbl);
		panel.add(maternoField);
		panel.add(fechNaciLbl);
		panel.add(fechaField);
		panel.add(mailLbl);
		panel.add(mailField);
		panel.add(estatusLbl);
		panel.add(appLbl);
		// panel.add(cargarComboApp()); // se agregan desde el acomodo de componentes
		panel.add(rollbl);
		// panel.add(cargarComboRol()); // se agregan desde el acomodo de componentes
		panel.add(passlbl);
		panel.add(passField);
		panel.add(pass2lbl);
		panel.add(pass2Field);
		panel.add(mensajeGeneralLbl);

		Component[] componentes = { LblAuxiliar, mensajeGeneralLbl, nombreLbl, nombreField, paternoLbl, PaternoField,
				maternoLbl, maternoField, fechNaciLbl, fechaField, mailLbl, mailField, estatusLbl, cargarComboEstatus(),
				appLbl, cargarComboApp(), rollbl, cargarComboRol(), passlbl, passField, pass2lbl, pass2Field,

		};

		for (Component comp : componentes) {
			((JComponent) comp).setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(comp);
			panel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

//		panel.add(Box.createRigidArea(new Dimension(0, 10)));
//		panel.add(btnCrear);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(btnModificar);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(btnBorrar);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(btnCrearLogin);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(btnRegresar);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(tbnBackLogin);

//		btnCrear(btnCrear, correo, rol);
		btnRegresar(btnRegresar, correo, rol);
		btnModificar(btnModificar, correo, rol);
		btnBorrar(btnBorrar, correo, rol);
		btnCrearDeLogin(btnCrearLogin);
		tbnBackLogin(tbnBackLogin);

		this.add(panel);
		configurarModo(correo,rol, modoDeTabla);
	}

//	public void btnBackSup(JButton btnBackSup, String correo, String rol) {
//
//		btnBackSup.addActionListener(e -> { // simplificacion lamba
//
//			Window ventana = SwingUtilities.getWindowAncestor(btnBackSup);
//			ventana.dispose(); // cierrar la ventana y libera recursos
//
//			CrearTablaUser tablaUsuarios = new CrearTablaUser();
//			tablaUsuarios.creaVentana(correo, rol);
//			tablaUsuarios.setVisible(true);
//		});
//	}

	public void btnCrearDeLogin(JButton btnCrear) { // btn del form de usuarios

		confirmaClave(passField, pass2Field, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(nombreField, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(PaternoField, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(maternoField, mensajeGeneralLbl);
		solicitarFechaValida(fechaField, 18, mensajeGeneralLbl);
		solicitarCorreoValido(mailField, mensajeGeneralLbl);

		btnCrear.addActionListener(e -> { // simplificacion lamba

			List<String> datos = crearUserFormLogin();

			if (datos == null) {
				return;
			}

			Window ventana = SwingUtilities.getWindowAncestor(btnCrear);
			ventana.dispose();

			PantallaLogin ventanaLogin = new PantallaLogin();
			SwingUtilities.invokeLater(() -> { // ejecuta el codigo en hilo event dispach thread (edt) -> expresion
												// lamda para ejecutar codigo
				ventanaLogin.setVisible(true);
			});
		});

	}
	
	public List<String> crearUserFormLogin() {
		ArrayList<String> listdatosDAO = null;

		String nombre = nombreField.getText().trim();
		String paterno = PaternoField.getText().trim();
		String materno = maternoField.getText().trim();
		String fecNaci = fechaField.getText().trim();
		// LocalDate feNaci = LocalDate.parse(fecNaci);
		// java.sql.Date sqlFecha = java.sql.Date.valueOf(feNaci);
		String correo = mailField.getText().trim();
		int estatus = 0;
		int app = 5;  // ObtenerIdApp();
		int rol = 4; // ObtenerIdRol();
		String pass = pass2Field.getText().trim();
		String passCifrado = PantallaLogin.cifrar(pass.trim(), 3);

		if (nombre.equalsIgnoreCase("Igresa Nombre") || paterno.equalsIgnoreCase("Ingresa Apellido  Paterno")
				|| materno.equalsIgnoreCase("Ingresa Apellido  Materno")
				|| fecNaci.equalsIgnoreCase("Ingresa AAAA-MM-DD") || correo.equalsIgnoreCase("Ingresa  E-Mail")
				|| pass.equalsIgnoreCase("Ingresa Contraseña") || passCifrado.equalsIgnoreCase("Confirma Contraseña")) {

			JOptionPane.showMessageDialog(null, "⚠️ Debes Llenar Todos Los Campos");

		} else {
			listdatosDAO = new ArrayList<>();
			listdatosDAO.add(nombre);
			listdatosDAO.add(paterno);
			listdatosDAO.add(materno);
			listdatosDAO.add(fecNaci);
			listdatosDAO.add(correo);
			listdatosDAO.add(String.valueOf(estatus));
			listdatosDAO.add(String.valueOf(app));
			listdatosDAO.add(String.valueOf(rol));
			listdatosDAO.add(passCifrado);
			

				boolean exito = CrudUserDAO.InsertarUser(nombre, paterno, materno, fecNaci, correo, estatus, app, rol,
						passCifrado);
				return exito ? List.of("ok") : null;
			
		}

		return listdatosDAO;// se va a modificar o borrar
	}
	
	
	
	

	public void configurarModo(String correo, String modoDeLogin, String modoDeTabla) {
		switch (modoDeTabla) {

		case "Modificar":
			btnBorrar.setVisible(false);
			btnModificar.setVisible(true);
			btnRegresar.setVisible(true);
			rollbl.setVisible(true);
			appLbl.setVisible(true);
			estatusLbl.setVisible(true);
			passlbl.setVisible(false);
			pass2lbl.setVisible(false);
			passField.setVisible(false);
			pass2Field.setVisible(false);
//			btnCrear.setVisible(false);
			comboApp.setVisible(true);
			comboEstatus.setVisible(true);
			comboRol.setVisible(true);
			btnCrearLogin.setVisible(false);
			tbnBackLogin.setVisible(false);
			mailField.setEditable(false);
			this.setSize(350, 650);
			panel.setBackground(new Color(220, 220, 220));
			break;

		case "borrar":

			btnBorrar.setVisible(true);
			btnRegresar.setVisible(true);
//			btnCrear.setVisible(false);
			btnModificar.setVisible(false);
			btnCrearLogin.setVisible(false);
			tbnBackLogin.setVisible(false);
			passlbl.setVisible(false);
			pass2lbl.setVisible(false);
			passField.setVisible(false);
			pass2Field.setVisible(false);
			comboApp.setVisible(false);
			comboEstatus.setVisible(false);
			comboRol.setVisible(false);
			appLbl.setVisible(false);
			rollbl.setVisible(false);
			estatusLbl.setVisible(false);
			this.setSize(400, 700);
			panel.setBackground(new Color(83, 145, 199));
			break;
		}

		switch (modoDeLogin) {

		case "crearUserNew":
			tbnBackLogin.setVisible(true);
			btnCrearLogin.setVisible(true);
//			btnCrear.setVisible(false);
			btnRegresar.setVisible(false);
			btnModificar.setVisible(false);
			btnBorrar.setVisible(false);
			comboApp.setVisible(false);
			comboEstatus.setVisible(false);
			comboRol.setVisible(false);
			appLbl.setVisible(false);
			rollbl.setVisible(false);
			estatusLbl.setVisible(false);
			this.setSize(400, 600);
			panel.setBackground(new Color(10, 240, 255));

			break;

		}
	}

	public static void tbnBackLogin(JButton tbnBackLogin) {

		tbnBackLogin.addActionListener(e -> {

			PantallaLogin ventanaLogin = new PantallaLogin();
			ventanaLogin.setVisible(true);

			Window ventana = SwingUtilities.getWindowAncestor(tbnBackLogin);
			ventana.dispose();
		});

	}

	public void btnBorrar(JButton btnEliminar, String correo, String rol) {

		btnEliminar.addActionListener(e -> {
			List<String> idBorrar = crearUsuariConClave();
			CrudUserDAO.borrarUsuario(idBorrar.get(8));

			CrearTablaUser tablaDatos = new CrearTablaUser();
			tablaDatos.creaVentana(correo, rol);
			tablaDatos.setVisible(true);

			Window ventana = SwingUtilities.getWindowAncestor(btnEliminar);
			ventana.dispose();

		});

	}

	public List<String> crearUsuariConClave() {
		ArrayList<String> listdatosDAO = null;

		String id = LblAuxiliar.getText();
		String nombre = nombreField.getText();
		String paterno = PaternoField.getText();
		String materno = maternoField.getText();
		String fecNaci = fechaField.getText();
		// LocalDate feNaci = LocalDate.parse(fecNaci);
		// java.sql.Date sqlFecha = java.sql.Date.valueOf(feNaci);
		String correo = mailField.getText();
		int estatus = ObtenerIdEstatus();
		int app = ObtenerIdApp();
		int rol = ObtenerIdRol();
		String pass = pass2Field.getText();
		String passCifrado = PantallaLogin.cifrar(pass, 3);

		if (nombre.equalsIgnoreCase("Igresa Nombre") || paterno.equalsIgnoreCase("Ingresa Apellido  Paterno")
				|| materno.equalsIgnoreCase("Ingresa Apellido  Materno")
				|| fecNaci.equalsIgnoreCase("Ingresa AAAA-MM-DD") || correo.equalsIgnoreCase("Ingresa  E-Mail")
				|| pass.equalsIgnoreCase("Ingresa Contraseña") || passCifrado.equalsIgnoreCase("Confirma Contraseña")) {

			JOptionPane.showMessageDialog(null, "⚠️ Debes Llenar Todos Los Campos");

		} else {
			listdatosDAO = new ArrayList<>();
			listdatosDAO.add(nombre);
			listdatosDAO.add(paterno);
			listdatosDAO.add(materno);
			listdatosDAO.add(fecNaci);
			listdatosDAO.add(correo);
			listdatosDAO.add(String.valueOf(estatus));
			listdatosDAO.add(String.valueOf(app));
			listdatosDAO.add(String.valueOf(rol));
			listdatosDAO.add(id);
			// listdatosDAO.add(passCifrado);
			if (id == null || id.trim().isEmpty()) {

				boolean exito = CrudUserDAO.InsertarUser(nombre, paterno, materno, fecNaci, correo, estatus, app, rol,
						passCifrado);
				return exito ? List.of("ok") : null;
			}
		}

		return listdatosDAO;// se va a modificar o borrar
	}

	public void btnModificar(JButton btnModificar, String correo, String rol) {

		btnModificar.addActionListener(e -> {
			List<String> listaModificar = crearUsuariConClave();
			CrudUserDAO.ActualizarUsuario(listaModificar);

			CrearTablaUser tablaDatos = new CrearTablaUser();
			tablaDatos.creaVentana(correo, rol);
			tablaDatos.setVisible(true);
			Window ventana = SwingUtilities.getWindowAncestor(btnModificar);
			ventana.dispose();
		});
	}

	public void btnCrear(JButton btnCrear, String correo, String rol) { // btn del form de usuarios

		confirmaClave(passField, pass2Field, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(nombreField, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(PaternoField, mensajeGeneralLbl);
		validarSoloLetrasEscucharTextField(maternoField, mensajeGeneralLbl);
		solicitarFechaValida(fechaField, 18, mensajeGeneralLbl);
		solicitarCorreoValido(mailField, mensajeGeneralLbl);

		btnCrear.addActionListener(e -> { // simplificacion lamba

			List<String> datos = crearUsuariConClave();

			if (datos == null) {
				return;
			}

//			if ("crearUserNew".equalsIgnoreCase(rol.trim())) {
//				Window ventaa = SwingUtilities.getWindowAncestor(btnCrear);
//				ventaa.dispose();
//				
//				PantallaLogin panLog = new PantallaLogin();
//				panLog.setVisible(true);				
//			}else {
			Window ventana = SwingUtilities.getWindowAncestor(btnCrear);
			ventana.dispose();

			CrearTablaUser tablaUsuarios = new CrearTablaUser();
			tablaUsuarios.creaVentana(correo, rol);
			tablaUsuarios.setVisible(true);
//		}
		});

	}

	public static boolean correoYaExiste(String correo) {

		Connection conexion = ConexionDBMysql.getInstancia().getConnection();

		String sqlUsuario = "SELECT 1 FROM Usuario WHERE e_mail = ?";
		String sqlGerente = "SELECT 1 FROM Gerente WHERE mail = ?";

		try (PreparedStatement psUsuario = conexion.prepareStatement(sqlUsuario);
				PreparedStatement psGerente = conexion.prepareStatement(sqlGerente)) {
			psUsuario.setString(1, correo);
			ResultSet rsUsuario = psUsuario.executeQuery();
			if (rsUsuario.next()) {
				return true; // El correo ya existe en Usuario
			}

			psGerente.setString(1, correo);
			ResultSet rsGerente = psGerente.executeQuery();
			if (rsGerente.next()) {
				return true; // El correo ya existe en Gerente
			}

			return false; // No se encontró en ninguna tabla

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al verificar correo: " + e.getMessage());
			return true; // Por seguridad, asumimos que sí existe si hay error
		}
	}

	public void btnRegresar(JButton btnRegresar, String correo, String rol) {

		btnRegresar.addActionListener(e -> { // simplificacion lamba

			Window ventana = SwingUtilities.getWindowAncestor(btnRegresar);
			ventana.dispose(); // cierrar la ventana y libera recursos

			CrearTablaUser tablaUsuarios = new CrearTablaUser();
			tablaUsuarios.creaVentana(correo, rol);
			tablaUsuarios.setVisible(true);

		});
	}

	public static List<String> llenarForm(List<String> ListLlenarForm) {

		int pos = 0;
		LblAuxiliar.setText(ListLlenarForm.get(pos++));
		nombreField.setText(ListLlenarForm.get(pos++));
		;
		PaternoField.setText(ListLlenarForm.get(pos++));
		maternoField.setText(ListLlenarForm.get(pos++));
		fechaField.setText(ListLlenarForm.get(pos++));
		mailField.setText(ListLlenarForm.get(pos++));
		comboEstatus.setSelectedItem(ListLlenarForm.get(pos++));
		comboApp.setSelectedItem(ListLlenarForm.get(pos++));
		comboRol.setSelectedItem(ListLlenarForm.get(pos++));

		return ListLlenarForm;
	}

	public static int ObtenerIdEstatus() {

		String validar = comboEstatus.getSelectedItem().toString();
		if (validar.equalsIgnoreCase("Activo")) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int ObtenerIdApp() {

		String validar = comboApp.getSelectedItem().toString();
	if (validar.equalsIgnoreCase("Null")) {
		return 5;
	}else if (validar.equalsIgnoreCase("Vix")) {
			return 1;
		} else if (validar.equalsIgnoreCase("YouTube")) {
			return 2;
		} else if (validar.equalsIgnoreCase("Netflix")) {
			return 3;
		} else {
			return 4;
		}
	}

	public static int ObtenerIdRol() {

		String validar = comboRol.getSelectedItem().toString();
		if (validar.equalsIgnoreCase("Null")) {
			return 4;
		}else if (validar.equalsIgnoreCase("Supervisor")) {
			return 1;
		} else if (validar.equalsIgnoreCase("Encargado")) {
			return 2;
		} else {
			return 3;
		}
	}

	public static void solicitarCorreoValido(JTextField correo, JLabel menGeneral) {

		correo.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarCorreo();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validarCorreo();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validarCorreo();
			}

			public void validarCorreo() {

				if (!esCorreoValido(correo.getText())) {
					correo.setBackground(Color.pink);
					menGeneral.setText("❌ Formato de correo inválido.");
					mensajeGeneralLbl.setForeground(Color.red);

				} else if (!tieneDominioPermitido(correo.getText())) {
					correo.setBackground(Color.pink);
					menGeneral.setText("⚠️ El dominio no está permitido.");
					mensajeGeneralLbl.setForeground(Color.red);

				} else {
					correo.setBackground(Color.green);
					menGeneral.setText("CORREO  VALIDO");
					menGeneral.setForeground(Color.blue);
				}
			}
		});
	}

	public static boolean esCorreoValido(String correo) {
		String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
		return correo.matches(regex);
	}

	public static boolean tieneDominioPermitido(String correo) {
		String[] dominiosPermitidos = { "@empresa.com" + "@EMPRESA.COM", "@corporativo.mx", "@CORPORATIVO.MX",
				"@miempresa.org", "@MIEMPRESA.ORG", "@gmail.com", "@GMAIL.COM", "@HOTMAIL.COM", "@hotmail.com" };
		for (String dominio : dominiosPermitidos) {
			if (correo.endsWith(dominio))
				return true;
		}
		return false;
	}

	public static Period calcularEdadCompleta(LocalDate fechaNacimiento) {
		// LocalDate hoy = LocalDate.now();
		return Period.between(fechaNacimiento, LocalDate.now());
	}

	public static void solicitarFechaValida(JTextField campoConfirmacion, int edadMinima, JLabel menGeneral) {
		campoConfirmacion.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				verificarFecha(campoConfirmacion, edadMinima);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				verificarFecha(campoConfirmacion, edadMinima);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				verificarFecha(campoConfirmacion, edadMinima);
			}

			private void verificarFecha(JTextField campoConfirmacion, int edadMinima) {
				String textoFecha = campoConfirmacion.getText().trim();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				try {
					LocalDate fecha = LocalDate.parse(textoFecha, formato);
					Period edad = calcularEdadCompleta(fecha);

					if (edad.getYears() < edadMinima) {
						menGeneral.setText("❌ Debes tener al menos 18 años");
						menGeneral.setForeground(Color.RED);
						campoConfirmacion.setBackground(Color.PINK);
					} else {
						campoConfirmacion.setBackground(Color.GREEN);
						menGeneral.setText(" fecha  correcta ");
					}

				} catch (DateTimeParseException ex) {
					if (!textoFecha.isEmpty()) {
						menGeneral.setText("❌ Formato inválido. Usa YYYY-MM-DD");
						campoConfirmacion.setBackground(Color.PINK);
						menGeneral.setForeground(Color.RED);
					} else {
						campoConfirmacion.setBackground(Color.white);
						// menGeneral.setText("❌ Formato inválido. Usa YYYY-MM-DD");
					}
				}
			}
		});
	}

	public static String validarSoloLetrasEscucharTextField(JTextField validarSoloLetras, JLabel menGeneral) {

		validarSoloLetras.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarSoloLetras(validarSoloLetras, menGeneral);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validarSoloLetras(validarSoloLetras, menGeneral);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validarSoloLetras(validarSoloLetras, menGeneral);
			}
		});

		return null;
	}

	public static String validarSoloLetras(JTextField nombreField2, JLabel menGeneral) {
		String texto = nombreField2.getText().trim();
		boolean valido = texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");

		if (!valido && !texto.isEmpty()) {

			menGeneral.setText("❌ SOLO LETRAS ");
			menGeneral.setForeground(Color.red);
			nombreField2.setBackground(Color.pink);
		} else {
			if (texto.isEmpty()) {
				nombreField2.setBackground(Color.pink);
			} else {
				menGeneral.setText("FORMATO  VALIDO");
				menGeneral.setForeground(Color.BLUE);
				nombreField2.setBackground(Color.GREEN);
			}
		}
		return texto;
	}

	public static String confirmaClave(JTextField passOriginal, JTextField pass2, JLabel mensaje) {
		ValidadorClave validador = new ValidadorClave(passOriginal, pass2, mensaje);

		if (validador.clavesCoinciden()) {
			String claveCifrada = PantallaLogin.cifrar(passOriginal.getText(), 3);
			return claveCifrada;
		}
		return null;
	}

	public JComboBox<String> cargarComboRol() {
		comboRol = new JComboBox<>();
		try {
			Connection conexion = ConexionDBMysql.getInstancia().getConnection();

			String sql = "SELECT tipo_rol FROM rol";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				comboRol.addItem(rs.getString("tipo_rol"));
			}

			// conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comboRol;
	}

	public JComboBox<String> cargarComboEstatus() {
		comboEstatus = new JComboBox<>();
		comboEstatus.addItem(("Activo"));
		comboEstatus.addItem(("Inactivo"));
		comboEstatus.setMaximumSize(new Dimension(200, 30)); // 🔹 x, y, ancho, alto)); // ancho alto
		return comboEstatus;
	}

	public JComboBox<String> cargarComboApp() {
		comboApp = new JComboBox<>();
		comboApp.setMaximumSize(new Dimension(200, 30)); // 🔹 x, y, ancho, alto));
		try {
			Connection conexion = ConexionDBMysql.getInstancia().getConnection();

			String sql = "SELECT nombre FROM apps";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				comboApp.addItem(rs.getString("nombre"));
			}

			// conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comboApp;
	}

}
