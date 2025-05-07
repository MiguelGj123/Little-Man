package frameTablero;

import javax.swing.ImageIcon;

public class Frm__T__05__00__05__IconGenerator__PowerUp {
	private static Frm__T__05__00__05__IconGenerator__PowerUp factory;
	private Frm__T__05__00__05__IconGenerator__PowerUp() {}
	
	public static Frm__T__05__00__05__IconGenerator__PowerUp getFactory() {
		if (factory == null) factory = new Frm__T__05__00__05__IconGenerator__PowerUp();
		return factory;
	}
	
	public ImageIcon getScaledImageIcon(int tipo) {
		ImageIcon icono;
		
		switch (tipo) {
			case 600: 
				icono = new ImageIcon("Pixels/PowerupAleatorio.png");
				break;
			case 601: 
				icono = new ImageIcon("Pixels/PowerupBombsUp.png");
				break;
			case 602: 
				icono = new ImageIcon("Pixels/PowerupBombsDown.png");
				break;
			case 603: 
				icono = new ImageIcon("Pixels/PowerupPatada.png");
				break;
			case 604: 
				icono = new ImageIcon("Pixels/PowerupPinchoBomba.png");
				break;
			case 605: 
				icono = new ImageIcon("Pixels/PowerupFireUp.png");
				break;
			case 606: 
				icono = new ImageIcon("Pixels/PowerupFireDown.png");
				break;
			case 607: 
				icono = new ImageIcon("Pixels/PowerupInvencibilidad.png");
				break;
			case 608: 
				icono = new ImageIcon("Pixels/PowerupPuntos.png");
				break;
			case 609: 
				icono = new ImageIcon("Pixels/PowerupTickUp.png");
				break;
			case 610: 
				icono = new ImageIcon("Pixels/PowerupTickDown.png");
				break;
			case 611: 
				icono = new ImageIcon("Pixels/PowerupTimeUp.png");
				break;
			case 612: 
				icono = new ImageIcon("Pixels/PowerupVidaUp.png");
				break;
			case 613: 
				icono = new ImageIcon("Pixels/PowerupVidaRec.png");
				break;
			default:
				icono = null;
				break;
		}
		//System.out.println("Cargando imagen desde: " + icono.toString()); // Añadir debug
		
    	return icono;
	}


}
