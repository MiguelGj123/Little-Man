package frameMenuPrincipal;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Frm__MP__02__01__LayeredPane__ElementosClickables extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	Frm__MP__02__01b_LayeredPane__Personajes personajesLayeredPane;
	Frm__MP__03__01__02__Panel__IconoOpciones iconoOpciones;
	
	ArrayList<JPanel> paneles;
	
	public Frm__MP__02__01__LayeredPane__ElementosClickables() {
    	setOpaque(false);
		setLayout(null);
		
		paneles = new ArrayList<JPanel>();
		
		personajesLayeredPane = new Frm__MP__02__01b_LayeredPane__Personajes();
		add(personajesLayeredPane, Integer.valueOf(0));
		
		iconoOpciones = new Frm__MP__03__01__02__Panel__IconoOpciones();
		paneles.add(iconoOpciones);
		
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i+1));
		}
		
		//cambiar los setBounds a la clase que los invoca
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	public void seleccionar(int personaje) {
		personajesLayeredPane.seleccionar(personaje);
	}

	public void rotarjugadorDerecha() {
		personajesLayeredPane.rotarJugadorDerecha();
	}
	
	public void rotarjugadorIzquierda() {
		personajesLayeredPane.rotarJugadorIzquierda();
	}
	
	public String getJugadorSeleccionado() {
		return personajesLayeredPane.getJugadorSeleccionado();
	}
	
}
