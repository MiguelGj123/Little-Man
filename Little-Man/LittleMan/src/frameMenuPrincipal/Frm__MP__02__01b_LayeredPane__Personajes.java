package frameMenuPrincipal;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Frm__MP__02__01b_LayeredPane__Personajes extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	Frm__MP__03__01__00__Panel__Personajes_Exp personajesExpPanel;
	Frm__MP__03__01__01__Panel__Personajes personajesPanel;
	
	ArrayList<JPanel> paneles;
	
	public Frm__MP__02__01b_LayeredPane__Personajes() {
    	setOpaque(false);
		setLayout(null);
		
		paneles = new ArrayList<JPanel>();
		
		personajesExpPanel = new Frm__MP__03__01__00__Panel__Personajes_Exp();
		paneles.add(personajesExpPanel);
		
		personajesPanel = new Frm__MP__03__01__01__Panel__Personajes();
		paneles.add(personajesPanel);
		
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
		//cambiar los setBounds a la clase que los invoca
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	public void seleccionar(int personaje) {
		if (personaje >= 0) {
			personajesPanel.seleccionar(personaje);
			personajesExpPanel.explotar(personaje);
		}
	}

	public void rotarJugadorDerecha() {
		seleccionar(personajesPanel.pressRight());
	}
	
	public void rotarJugadorIzquierda() {
		seleccionar(personajesPanel.pressLeft());
	}
	
	public String getJugadorSeleccionado() {
		return personajesPanel.getJugadorSeleccionado();
	}

}
