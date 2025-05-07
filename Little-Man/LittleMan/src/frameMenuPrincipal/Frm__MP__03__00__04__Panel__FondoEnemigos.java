package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__00__04__Panel__FondoEnemigos extends JPanel{

	private static final long serialVersionUID = 1L;
	Frm__MP__04__00__04__Label__FondoEnemigos[] Enemigos;
	
	public Frm__MP__03__00__04__Panel__FondoEnemigos() {
    	setOpaque(false);
		setLayout(null);
		
		Enemigos = new Frm__MP__04__00__04__Label__FondoEnemigos[6];
		
		for (int enemigo = 0; enemigo < 6; enemigo++) {
			Enemigos[enemigo] = new Frm__MP__04__00__04__Label__FondoEnemigos(enemigo);
			add(Enemigos[enemigo]);
		}
		
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	
}
