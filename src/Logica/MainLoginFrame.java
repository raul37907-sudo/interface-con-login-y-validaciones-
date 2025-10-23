package Logica;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import IguTablaUsuarios.CrearTablaUser;
import IguTablaUsuarios.CrearVentanaDAO;
import iguVentas.Almacen;   


@SuppressWarnings("unused")
public class MainLoginFrame {

	public static void main(String[] args) {
		//JOptionPane.showMessageDialog(null, "");
	//CrearTablaDAO.crearVentanaDao();   // crea la  ventana  usuarios  crud  como  public static void 
//		CrearVentanaDAO crearVentanaDao = new CrearVentanaDAO();
//		crearVentanaDao.setVisible(true);
		
		// la tabla de usuarios  
		
//		CrearTablaUser crearTabla = new CrearTablaUser();  // instancia  la  creacion  de la ventana  a tabla  de usuarios
//		crearTabla.creaVentana("","");

		// te permite ver  el  flujo  desde login
		
		IguLogin.PantallaLogin pantallaPass = new IguLogin.PantallaLogin();
		SwingUtilities.invokeLater(() -> {  //ejecuta el  codigo  en hilo event dispach thread (edt)  -> expresion lamda para ejecutar  codigo 
			pantallaPass.setVisible(true);
        });
		
		
		// la ventana  del  form  del  ventas 
		
//		iguVentas.TiendaGUI Pantallaentas = new iguVentas.TiendaGUI();
//		SwingUtilities.invokeLater(() -> {  //ejecuta el  codigo  en hilo event dispach thread (edt)  -> expresion lamda para ejecutar  codigo 
//			Pantallaentas.setVisible(true);
//		});
	
		// VISTA ALMACEN  
		
//		Almacen almacen   = new Almacen ("yo","TU");
//		SwingUtilities.invokeLater(() ->{
//			almacen.setVisible(true);
//		});
		
		
		
		
}
	
}