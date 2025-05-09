package frameMenuPrincipal;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Frm__MP__04__01__00__Label__Personajes_Exp extends JLabel{
	
	private static final long serialVersionUID = 1L;
	ImageIcon iconPersonajes_Exp = new ImageIcon("Pixels/blast.gif");
	
	private final int ancho = 150;
	private final int alto = 150;
	private static final int[][] posiciones = new int[][] {{85,295},{205,385},{653,380},{765,298}};
	
	private static final int cantPersonajes= posiciones.length;
	
	private Timer explosionTimer;
	
	public Frm__MP__04__01__00__Label__Personajes_Exp() {
    	setOpaque(false);
		setLayout(null);
		
		Image Personajes_ExpEscalado = iconPersonajes_Exp.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		ImageIcon Personajes_ExpEscaladoIcon = new ImageIcon(Personajes_ExpEscalado);
		
		setIcon(Personajes_ExpEscaladoIcon);
		setVisible(false);
	}

	public static int getCantPersonajes() {
		return cantPersonajes;
	}
	
	public void explotar(int personaje) {
		
        if (explosionTimer  != null && explosionTimer.isRunning()) {										// Cancelar cualquier timer previo antes de iniciar uno nuevo
        	explosionTimer.stop();
        }
        
        setBounds(posiciones[personaje][0], posiciones[personaje][1], ancho, alto);
        setVisible(true);

        explosionTimer = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	explosionTimer.stop(); // Detener el timer después de ejecutarse
            }
        });
        
        explosionTimer.setRepeats(false); // Solo se ejecuta una vez
        explosionTimer.start();
	}
}
