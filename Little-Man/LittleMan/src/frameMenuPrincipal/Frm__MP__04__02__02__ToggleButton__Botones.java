package frameMenuPrincipal;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import sonido.SoundManager;


public class Frm__MP__04__02__02__ToggleButton__Botones extends JToggleButton{
	
	private static final long serialVersionUID = 1L;
	
	private final int[] dimensiones = new int[] {63,51};
	private final String[][] imagenes = new String[][]
			{{"Pixels/Selector_dificultad_0_on.png","Pixels/Selector_dificultad_0_off.png"},
			 {"Pixels/Selector_dificultad_1_on.png","Pixels/Selector_dificultad_1_off.png"},
			 {"Pixels/Selector_dificultad_2_on.png","Pixels/Selector_dificultad_2_off.png"},
			 {"Pixels/Selector_dificultad_3_on.png","Pixels/Selector_dificultad_3_off.png"}};

	private final String[][] nombres = new String[][] {
		{"PACIFICO",	"FACIL",	"NORMAL",	"DIFICIL"},
		{"APAGADO",		"BAJO",		"MEDIO",	"ALTO"},
		{"ALEATORIO",	"NORMAL",	"NO_DURO",	"VACIO"}
	};
	
	
	public Frm__MP__04__02__02__ToggleButton__Botones(int fila, int boton) {
    	setOpaque(false);
		setLayout(null);
		
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);        
		setBorderPainted(false);
		setPreferredSize(new Dimension(dimensiones[0], dimensiones[1]));
		
		setIcon(seleccionarIcon(imagenes[boton][1]));				//
		setSelectedIcon(seleccionarIcon(imagenes[boton][0]));		//
		
		setFocusable(false);
		
		setName(nombres[fila][boton]);
		
		setBounds(boton*dimensiones[0], 0, dimensiones[0], dimensiones[1]);
		if (boton == 1) doClick();
		setVisible(true);
		
		addActionListener(new ControllerBoton());
	}
	
	private ImageIcon seleccionarIcon (String image) {
		ImageIcon personajeSinEscalarIcon = new ImageIcon(image);
		Image personajesEscalado = personajeSinEscalarIcon.getImage().getScaledInstance(dimensiones[0], dimensiones[1], Image.SCALE_SMOOTH);
		ImageIcon personajeEscaladoIcon = new ImageIcon(personajesEscalado);
		
		return personajeEscaladoIcon;
	}
		
	
	class ControllerBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String anterior= Frm__00__Frame_Principal.getMenuPrincipal().getAnteriorVolumen();
			Frm__00__Frame_Principal.getMenuPrincipal().hacerSonido("SELECT_MENU"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
			Frm__00__Frame_Principal.getMenuPrincipal().pararSonido("MUSIC_MENU"+anterior);
			Frm__00__Frame_Principal.getMenuPrincipal().ponerImagenCombinadaMenuOpciones();
			Frm__00__Frame_Principal.getMenuPrincipal().hacerMusica("MUSIC_MENU"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
			

		}
	}
}
