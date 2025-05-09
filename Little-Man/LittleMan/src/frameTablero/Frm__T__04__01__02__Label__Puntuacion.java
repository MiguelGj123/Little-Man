package frameTablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//JLabel que muestra la puntuación en texto, estilizado con fuente y color.
public class Frm__T__04__01__02__Label__Puntuacion extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public Frm__T__04__01__02__Label__Puntuacion()
	{
       setFont(new Font("Monospaced", Font.BOLD, 24));
       setForeground(Color.WHITE);
       setOpaque(false);
       setHorizontalAlignment(SwingConstants.CENTER);
       setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); 
       setPreferredSize(new Dimension(200, 45)); 
	}
	// Cambia el texto visible para mostrar la nueva puntuación.
	public void gestionarPuntuacion(int pPuntuacion) 
	{
       setText("Points: " + pPuntuacion);
	}
	
	public void resetPuntuacion() {
	       setText("Points: " + 0);
	}

}
