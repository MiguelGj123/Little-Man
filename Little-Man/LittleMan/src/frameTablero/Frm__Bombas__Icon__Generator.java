package frameTablero;

import javax.swing.ImageIcon;

public class Frm__Bombas__Icon__Generator {
	private static Frm__Bombas__Icon__Generator factory;
	private Frm__Bombas__Icon__Generator() {}
	
	public static Frm__Bombas__Icon__Generator getFactory() {
		if (factory == null) factory = new Frm__Bombas__Icon__Generator();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 30: 
				icono = new ImageIcon("Pixels/bomb1.png");
				break;
			case 35: 
				icono = new ImageIcon("Pixels/bomb2.png");
				break;
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}
}
