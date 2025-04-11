package frameTablero;

import javax.swing.ImageIcon;

public class Frm__Enemigos__Icon__Generator{
	private static Frm__Enemigos__Icon__Generator factory;
	private Frm__Enemigos__Icon__Generator() {}
	
	public static Frm__Enemigos__Icon__Generator getFactory() {
		if (factory == null) factory = new Frm__Enemigos__Icon__Generator();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 40: 
				icono = new ImageIcon("Pixels/baloon1.png");
				break;
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}
}
