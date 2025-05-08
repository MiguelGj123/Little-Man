package frameMenuPrincipal;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;


public class Frm__MP__03__01__01__Panel__Personajes extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Frm__MP__04__01__01__ToggleButton__Personajes[] personajes;
	ButtonGroup grupo;
	

	public Frm__MP__03__01__01__Panel__Personajes() {
    	setOpaque(false);
		setLayout(null);
		
		personajes = new Frm__MP__04__01__01__ToggleButton__Personajes[4];
		grupo = new ButtonGroup();
		
		for (int personaje = 0; personaje < personajes.length; personaje++) {
			personajes[personaje] = new Frm__MP__04__01__01__ToggleButton__Personajes(personaje);
			grupo.add(personajes[personaje]);
			add(personajes[personaje]);
		}
		
		setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}

	public String getJugadorSeleccionado() {
		for (int i = 0; i < personajes.length; i++) {
			if (personajes[i].isSelected()) return personajes[i].getName();
		}
		return null;
	}
	
	public void seleccionar(int personaje) {
		personajes[personaje].doClick();
	}
	
	public int pressRight() {
		for (int i = 0; i < personajes.length - 1; i++) {
			if (personajes[i].isSelected()) return i+1;
		}
		return - 1;
	}
	
	public int pressLeft() {
		for (int i = 1; i < personajes.length; i++) {
			if (personajes[i].isSelected()) return i-1;
		}
		return - 1;
	}
	
	
}
