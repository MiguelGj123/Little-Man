package frameTablero;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__Fondo__Label extends JLabel{
	private static final long serialVersionUID = 1L;
	private ImageIcon fondoNormal = new ImageIcon("Pixels/stageBack1.png");
	private ImageIcon fondoVacio  = new ImageIcon("Pixels/stageBack2.png");
	private ImageIcon fondoNoDuro = new ImageIcon("Pixels/stageBack3.png");

	private Frm__CONFIG config = Frm__CONFIG.getConfig();

	public Frm__Fondo__Label(String param) {
		System.out.println(param);
		ImageIcon fondoSeleccionado = fondoNormal;
    	if (param.equals("NORMAL" )) fondoSeleccionado = fondoNormal;
    	if (param.equals("VACIO"  )) fondoSeleccionado = fondoVacio;
    	if (param.equals("NO_DURO")) fondoSeleccionado = fondoNoDuro;
    	
    	Image fondoEscalado = fondoSeleccionado.getImage().getScaledInstance(config.getANCHO(), config.getALTO(), Image.SCALE_SMOOTH);
    	
    	ImageIcon fondoEscaladoIcon = new ImageIcon(fondoEscalado);
    	
    	setIcon(fondoEscaladoIcon);
	}
	
}
