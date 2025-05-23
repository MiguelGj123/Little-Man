package frameMenuPrincipal;


import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//Panel que contiene los elementos de fondo: imagen, título, efectos, controles y enemigos.
public class Frm__MP__02__00__LayeredPane__Fondo extends JLayeredPane{

	private static final long serialVersionUID = 1L;
	
	Frm__MP__03__00__00__Panel__Fondo fondoPanel;
	Frm__MP__03__00__01__Panel__Titulo tituloPanel;
	Frm__MP__03__00__02__Panel__Explosion explosionPanel;
	Frm__MP__03__00__03__Panel__FondoBoss fondoBossPanel;
	Frm__MP__03__00__04__Panel__FondoEnemigos fondoEnemigosPanel;
	Frm__MP__03__00__05__Panel__Controles controlesPanel;
	
	Timer timerExplosion;
	
	ArrayList<JPanel> paneles;
	
	// Añade todos los elementos visuales del fondo al panel con su orden de visualización.
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
		
		controlesPanel = new Frm__MP__03__00__05__Panel__Controles();
		paneles.add(controlesPanel);
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	// Muestra el panel de explosión como efecto visual.
	public void explotar() {
		explosionPanel.setVisible(true);
	}
	
	// Oculta el panel de explosión.
	public void desExplotar() {
		explosionPanel.setVisible(false);
	}
}
