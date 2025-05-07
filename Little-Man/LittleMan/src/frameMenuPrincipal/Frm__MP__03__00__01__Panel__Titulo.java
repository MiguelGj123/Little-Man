package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__00__01__Panel__Titulo  extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__00__01__Panel__Titulo() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__00__01__Label__Titulo());
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
	}
	

}
