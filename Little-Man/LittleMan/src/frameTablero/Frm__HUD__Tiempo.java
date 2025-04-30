package frameTablero;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class Frm__HUD__Tiempo extends JLayeredPane{
	private static final long serialVersionUID = 1L;
    private JLabel tiempoLabel;


	private static Frm__HUD__Tiempo tiempo;

	
	private Frm__HUD__Tiempo() {

		setLayout(null);
	}
	
	public static Frm__HUD__Tiempo getHUDTiempo(){
		if (tiempo == null) tiempo = new Frm__HUD__Tiempo();
		return tiempo;
	}
		
	public JLabel tiempoEnPantalla() {


    	tiempoLabel = new JLabel();
        tiempoLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        tiempoLabel.setForeground(Color.WHITE);
        tiempoLabel.setBackground(Color.BLACK);
        tiempoLabel.setOpaque(true);
        tiempoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        tiempoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // algo de padding

        return tiempoLabel;
    }
	
	
	public void gestionarTemporizador(int tiempo) {
    	if (tiempoLabel != null) {
            tiempoLabel.setText("TIME: " + tiempo);
        }
    }

	public void resetTiempo() {
		tiempoLabel=null;
	}
    
}