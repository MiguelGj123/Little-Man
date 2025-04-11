package frameTablero;

import javax.swing.JPanel;

public class Frm__Fondo__Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Frm__CONFIG config = Frm__CONFIG.getConfig();


	
    public Frm__Fondo__Panel(String param) {
    	setOpaque(false);
		setLayout(null); // para usar setBounds()

		Frm__Fondo__Label fondo = new Frm__Fondo__Label(param);
		fondo.setBounds(0, 0, config.getANCHO(), config.getALTO());

		add(fondo);
    }
}
