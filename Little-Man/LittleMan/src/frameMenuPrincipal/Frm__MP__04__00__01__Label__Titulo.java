package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__MP__04__00__01__Label__Titulo  extends JLabel{
	
	private static final long serialVersionUID = 1L;

	ImageIcon iconTitulo = new ImageIcon("Pixels/title.png");
	
	private final int ancho = 600;
	private final int alto = 200;
	private final int posX = ( CONFIG__MP.getAncho() - ancho ) / 2;
	private final int posY = 15;
	
	public Frm__MP__04__00__01__Label__Titulo() {
    	setOpaque(false);
		setLayout(null);
		
    	Image TituloEscalado = iconTitulo.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    	ImageIcon TituloEscaladoIcon = new ImageIcon(TituloEscalado);
    	
    	setIcon(TituloEscaladoIcon);
    	setBounds(posX, posY, ancho, alto);
	}


}
