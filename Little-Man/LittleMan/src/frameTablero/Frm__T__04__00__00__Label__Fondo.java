package frameTablero;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__T__04__00__00__Label__Fondo extends JLabel{
	private static final long serialVersionUID = 1L;
	private ImageIcon fondoNormal = new ImageIcon("Pixels/stageBack1.png");
	private ImageIcon fondoVacio  = new ImageIcon("Pixels/stageBack2.png");
	private ImageIcon fondoNoDuro = new ImageIcon("Pixels/stageBack3.png");

	public Frm__T__04__00__00__Label__Fondo() 
	{}
	
	public void iniciarFondo(String fondo)
	{
		ImageIcon fondoSeleccionado = fondoNormal;
    	if (fondo.equals("NORMAL" )) fondoSeleccionado = fondoNormal;
    	if (fondo.equals("VACIO"  )) fondoSeleccionado = fondoVacio;
    	if (fondo.equals("NO_DURO")) fondoSeleccionado = fondoNoDuro;
    	
		Image fondoEscalado = fondoSeleccionado.getImage().getScaledInstance(T_CFG.ANCHO_PANE, T_CFG.ANCHO_PANE, Image.SCALE_SMOOTH);
    	
    	ImageIcon fondoEscaladoIcon = new ImageIcon(fondoEscalado);
    	
    	setIcon(fondoEscaladoIcon);
	}
	
}
