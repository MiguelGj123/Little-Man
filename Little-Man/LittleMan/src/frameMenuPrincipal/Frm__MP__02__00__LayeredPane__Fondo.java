package frameMenuPrincipal;


import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frm__MP__02__00__LayeredPane__Fondo extends JLayeredPane{

	private static final long serialVersionUID = 1L;
	
	Frm__MP__03__00__00__Panel__Fondo fondoPanel;
	Frm__MP__03__00__01__Panel__Titulo tituloPanel;
	Frm__MP__03__00__02__Panel__Explosion explosionPanel;
	Frm__MP__03__00__03__Panel__FondoBoss fondoBossPanel;
	Frm__MP__03__00__04__Panel__FondoEnemigos fondoEnemigosPanel;
	Frm__MP__03__00__05__Panel__Controles controles;
	
	Timer timerExplosion;
	
	ArrayList<JPanel> paneles;
	
	public Frm__MP__02__00__LayeredPane__Fondo() {
    	setOpaque(false);
		setLayout(null);
		
		paneles = new ArrayList<JPanel>();
		
		fondoPanel = new Frm__MP__03__00__00__Panel__Fondo();
		paneles.add(fondoPanel);
		
		tituloPanel = new Frm__MP__03__00__01__Panel__Titulo();
		paneles.add(tituloPanel);
		
		explosionPanel = new Frm__MP__03__00__02__Panel__Explosion();
		paneles.add(explosionPanel);
		
		fondoBossPanel = new Frm__MP__03__00__03__Panel__FondoBoss();
		paneles.add(fondoBossPanel);
		
		fondoEnemigosPanel = new Frm__MP__03__00__04__Panel__FondoEnemigos();
		paneles.add(fondoEnemigosPanel);
		
		controles = new Frm__MP__03__00__05__Panel__Controles();
		paneles.add(controles);
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
		//cambiar los setBounds a la clase que los invoca
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	public void explotar() {
		explosionPanel.setVisible(true);
	}
	
	public void desExplotar() {
		explosionPanel.setVisible(false);
	}
}
