package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__T__04__00__04__Label__Jugador extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__T__04__00__04__Label__Jugador(int param) {
		ImageIcon icono = Frm__T__05__00__04__IconGenerator__Jugador.getFactory().getScaledImageIcon(param);
		if (icono != null) {
			setVisible(false);
	    	imagen = icono;
			setIcon(imagen);
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
	
	public void changeState(int param) {
		ImageIcon icono = Frm__T__05__00__04__IconGenerator__Jugador.getFactory().getScaledImageIcon(param);
		if (icono != null) {
			setVisible(true);
			imagen = icono;
			setIcon(imagen);
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}




}
