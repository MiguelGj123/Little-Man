package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//JLabel personalizado que representa un bloque gráfico y puede cambiar de estado.
public class Frm__T__04__00__01__Label__Bloques extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__T__04__00__01__Label__Bloques() {
		ImageIcon icono = Frm__T__05__00__01__IconGenerator__Bloques.getFactory().getScaledImageIcon(10, null);
		if (icono != null) {
			setVisible(true);
	    	imagen = icono;
			setIcon(imagen);
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
	
	// Cambia la imagen del bloque según el código y estado actual.
	public void changeState(int param, String stage) {
		ImageIcon icono = Frm__T__05__00__01__IconGenerator__Bloques.getFactory().getScaledImageIcon(param, stage);
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
