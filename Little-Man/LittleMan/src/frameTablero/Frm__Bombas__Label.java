package frameTablero;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__Bombas__Label extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagen;

	public Frm__Bombas__Label(int param) {
		ImageIcon icono = Frm__Bombas__Icon__Generator.getFactory().getScaledImageIcon(param);
		//System.out.println("Frm_Bombas_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(false);
	    	imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Bomba añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
	
	public void changeState(int param) {
		ImageIcon icono = Frm__Bombas__Icon__Generator.getFactory().getScaledImageIcon(param);
		//System.out.println("Frm_Bombas_Laebl icono.esNull:" + icono == null);
		if (icono != null) {
			setVisible(true);
			imagen = icono;
			setIcon(imagen);
			//System.out.println("Icono Bomba añadido correctamente");
		} else {
			setVisible(false);
		}
		setOpaque(false);
	}
}
