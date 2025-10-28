package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Persistencia.ConexionDBMysql;
import iguVentas.Almacen;

public class CrudAlmacen {

	static Connection conexion = ConexionDBMysql.getInstancia().getConnection();

	public static void modificarAlmacen(String datoaActualizar[],DefaultTableModel modelo) {

		try {
			String sqlModificaAlmacen = "UPDATE producto SET producto=?, stock=?, precio_Proveedor=?,Precio_Venta =?, "
					+ "Descripción=?, Usuario_modifica =?, puesto_modifica=?, fecha_Modificacion=? WHERE id_Producto=? ";
			PreparedStatement stmtActualiza = conexion.prepareStatement(sqlModificaAlmacen);
			int posLis = 0;
			int posSql = 1;
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			stmtActualiza.setString(posSql++, datoaActualizar[posLis++]);
			int filasAfectadas = stmtActualiza.executeUpdate();
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(null, "✅ Producto actualizado correctamente.");
			modelo.setRowCount(0);
				Almacen.obtenerProductos(modelo);
			} else {
				JOptionPane.showMessageDialog(null, "⚠️ No se encontró el producto con ese ID.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede actualizar la fila  " + e.getMessage());
		}

	}

	public static List<Object[]> CrudBuscarEnAlmacen(String buscarProducto) {

		String busquedaQuery = "SELECT * FROM producto WHERE LOWER(producto) LIKE  LOWER(?) ";
		List<Object[]> ListBusqueda = new ArrayList<>();
		try {
			PreparedStatement ptmtBusqueda = conexion.prepareStatement(busquedaQuery);
			ptmtBusqueda.setString(1, "%" + buscarProducto + "%");
			ResultSet rsBusqueda = ptmtBusqueda.executeQuery();
			while (rsBusqueda.next()) {
				Object[] fila = { rsBusqueda.getString("Id_Producto"), rsBusqueda.getString("producto"),
						rsBusqueda.getInt("stock"), rsBusqueda.getString("estatus"),
						rsBusqueda.getBigDecimal("precio_Proveedor"), rsBusqueda.getBigDecimal("Precio_Venta"),
						rsBusqueda.getTimestamp("fecha_Ingreso"), rsBusqueda.getString("Descripción"),
						rsBusqueda.getString("Usuario_recibe") };
				ListBusqueda.add(fila);

			}
			return ListBusqueda;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Busqueda no exitosa" + e.getMessage());
		}
		return null;
	}

	public static void CrudAddAlmacen(List<String> listProductos) {

		String sqlProductos = "INSERT INTO producto(producto,stock,estatus,precio_Proveedor, "
				+ "Precio_Venta,fecha_Ingreso ,Descripción,Usuario_recibe,puesto_recibe) VALUES (?,?,?,?,?,?,?,?,?) ";

		try {
			PreparedStatement psProductos = conexion.prepareStatement(sqlProductos);
			int pos = 1;
			int posList = 0;
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.setString(pos++, listProductos.get(posList++).toString());
			psProductos.executeUpdate();

//			JOptionPane.showMessageDialog( null, "✅ Producto guardado correctamente.");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "No se guardo  el  producto" + ex.getMessage());
		}

	}

}
