package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__MP__04__00__04__Label__FondoEnemigos extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private static final int[][] dimensiones =
		new int[][] {{177,357},{100,216},{55,55},{51,60},{63,57},{48,48}};
	private static final int[][] posiciones = 
		new int[][] {{910,180},{-17,470},{150,500},{800,480},{880,520},{410,480}};
	private static final String[] imagenes = new String[]
			{"Pixels/boss3.png",
			 "Pixels/boss4.png",
			 "Pixels/baloon1.png",
			 "Pixels/doria2.png",
			 "Pixels/pass1.png",
			 "Pixels/bomb1.png"};
			 
	
	public Frm__MP__04__00__04__Label__FondoEnemigos (int enemigo) {
		
    	setOpaque(false);
		setLayout(null);
		
		ImageIcon EnemigoSinEscalarIcon = new ImageIcon(imagenes[enemigo]);
		Image EnemigosEscalado = EnemigoSinEscalarIcon.getImage().getScaledInstance(dimensiones[enemigo][0], dimensiones[enemigo][1], Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(EnemigosEscalado));
		
		setBounds(posiciones[enemigo][0], posiciones[enemigo][1], dimensiones[enemigo][0], dimensiones[enemigo][1]);
		setVisible(true);
	}
}
