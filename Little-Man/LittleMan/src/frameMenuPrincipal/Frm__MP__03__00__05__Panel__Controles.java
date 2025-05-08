package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__00__05__Panel__Controles extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__00__05__Panel__Controles() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__00__05__Label__Controles());
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	
	

}
