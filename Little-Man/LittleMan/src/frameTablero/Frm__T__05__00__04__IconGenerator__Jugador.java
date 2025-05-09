package frameTablero;

import javax.swing.ImageIcon;

public class Frm__T__05__00__04__IconGenerator__Jugador {
	private static Frm__T__05__00__04__IconGenerator__Jugador factory;
	private Frm__T__05__00__04__IconGenerator__Jugador() {}
	
	public static Frm__T__05__00__04__IconGenerator__Jugador getFactory() {
		if (factory == null) factory = new Frm__T__05__00__04__IconGenerator__Jugador();
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
			case 2010: 
				icono = new ImageIcon("Pixels/bluefront1.png");
				break;
			case 2011: 
				icono = new ImageIcon("Pixels/blueleft2.png");
				break;
			case 2012: 
				icono = new ImageIcon("Pixels/blueright2.png");
				break;
			case 2013: 
				icono = new ImageIcon("Pixels/blueup2.png");
				break;
			case 2014: 
				icono = new ImageIcon("Pixels/bluedown1.png");
				break;
			case 211: 
				icono = new ImageIcon("Pixels/bluewithbomb3.png");
				break;
			case 221: 
				icono = new ImageIcon("Pixels/onFire2.png");
				break;
			case 231: 
				icono = new ImageIcon("Pixels/bluehappy1.png");
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
			case 2510: 
				icono = new ImageIcon("Pixels/redfront1.png");
				break;
			case 2511: 
				icono = new ImageIcon("Pixels/redleft2.png");
				break;
			case 2512: 
				icono = new ImageIcon("Pixels/redright2.png");
				break;
			case 2513: 
				icono = new ImageIcon("Pixels/redup2.png");
				break;
			case 2514: 
				icono = new ImageIcon("Pixels/reddown1.png");
				break;
			case 261: 
				icono = new ImageIcon("Pixels/redwithbomb4.png");
				break;
			case 271: 
				icono = new ImageIcon("Pixels/onFire4.png");
				break;
			case 281: 
				icono = new ImageIcon("Pixels/redhappy1.png");
				break;
			default:
				icono = null;
				break;
		}
		
    	return icono;
	}


}
