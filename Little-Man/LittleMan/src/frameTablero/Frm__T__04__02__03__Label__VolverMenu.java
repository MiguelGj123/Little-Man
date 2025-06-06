package frameTablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import escenario.Escenario;

public class Frm__T__04__02__03__Label__VolverMenu extends JLabel {
	
private static final long serialVersionUID = 1L;
	
	public Frm__T__04__02__03__Label__VolverMenu() 
	{
		super("MENU", JLabel.CENTER);
    	setOpaque(false);
	    setFont(new Font("Arial", Font.BOLD, 24));
	    setForeground(Color.WHITE);
	    addMouseListener(new ControllerMenu());
	}
	
	
	class ControllerMenu implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Escenario.getEscenario().finalizarPartida(false);

		}

		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}

}
