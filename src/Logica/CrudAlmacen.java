package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Persistencia.ConexionDBMysql;

public class CrudAlmacen {
	
	static Connection conexion = ConexionDBMysql.getInstancia().getConnection();
	
	public static void CrudAddAlmacen (List<String> listProductos) {
	
		
		String sqlProductos ="INSERT INTO producto(producto,stock,estatus,precio_Proveedor, "
				+ "Precio_Venta,fecha_Ingreso ,Descripción,Usuario_recibe) VALUES (?,?,?,?,?,?,?,?) ";	
		
		try {
			PreparedStatement psProductos = conexion.prepareStatement(sqlProductos);
			int pos =1;
			int posList = 0;
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.setString(pos++,listProductos.get(posList++).toString() );
			psProductos.executeUpdate();
			
//			JOptionPane.showMessageDialog( null, "✅ Producto guardado correctamente.");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "No se guardo  el  producto"+ ex.getMessage());
		}
		
	}
	
	

}
