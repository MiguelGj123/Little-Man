package frameTablero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Frm__T__03__02__00__Panel__MenuFondo extends JPanel {

    private static final long serialVersionUID = 1L;

	public Frm__T__03__02__00__Panel__MenuFondo() 
	{
		 setOpaque(false); // IMPORTANTE para permitir transparencia
		 setLayout(null);
		 setVisible(true);
	}
	
	 @Override
	    protected void paintComponent(Graphics g) 
	 {
	     
	       super.paintComponent(g);
	       Graphics2D g2d = (Graphics2D) g.create();
	       g2d.setColor(new Color(0, 0, 0, 150));
	       g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
	       g2d.dispose();
	 }
	  
}
