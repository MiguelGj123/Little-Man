package frameTablero;

import javax.swing.ImageIcon;

public class Frm__T__05__00__02__IconGenerator__Bombas {
	private static Frm__T__05__00__02__IconGenerator__Bombas factory;
	private Frm__T__05__00__02__IconGenerator__Bombas() {}
	
	public static Frm__T__05__00__02__IconGenerator__Bombas getFactory() {
		if (factory == null) factory = new Frm__T__05__00__02__IconGenerator__Bombas();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 30: 
				icono = new ImageIcon("Pixels/bomb1.png");
				break;
			case 301: 
				icono = new ImageIcon("Pixels/bomb3.png");
				break;
			case 35: 
				icono = new ImageIcon("Pixels/bomb2.png");
				break;
			case 351: 
				icono = new ImageIcon("Pixels/bomb4.png");
				break;
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}
}
