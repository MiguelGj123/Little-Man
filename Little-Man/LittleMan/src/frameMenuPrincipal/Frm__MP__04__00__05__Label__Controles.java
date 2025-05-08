package frameMenuPrincipal;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Frm__MP__04__00__05__Label__Controles  extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private final int alto = 175;
	private final int ancho = 1800;

	public Frm__MP__04__00__05__Label__Controles() {
		super("<html><div style='text-align:right;'>WASD: Mover<br>Espacio: Bomba<br>ESC: Pausa</div></html>", JLabel.CENTER);
    	setOpaque(false);
    	setLayout(null);
	    setFont(new Font("Monospaced", Font.BOLD, 22));
	    setForeground(Color.BLACK);
	    setVisible(true);
	    setBounds(0, 0, ancho, alto);
	}


}
