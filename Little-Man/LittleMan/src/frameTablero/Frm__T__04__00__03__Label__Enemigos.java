package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__T__04__00__03__Label__Enemigos extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__T__04__00__03__Label__Enemigos(int param) {
		ImageIcon icono = Frm__T__05__00__03__IconGenerator__Enemigos.getFactory().getScaledImageIcon(param);
		//System.out.println("Frm_Enemigos_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(false);
	    	imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Enemigo añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
	
	public void changeState(int param) {
		ImageIcon icono = Frm__T__05__00__03__IconGenerator__Enemigos.getFactory().getScaledImageIcon(param);
		//System.out.println("Frm_Enemigos_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(true);
			imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Enemigo añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}


}
