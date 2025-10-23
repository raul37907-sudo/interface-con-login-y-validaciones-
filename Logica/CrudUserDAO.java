package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import IguLogin.AlertaAnimada;
import IguTablaUsuarios.CrearVentanaDAO;
import Persistencia.ConexionDBMysql;

public class CrudUserDAO {

	static Connection conexion = ConexionDBMysql.getInstancia().getConnection();

	
	public static void borrarUsuario (String idBorrar) {
		int idEliminar = Integer.parseInt(idBorrar);
		
		String sqlborrar = " DELETE FROM Usuario WHERE id_usuario = ?";
		try {
			PreparedStatement psBorrar = conexion.prepareStatement(sqlborrar);
			psBorrar.setInt(1, idEliminar);
			int filaAfectada = psBorrar.executeUpdate();
			JOptionPane.showMessageDialog(null,
			filaAfectada > 0 ? " Se Elimino De Forma Correcta " : "No Se Encontro Usuario");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"ERROR " +	e.getMessage());
		}
		
	}
	
	public static boolean InsertarUser(String nombre, String paterno, String materno, String qlFecha, String correo,
			int estatus, int app, int rol, String pass) {

		 if (CrearVentanaDAO.correoYaExiste(correo)) {
		        JOptionPane.showMessageDialog(null, "⚠️ El correo ya está registrado. Ingresa uno diferente.");
		        return false;
		    }
		
		
		String sql = "INSERT INTO Usuario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, e_mail, estatus_activo, id_app, id_rol, pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try  {
			PreparedStatement ps = conexion.prepareStatement(sql);
			int pos = 1;
			ps.setString(pos++, nombre);
			ps.setString(pos++, paterno);
			ps.setString(pos++, materno);
			ps.setString(pos++, (qlFecha));
			ps.setString(pos++, correo);
			ps.setInt(pos++, estatus);
			ps.setInt(pos++, app);
			ps.setInt(pos++, rol);
			ps.setString(pos++, pass);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "✅ Usuario guardado correctamente.");
	        return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "x Error al guardar Datos" + e.getMessage());
	        return false;
		}
	}

	public static void ActualizarUsuario(List<String> listactualizar) {

		try {
			String sql = "UPDATE Usuario SET nombre=?, apellido_paterno=?, apellido_materno=?, fecha_nacimiento=?, e_mail=?, estatus_activo=?, id_app=?, id_rol=? WHERE id_usuario=? ";
			PreparedStatement stmtActualizar = conexion.prepareStatement(sql);
			int pos = 1;
			int list = 0;
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));
			stmtActualizar.setString(pos++, listactualizar.get(list++));

			int filas = stmtActualizar.executeUpdate();
			if (filas > 0) {
				
				JOptionPane.showMessageDialog(null," ✅  Actualización Exitosa");
			} else {
				AlertaAnimada.mostrarAlertaAnimada("  ❌ ⚠️ Usuario No Encontrado ");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			AlertaAnimada.mostrarAlertaAnimada(" ❌ ERROR AL ACTUALIZAR  EL  USUARIO  "+ ex.getMessage());
		}
	}

}
