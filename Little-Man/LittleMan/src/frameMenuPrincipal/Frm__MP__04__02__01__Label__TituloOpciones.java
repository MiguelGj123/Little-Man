package frameMenuPrincipal;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Frm__MP__04__02__01__Label__TituloOpciones extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	private final int alto = 156;
	private final int ancho = 435;
	private final int posX = 100;
	private final int posY = 160;
	
	private final String fondoOpciones = "Pixels/Selector_nombres.png";
			/*
			{{"Pixels/Selector_dificultad_0_off.png","Pixels/Selector_dificultad_0_on.png"},
			 {"Pixels/Selector_dificultad_1_off.png","Pixels/Selector_dificultad_1_on.png"},
			 {"Pixels/Selector_dificultad_2_off.png","Pixels/Selector_dificultad_2_on.png"},
			 {"Pixels/Selector_dificultad_3_off.png","Pixels/Selector_dificultad_3_on.png"}};
			 */
	public Frm__MP__04__02__01__Label__TituloOpciones() {
    	setOpaque(false);
		setLayout(null);
		
		ImageIcon iconFondo = new ImageIcon(fondoOpciones);
    	Image fondoEscalado = iconFondo.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    	ImageIcon fondoEscaladoIcon = new ImageIcon(fondoEscalado);
    	
    	setIcon(fondoEscaladoIcon);
    	setBounds(posX, posY, ancho, alto);
	}
}
