package IguLogin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class AlertaAnimada {
	public static void mostrarAlertaAnimada(String mensaje) {
		JWindow ventana = new JWindow();

		JLabel etiqueta = new JLabel(mensaje);
		
		@SuppressWarnings("unused")
		boolean validarMensaje = mensaje == "LOGIN CORRECTO" ? acceder(etiqueta) : denegar(etiqueta);

		ventana.getContentPane().add(etiqueta);
		ventana.pack();

		// Posición inicial: fuera de pantalla (abajo)
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int x = pantalla.width - ventana.getWidth() -670 ;
		int yFinal = pantalla.height - ventana.getHeight()  -250;
		int yInicio = pantalla.height;

		ventana.setLocation(x, yInicio);
		ventana.setVisible(true);

		// Animación de deslizamiento hacia arriba
		Timer animacion = new Timer(100, null);
		animacion.addActionListener(e -> {
			Point posicion = ventana.getLocation();
			if (posicion.y > yFinal) {
				ventana.setLocation(posicion.x , posicion.y - 45);//con est juegas  arriba abajo izquierda derecha
			} else {
				((Timer) e.getSource()).stop();
				// Cerrar después de 2 segundos
				new Timer(600, ev -> ventana.dispose()).start();
			}
		});
		animacion.start();
	}

	public static boolean acceder(JLabel etiqueta) {
		etiqueta.setOpaque(true);
		etiqueta.setBackground(Color.GREEN); // Verde oscuro
		etiqueta.setForeground(Color.WHITE);
		etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
		etiqueta.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		return  true;

	}
	public static boolean denegar(JLabel etiqueta) {
		etiqueta.setOpaque(true);
		etiqueta.setBackground(Color.RED); // Verde oscuro
		etiqueta.setForeground(Color.WHITE);
		etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
		etiqueta.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		return  true;

	}
}
