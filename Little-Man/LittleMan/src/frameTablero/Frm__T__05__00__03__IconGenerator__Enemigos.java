package frameTablero;

import javax.swing.ImageIcon;

public class Frm__T__05__00__03__IconGenerator__Enemigos{
	private static Frm__T__05__00__03__IconGenerator__Enemigos factory;
	private Frm__T__05__00__03__IconGenerator__Enemigos() {}
	
	public static Frm__T__05__00__03__IconGenerator__Enemigos getFactory() {
		if (factory == null) factory = new Frm__T__05__00__03__IconGenerator__Enemigos();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 40: 
				icono = new ImageIcon("Pixels/baloon1.png");
				break;
			case 41: 
				icono = new ImageIcon("Pixels/doria1.png");
				break;
			default:
				icono = null;
				break;
		}
		
    	return icono;
	}
}
