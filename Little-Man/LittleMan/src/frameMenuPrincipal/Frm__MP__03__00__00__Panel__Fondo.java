package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__00__00__Panel__Fondo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__00__00__Panel__Fondo() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__00__00__Label__Fondo());
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	
	

}
