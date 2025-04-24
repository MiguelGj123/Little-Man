package frameTablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class Frm__HUD__Puntuacion extends JLayeredPane{
	private static final long serialVersionUID = 1L;
    private JLabel puntuacionLabel;
    private Double puntos=0.;


	private static Frm__HUD__Puntuacion puntuacion;

	
	private Frm__HUD__Puntuacion() {
		setLayout(null);
	}
	
	public static Frm__HUD__Puntuacion getHUDPuntuacion(){
		if (puntuacion == null) puntuacion = new Frm__HUD__Puntuacion();
		return puntuacion;
	}
		
	public JLabel puntuacionEnPantalla() {


    	puntuacionLabel = new JLabel();
        puntuacionLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        puntuacionLabel.setForeground(Color.WHITE);
        puntuacionLabel.setBackground(Color.BLACK);
        puntuacionLabel.setOpaque(true);
        puntuacionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        puntuacionLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // algo de padding
        puntuacionLabel.setPreferredSize(new Dimension(200, 45)); 
        

        return puntuacionLabel;
    }
	
	public void gestionarPuntuacion(Double pPuntuacion) {
    	if (puntuacionLabel != null) {
    		if (pPuntuacion==-1.) {
    			puntos=1.;
    		} 
    		puntos=pPuntuacion+puntos;
            puntuacionLabel.setText("Points: " + (int) (double) puntos);
        }
    }
    
}
