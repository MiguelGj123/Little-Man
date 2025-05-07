package frameTablero;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Frm__T__04__02__01__Label__MenuTitulo extends JLabel{

	private static final long serialVersionUID = 1L;

	public Frm__T__04__02__01__Label__MenuTitulo() {
		super("", JLabel.CENTER);
    	setOpaque(false);
	    setFont(new Font("Arial", Font.BOLD, 24));
	    setForeground(Color.WHITE);
	}
	public void cambiarTitulo(String texto) 
	{
	        setText(texto);
	}
}
