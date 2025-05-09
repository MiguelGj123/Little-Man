package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__02__01__Panel__TituloOpciones extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__02__01__Panel__TituloOpciones() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__02__01__Label__TituloOpciones());
    	setBounds(0, 0, CONFIG__MENU_OPCIONES.getAncho(), CONFIG__MENU_OPCIONES.getAlto());
	}
	
	

}
