package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//Label que carga y escala la imagen de fondo del menú.
public class Frm__MP__04__00__00__Label__Fondo extends JLabel{
	
	private static final long serialVersionUID = 1L;
	ImageIcon iconFondo = new ImageIcon("Pixels/back.png");
	
	public Frm__MP__04__00__00__Label__Fondo() {
    	setOpaque(false);
		setLayout(null);
		
    	Image fondoEscalado = iconFondo.getImage().getScaledInstance(CONFIG__MP.getAncho(), CONFIG__MP.getAlto(), Image.SCALE_SMOOTH);
    	ImageIcon fondoEscaladoIcon = new ImageIcon(fondoEscalado);
    	
    	setIcon(fondoEscaladoIcon);
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	
}
