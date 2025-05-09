package frameMenuPrincipal;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Frm__MP__04__01__03__Label__Personaje_Texto  extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private final int alto = 1150;
	private final int ancho = 1000;

	public Frm__MP__04__01__03__Label__Personaje_Texto() {
		super("<html><div style='text-align:center;'>3 vidas y 10 bombas super de radio 1.<br>Versátil y equilibrado</div></html>", JLabel.CENTER);
    	setOpaque(false);
    	setLayout(null);
	    setFont(new Font("Monospaced", Font.BOLD, 22));
	    setForeground(Color.BLACK);
	    setVisible(true);
	    setBounds(0, 0, ancho, alto);
	}
	public void cambiarTexto(String texto) 
	{
	        setText(texto);
	}


}
