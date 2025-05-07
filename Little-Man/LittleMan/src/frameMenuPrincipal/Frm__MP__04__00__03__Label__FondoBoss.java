package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__MP__04__00__03__Label__FondoBoss extends JLabel{
	
	private static final long serialVersionUID = 1L;

	ImageIcon iconFondoBoss = new ImageIcon("Pixels/boss2.png");
	
	private final int ancho = 280;
	private final int alto = 310;
	private final int posX = ( CONFIG__MP.getAncho() - ancho ) / 2;
	private final int posY = 200;
	
	public Frm__MP__04__00__03__Label__FondoBoss() {
    	setOpaque(false);
		setLayout(null);
		
    	Image FondoBossEscalado = iconFondoBoss.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    	ImageIcon FondoBossEscaladoIcon = new ImageIcon(FondoBossEscalado);
    	
    	setIcon(FondoBossEscaladoIcon);
    	setBounds(posX, posY, ancho, alto);
	}

}
