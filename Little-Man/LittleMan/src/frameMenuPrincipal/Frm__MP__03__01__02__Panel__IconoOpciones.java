package frameMenuPrincipal;

import javax.swing.JPanel;


public class Frm__MP__03__01__02__Panel__IconoOpciones extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__01__02__Panel__IconoOpciones() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__01__02__Label__IconoOpciones());
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
}
