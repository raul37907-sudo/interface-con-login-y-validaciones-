package IguLogin;

import IguTablaUsuarios.CrearTablaUser;
import IguTablaUsuarios.CrearVentanaDAO;
import Persistencia.ConexionDBMysql;
import iguVentas.TiendaGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class PantallaLogin extends JFrame {

	private static JTextField correoField;
	private static JPasswordField claveField;
	private static JButton loginButton;

	private JButton btnCrear;

	public PantallaLogin() {

		setTitle("L O G I N ");

		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// boton cerrar finaliza el programa
		setLocationRelativeTo(null); // Centra la ventana
		setResizable(false); // 🔒 Desactiva el botón de maximizar

		// Crear panel principal con layout
		// JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15)); //GridLayout) que
		// organiza los componentes en: 3 filas 2 columnas
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// 30 píxeles de espacio horizontal entre columnas 30 píxeles de espacio
		// vertical entre filas
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // agrega un margen interno (padding)
		panel.setBackground(new Color(240, 248, 255)); // Azul claro

		// Crear componentes etiquetas
		JLabel titulo = new JLabel(" SIGN UP "); // centra el titulo
//		titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // centrar componente
		titulo.setFont(new Font("Arial", Font.BOLD, 24));

		JLabel correoLabel = new JLabel("Correo:");
		correoLabel.setFont(new Font("Arial", Font.BOLD, 14)); // tipo de letra y tamaño de los jlabel

		correoField = new JTextField();
		TextJFieldPlaceholder.aplicarPlaceholder(correoField, "INGRESA TU CORREO");
		correoField.setMaximumSize(new Dimension(800, 20)); // ancho y alto del botón

		JLabel claveLabel = new JLabel("Clave:");
		claveLabel.setFont(new Font("Arial", Font.BOLD, 14));

		claveField = new JPasswordField();
		TextJFieldPlaceholder.aplicarPlaceholder(claveField, "INGRESA TU CLAVE");
		claveField.setMaximumSize(new Dimension(800, 20)); // ancho y alto del botón

		JCheckBox mostrarClave = new JCheckBox("Mostrar clave");
		ConvertirAsteriscoClave(claveField, mostrarClave); // muestra l a clave ya que se oculta como
															// asteriscos

		loginButton = new JButton("Login");
		loginButton.setBackground(new Color(70, 130, 180)); // Azul acero
		loginButton.setForeground(Color.YELLOW);// color de la letra
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		loginButton.setFocusPainted(false); // Swing dibuja un borde punteado o resaltado alrededor de él. Esto se llama
		cambioVisualBoton(loginButton);

		btnCrear = new JButton("CREAR");
		btnCrear.setBackground(new Color(70, 130, 180)); // Azul acero
		btnCrear.setForeground(Color.YELLOW);// color de la letra
		btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
		btnCrear.setFocusPainted(false);
		cambioVisualBoton(btnCrear);

		JLabel focoInicial = new JLabel();
		focoInicial.setFocusable(true); // Asegúrate de que pueda recibir foco
		panel.add(focoInicial); // Agrega al contenedor
		focoInicial.requestFocusInWindow(); // Redirige el foco
		panel.add(titulo);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(correoLabel);
		panel.add(correoField);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(claveLabel);
		panel.add(claveField);
		panel.add(mostrarClave); // muestra el checkbox
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		panel.add(loginButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		panel.add(btnCrear);
		// Agregar panel al frame
		add(panel);
		clikBotonLogin(loginButton);
		btnCrear(btnCrear);
	}

	public static void btnCrear(JButton btnCrear) {

		btnCrear.addActionListener(e -> {

			CrearVentanaDAO formCrear = new CrearVentanaDAO("", "crearUserNew", "");
			formCrear.setVisible(true);
			Window ventana = SwingUtilities.getWindowAncestor(btnCrear);
			ventana.dispose();

		});

	}

	public static void clikBotonLogin(JButton loginbtn) {

		loginbtn.addActionListener(e -> { // simplificacion lamba
			String correo = correoField.getText().toLowerCase();

			char[] clave = claveField.getPassword();
			String claveEncriptada = cifrar(String.valueOf(clave), 3);
			boolean validar;

			validar = UserInactivo(correo, claveEncriptada);
			if (!validar) {
				JOptionPane.showMessageDialog(null, "⚠️ Usuario inactivo. No puedes Iniciar  Sesión .");
			} else {

				validarCorreoYClave(correo, claveEncriptada, loginbtn);
			}
//			Window ventana = SwingUtilities.getWindowAncestor(loginbtn);
//			ventana.dispose();
			// no puede ir en el boton por que primero debe de validar si pasa o no

		});

	}

	public static void ConvertirAsteriscoClave(JPasswordField claveField, JCheckBox mostrarClave) {
		mostrarClave.addActionListener(e -> {
			if (mostrarClave.isSelected()) {
				claveField.setEchoChar((char) 0); // Muestra texto plano
			} else {
				claveField.setEchoChar('*'); // Oculta con puntos
			}
		});
	}

	public static void cambioVisualBoton(JButton loginButton) {
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(new Color(200, 130, 180));// azul acero
				// loginButton.setBackground(new Color(70, 130, 180));//azul acero
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(new Color(70, 130, 180));
			}
		});

	}

	public static String cifrar(String texto, int desplazamiento) {
		StringBuilder resultado = new StringBuilder();
		for (char c : texto.toCharArray()) {
			if (c >= 32 && c <= 126) { // Rango de caracteres imprimibles
				char cifrado = (char) (((c - 32 + desplazamiento) % 95) + 32);
				resultado.append(cifrado);
			} else {
				resultado.append(c); // Mantiene caracteres fuera del rango
			}
		}
		return resultado.toString();
	}

//	public static String descifrar(String texto, int desplazamiento) {
//		return cifrar(texto, 95 - (desplazamiento % 95)); // Reversa del cifrado
//	}

	public static boolean UserInactivo(String correo, String clave) {

		Connection conexion = ConexionDBMysql.getInstancia().getConnection();
		String UserSqlInactivo = "SELECT r.tipo_rol, u.estatus_activo FROM Usuario u Join Rol r ON u.id_rol = r.id_rol WHERE u.e_mail = ? AND u.pass =? ";
		String GerenteSqlInactivo = " SELECT r.tipo_rol, g.estatus_activo FROM Gerente g JOIN Clave c ON g.id_pass = c.id_pass join Rol r ON r.id_rol = 1 WHERE g.mail =? AND c.clave =? ";

		try {
			PreparedStatement psUserInactivo = conexion.prepareStatement(UserSqlInactivo);
			psUserInactivo.setString(1, correo);
			psUserInactivo.setString(2, clave);
			ResultSet rs = psUserInactivo.executeQuery();

			if (rs.next()) {
				int estatus = rs.getInt("estatus_activo");
				return estatus == 1;
			}

			// Verificar Gerente
			PreparedStatement psGerente = conexion.prepareStatement(GerenteSqlInactivo);
			psGerente.setString(1, correo);
			psGerente.setString(2, clave);
			ResultSet rsGerente = psGerente.executeQuery();

			if (rsGerente.next()) {
				int estatus = rsGerente.getInt("estatus_activo");
				return estatus == 1;
			}
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Correo no Encontrado " + e.getMessage());
		}
		return true;
	}

	///////////// ******************************************************************************

	// validas la entrada y distribución de tareas por medio de los roles
	public static boolean validarCorreoYClave(String correo, String clave, JButton loginbtn) {
		Connection conn = ConexionDBMysql.getInstancia().getConnection();
		String sqlGerente = " SELECT r.tipo_rol FROM Gerente g  JOIN Clave c ON g.id_pass = c.id_pass JOIN Rol r ON r.id_rol = 1  WHERE g.mail = ? AND c.clave = ? ";

		String sqlUsuario = " SELECT r.tipo_rol FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol  WHERE u.e_mail = ? AND u.pass = ? ";

		try {
			// Validar Gerente
			PreparedStatement psGerente = conn.prepareStatement(sqlGerente);
			psGerente.setString(1, correo);
			psGerente.setString(2, clave);
			ResultSet rsGerente = psGerente.executeQuery();

			if (rsGerente.next()) {
				// String rol = rsGerente.getString("tipo_rol");
				AccesoCorrecto("LOGIN CORRECTO");
				Window ventana = SwingUtilities.getWindowAncestor(loginbtn);
				ventana.dispose();
				JOptionPane.showMessageDialog(null, "Permiso de Administrador");
				CrearTablaUser tablaUsuarios = new CrearTablaUser();
				tablaUsuarios.creaVentana(correo, "Administrador");
				return true;
			}

			// Validar Usuario
			PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
			psUsuario.setString(1, correo);
			psUsuario.setString(2, clave);
			ResultSet rsUsuario = psUsuario.executeQuery();

			if (rsUsuario.next()) {
				String rol = rsUsuario.getString("tipo_rol");
				AccesoCorrecto("LOGIN CORRECTO");

				if ("Supervisor".equalsIgnoreCase(rol.trim()) || "Encargado".equalsIgnoreCase(rol.trim())) {
					Window ventana = SwingUtilities.getWindowAncestor(loginbtn);
					ventana.dispose();
					JOptionPane.showMessageDialog(null, "Acceso Correcto Eres : " + rol);
					CrearTablaUser tablaUser = new CrearTablaUser();
					tablaUser.creaVentana(correo, rol);
					return true;
				} else if ("Empleado".equalsIgnoreCase(rol.trim())) {
					JOptionPane.showMessageDialog(null, "Acceso Correcto Eres : " + rol);
					Window ventana = SwingUtilities.getWindowAncestor(loginbtn);
					ventana.dispose();
					TiendaGUI tienda = new TiendaGUI(correo, rol);
					tienda.setVisible(true);
					return true;
				}
			}

			// Si ninguno coincide
			AccesoInCorrecto("Correo o clave incorrectos");
			return false;

		} catch (Exception e) {
			AccesoInCorrecto("Error en validación de accesos: " + e.getMessage());
			return false;
		}
	}

	public static boolean AccesoCorrecto(String mensaje) {
		AlertaAnimada.mostrarAlertaAnimada(mensaje);
		return true;
	}

	public static boolean AccesoInCorrecto(String mensaje) {
		AlertaAnimada.mostrarAlertaAnimada(mensaje);
		return false;
	}

}
