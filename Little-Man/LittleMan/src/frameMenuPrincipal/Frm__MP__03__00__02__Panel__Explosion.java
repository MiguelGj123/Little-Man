package frameMenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Frm__MP__03__00__02__Panel__Explosion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Frm__MP__03__00__02__Panel__Explosion() {
    	setOpaque(false);
		setLayout(null);
		
		add(new Frm__MP__04__00__02__Label__Explosion());
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(false);
	}
}
