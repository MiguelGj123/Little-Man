package frameTablero;

import javax.swing.ImageIcon;

public class Frm__Bloques__Icon__Generator {
	private static Frm__Bloques__Icon__Generator factory;
	private Frm__Bloques__Icon__Generator() {}
	
	public static Frm__Bloques__Icon__Generator getFactory() {
		if (factory == null) factory = new Frm__Bloques__Icon__Generator();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
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
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}
}
