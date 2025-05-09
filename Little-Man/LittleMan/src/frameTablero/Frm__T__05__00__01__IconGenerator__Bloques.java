package frameTablero;

import javax.swing.ImageIcon;

//Fábrica Singleton encargada de generar iconos escalados para los bloques en función de su tipo.
public class Frm__T__05__00__01__IconGenerator__Bloques {
	private static Frm__T__05__00__01__IconGenerator__Bloques factory;
	private Frm__T__05__00__01__IconGenerator__Bloques() {}
	
	public static Frm__T__05__00__01__IconGenerator__Bloques getFactory() {
		if (factory == null) factory = new Frm__T__05__00__01__IconGenerator__Bloques();
		return factory;
	}
	
	// Devuelve un icono escalado en función del tipo de bloque y estado.
	public ImageIcon getScaledImageIcon(int tipo, String param) {
		ImageIcon icono;
		
		switch (tipo) {
			case 10: 
				icono = new ImageIcon("Pixels/hard1.png");
				break;
			case 11:
				icono = new ImageIcon("Pixels/soft3.png");
				break;
			case 13:
				icono = new ImageIcon("Pixels/miniBlast1.gif");
				break;
			case 15:
				icono = new ImageIcon("Pixels/hard2.png");
				break;
			default:
				icono = null;
				break;
		}
		
    	return icono;
	}
}
