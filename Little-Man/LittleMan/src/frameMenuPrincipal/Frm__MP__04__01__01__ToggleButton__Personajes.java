package frameMenuPrincipal;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import sonido.SoundManager;


public class Frm__MP__04__01__01__ToggleButton__Personajes extends JToggleButton {	
	
	private static final long serialVersionUID = 1L;
	
	private final int personaje;
	private PersonajeController miController;
	
	private final int[][] dimensiones =
		new int[][] {{90,132},{75,139},{65,128},{171,150}};
		
	private final int[][] posiciones = 
		new int[][] {{110,290},{240,380},{695,380},{750,290}};
		
	private final String[][] imagenes = new String[][]
			{{"Pixels/bomber1.png","Pixels/bomber1_gray.png"},
			 {"Pixels/bomber2.png","Pixels/bomber2_gray.png"},
			 {"Pixels/bomber3.png","Pixels/bomber3_gray.png"},
			 {"Pixels/bomber4.png","Pixels/bomber4_gray.png"}};

	private String[] personajesNombre = 
		new String[] {"BLANCO", "NEGRO", "AZUL", "ROJO"};
			
	public Frm__MP__04__01__01__ToggleButton__Personajes(int personaje) {
		this.personaje = personaje;
	    setOpaque(false);
	    setLayout(null);
		
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);        
		setBorderPainted(false);
		setPreferredSize(new Dimension(dimensiones[personaje][0], dimensiones[personaje][1]));
		
		setIcon(seleccionarIcon(imagenes[personaje][1]));				//
		setSelectedIcon(seleccionarIcon(imagenes[personaje][0]));		//
		
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        miController = new PersonajeController();
        addMouseListener(miController); // Usar el nuevo controller
	    
		setFocusable(false);
        
	    if (personaje == 0) doClick();
	    setName(personajesNombre[personaje]);
	    
	    setBounds(posiciones[personaje][0], posiciones[personaje][1], dimensiones[personaje][0], dimensiones[personaje][1]);
	    setVisible(true);
	}

	
	private ImageIcon seleccionarIcon (String image) {
		ImageIcon personajeSinEscalarIcon = new ImageIcon(image);
		Image personajesEscalado = personajeSinEscalarIcon.getImage().getScaledInstance(dimensiones[personaje][0], dimensiones[personaje][1], Image.SCALE_SMOOTH);
		ImageIcon personajeEscaladoIcon = new ImageIcon(personajesEscalado);
		
		return personajeEscaladoIcon;
	}	
	
	class PersonajeController implements MouseListener {
		
		private void iniciarJuego() {
			String[] opcionesSeleccionadas = Frm__00__Frame_Principal.getMenuPrincipal().getOpcionesSeleccionadas();
    		Frm__00__Timer__IniciarJuego.iniciarPartida(personajesNombre[personaje], opcionesSeleccionadas[0], opcionesSeleccionadas[1], opcionesSeleccionadas[2]);
        }
		
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    	if (!Frm__MP__01__LayeredPane_Principal.getPane().opcionesAbiertas()) {
	    		Frm__00__Frame_Principal.getMenuPrincipal().pararSonido("MINI_EXPLODE"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
	    		Frm__00__Frame_Principal.getMenuPrincipal().actualizarJugadorColor(personaje);
	    		Frm__00__Frame_Principal.getMenuPrincipal().hacerSonido("MINI_EXPLODE"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
	    	}
	    }
	    
	    // Implementar otros métodos necesarios de MouseListener
	    @Override public void mouseClicked(MouseEvent e) {
	    	if (!Frm__MP__01__LayeredPane_Principal.getPane().opcionesAbiertas()) {
	    		Frm__00__Frame_Principal.getMenuPrincipal().pararSonido("MUSIC_MENU"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
	    		iniciarJuego();
	    		Frm__00__Frame_Principal.getMenuPrincipal().hacerSonido("BOMB_EXPLODE"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());
	    		
	    	}
	    }
	    
	    
	    @Override public void mousePressed(MouseEvent e) {}
	    @Override public void mouseReleased(MouseEvent e) {}
	    @Override public void mouseExited(MouseEvent e) {}
	}


	

}
