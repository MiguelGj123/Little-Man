package frameTablero;

import javax.swing.JPanel;

public class Frm__T__03__00__00__Panel__Fondo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	
	// CONTIENE:
	private Frm__T__04__00__00__Label__Fondo labelFondo;

	
    public Frm__T__03__00__00__Panel__Fondo() {
    	setOpaque(false);
		setLayout(null); // para usar setBounds()

		labelFondo = new Frm__T__04__00__00__Label__Fondo();
		labelFondo.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);

		add(labelFondo);
    }
    
    public void iniciarFondo(String fondo)
    {
    	labelFondo.iniciarFondo(fondo);
    }
}
