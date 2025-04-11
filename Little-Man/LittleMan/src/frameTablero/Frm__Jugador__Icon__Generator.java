package frameTablero;

import javax.swing.ImageIcon;

public class Frm__Jugador__Icon__Generator {
	private static Frm__Jugador__Icon__Generator factory;
	private Frm__Jugador__Icon__Generator() {}
	
	public static Frm__Jugador__Icon__Generator getFactory() {
		if (factory == null) factory = new Frm__Jugador__Icon__Generator();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 200: 
				icono = new ImageIcon("Pixels/whitefront1.png");
				break;
			case 201: 
				icono = new ImageIcon("Pixels/whiteleft2.png");
				break;
			case 202: 
				icono = new ImageIcon("Pixels/whiteright2.png");
				break;
			case 203: 
				icono = new ImageIcon("Pixels/whiteup2.png");
				break;
			case 204: 
				icono = new ImageIcon("Pixels/whitedown1.png");
				break;
			case 21: 
				icono = new ImageIcon("Pixels/whitewithbomb1.png");
				break;
			case 22: 
				icono = new ImageIcon("Pixels/onFire2.png");
				break;
			case 23: 
				icono = new ImageIcon("Pixels/whitehappy1.png");
				break;
			case 250: 
				icono = new ImageIcon("Pixels/blackfront1.png");
				break;
			case 251: 
				icono = new ImageIcon("Pixels/blackleft2.png");
				break;
			case 252: 
				icono = new ImageIcon("Pixels/blackright2.png");
				break;
			case 253: 
				icono = new ImageIcon("Pixels/blackup2.png");
				break;
			case 254: 
				icono = new ImageIcon("Pixels/blackdown1.png");
				break;
			case 26: 
				icono = new ImageIcon("Pixels/blackwithbomb2.png");
				break;
			case 27: 
				icono = new ImageIcon("Pixels/onFire4.png");
				break;
			case 28: 
				icono = new ImageIcon("Pixels/blackhappy1.png");
				break;
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}


}
