package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__Bloques__Label extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__Bloques__Label(int param, String stage) {
		ImageIcon icono = Frm__Bloques__Icon__Generator.getFactory().getScaledImageIcon(param, stage);
		//System.out.println("Frm_Bloques_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(true);
	    	imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Bloque añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
	
	public void changeState(int param, String stage) {
		ImageIcon icono = Frm__Bloques__Icon__Generator.getFactory().getScaledImageIcon(param, stage);
		//System.out.println("Frm_Bloques_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(true);
			imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Bloque añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
}
