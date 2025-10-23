package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ConexionDBMysql {
	 /* clase usando  Sigleton crea una  unica  instancia accesible globalmente ,  solo
	crea una  conexion  durante  toda la ejecucion, evita  errores multiples de conexiones abiertas 
 controla su propia creacion */
private static ConexionDBMysql instancia;
private Connection conexion;

private final String URL = "jdbc:mysql://localhost/login_cesar_igu";
private final String USER = "root";
private final String LOGIN = "";

private  ConexionDBMysql(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(URL,USER,LOGIN);////////////////
		//System.out.println("✅ Conexion Establecida Exitosamente");
		
	} catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(
			    null,                      // Componente padre
			    " ❌ ❌ ❌ Error en la conexión",    // Mensaje
			    "Error",                   // Título de la ventana
			    JOptionPane.ERROR_MESSAGE  // Tipo de mensaje
			);		//System.out.println(" ❌ ERROR EN  LA  CONEXION A LA BASE DE DATOS " + e.getMessage());
	}
}

//metodo publico para  crear  la instancia 
public static ConexionDBMysql getInstancia() {
	if (instancia == null) {
		instancia = new ConexionDBMysql();
	}
	return instancia;
}

// metodO para ESTABLECER  L A CONEXIÓN 
public Connection getConnection() {
	return conexion;
}
}
