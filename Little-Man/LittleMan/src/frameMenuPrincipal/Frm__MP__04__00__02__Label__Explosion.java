package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__MP__04__00__02__Label__Explosion extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	private final int ancho = 600;
	private final int alto = 600;
	private final int posX = ( CONFIG__MP.getAncho() - ancho ) / 2;
	private final int posY = 100;
	

	ImageIcon iconExplosion = new ImageIcon("Pixels/explotion-explode.gif");
	
	public Frm__MP__04__00__02__Label__Explosion() {
    	setOpaque(false);
		setLayout(null);
		
    	Image ExplosionEscalado = iconExplosion.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
    	ImageIcon ExplosionEscaladoIcon = new ImageIcon(ExplosionEscalado);
    	
    	setIcon(ExplosionEscaladoIcon);
    	setBounds(posX, posY, ancho, alto);
	}
	

}
