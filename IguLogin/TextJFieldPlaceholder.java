package IguLogin;

	import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

	public class TextJFieldPlaceholder {

		public static void aplicarPlaceholder(JTextField correo, String textoPlaceholder) { // este es para el JTextField 
	        correo.setText(textoPlaceholder);
	        correo.setForeground(Color.GRAY);

	        correo.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (correo.getText().equals(textoPlaceholder)) {
	                    correo.setText("");
	                    correo.setForeground(Color.BLACK);
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (correo.getText().isEmpty()) {
	                    correo.setText(textoPlaceholder);
	                    correo.setForeground(Color.GRAY);
	                }
	            }
	        });
	    }

	    public static void aplicarPlaceholder(JPasswordField password, String textoPlaceholder) { // este es para el JPasswordField 
	        password.setEchoChar((char) 0); // Mostrar texto plano
	        password.setText(textoPlaceholder);
	        password.setForeground(Color.GRAY);

	        password.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (String.valueOf(password.getPassword()).equals(textoPlaceholder)) {
	                    password.setText("");
	                    password.setForeground(Color.BLACK);
	                    password.setEchoChar('•'); // Activar ocultamiento
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (String.valueOf(password.getPassword()).isEmpty()) {
	                    password.setEchoChar((char) 0); // Mostrar texto plano
	                    password.setText(textoPlaceholder);
	                    password.setForeground(Color.GRAY);
	                }
	            }
	        });
	    }
	

}
