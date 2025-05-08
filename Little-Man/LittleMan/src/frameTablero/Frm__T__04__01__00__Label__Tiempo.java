package frameTablero;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Frm__T__04__01__00__Label__Tiempo extends JLabel {


	private static final long serialVersionUID = 1L;

	public Frm__T__04__01__00__Label__Tiempo()
	{

		setFont(new Font("Monospaced", Font.BOLD, 24));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // algo de padding
    }
	
	public void gestionarTemporizador(int tiempo) 
	{
		setText("TIME: " + tiempo);
    }


}
