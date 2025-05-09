package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__01__00__Panel__Personajes_Exp extends JPanel{

	private static final long serialVersionUID = 1L;
	Frm__MP__04__01__00__Label__Personajes_Exp exp_Personajes;
	
	public Frm__MP__03__01__00__Panel__Personajes_Exp() {
    	setOpaque(false);
		setLayout(null);
		
		exp_Personajes = new Frm__MP__04__01__00__Label__Personajes_Exp();
		add(exp_Personajes);
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	
	public void explotar(int personaje) {
		exp_Personajes.explotar(personaje);
	}
	
	
}
