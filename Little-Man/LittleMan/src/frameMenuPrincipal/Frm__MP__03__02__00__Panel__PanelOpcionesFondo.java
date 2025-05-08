package frameMenuPrincipal;



import javax.swing.JPanel;


public class Frm__MP__03__02__00__Panel__PanelOpcionesFondo extends JPanel{

	private static final long serialVersionUID = 1L;
	Frm__MP__04__02__00__Label__PanelOpcionesFondo panelOpciones;
	
	public Frm__MP__03__02__00__Panel__PanelOpcionesFondo() {
    	setOpaque(false);
		setLayout(null);
		
		panelOpciones = new Frm__MP__04__02__00__Label__PanelOpcionesFondo();
		
		add(panelOpciones);
    	setBounds(0, 0, CONFIG__MENU_OPCIONES.getAncho(), CONFIG__MENU_OPCIONES.getAlto());
    	setVisible(true);
	}
	

}
