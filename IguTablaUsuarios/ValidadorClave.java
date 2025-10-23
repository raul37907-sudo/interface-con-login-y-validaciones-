package IguTablaUsuarios;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ValidadorClave {

    private final JTextField campoOriginal;
    private final JTextField campoConfirmacion;
    private final JLabel mensaje;

    public ValidadorClave(JTextField campoOriginal, JTextField campoConfirmacion, JLabel mensaje) {
        this.campoOriginal = campoOriginal;
        this.campoConfirmacion = campoConfirmacion;
        this.mensaje = mensaje;
        agregarListener();
    }
    
    

    private void agregarListener() {
         DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificar();
            }
        };
        campoOriginal.getDocument().addDocumentListener(listener);
        campoConfirmacion.getDocument().addDocumentListener(listener);
    }

    private void verificar() {
        String clave1 = campoOriginal.getText();
        String clave2 = campoConfirmacion.getText();
        
        
        
        if (clave2.equals(clave1)) {
            
            	campoConfirmacion.setBackground(Color.white);
                mensaje.setText("✔ Las claves coinciden");
                mensaje.setForeground(Color.BLUE);
                campoOriginal.setBackground(Color.green);
                campoConfirmacion.setBackground(Color.green);
            
        } else {
        	
        	
            campoConfirmacion.setBackground(Color.pink);
            campoOriginal.setBackground(Color.pink);
            mensaje.setText("✘ Las claves no coinciden");
            mensaje.setForeground(Color.RED);
        }
    }

    public boolean clavesCoinciden() {
        return campoOriginal.getText().equals(campoConfirmacion.getText());
        
    }
}
