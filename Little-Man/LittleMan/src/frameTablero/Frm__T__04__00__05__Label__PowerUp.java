package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__T__04__00__05__Label__PowerUp extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__T__04__00__05__Label__PowerUp(int param) {
		ImageIcon icono = Frm__T__05__00__05__IconGenerator__PowerUp.getFactory().getScaledImageIcon(param);
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
		ImageIcon icono = Frm__T__05__00__05__IconGenerator__PowerUp.getFactory().getScaledImageIcon(param);
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
