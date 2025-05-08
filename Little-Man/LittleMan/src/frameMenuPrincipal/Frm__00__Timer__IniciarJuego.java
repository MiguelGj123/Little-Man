package frameMenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import escenario.Escenario;



public class Frm__00__Timer__IniciarJuego extends Timer{
	
	private static final long serialVersionUID = 1L;
	
	private static Frm__00__Timer__IniciarJuego myTimer;
	private static final int tiempo = 1000;
	
	private static String playerTipo;
	private static String dificultad;
	private static String pantalla;
//	private static String volumen;
	private static boolean started;
	
	public static void iniciarPartida(String pPlayerTipo, String pDificultad, String pVolumen, String pPantalla) {
		playerTipo = pPlayerTipo;
		dificultad = pDificultad;
		pantalla = pPantalla;
//		volumen = pVolumen;
		if (!started) {
			started = true;
			Frm__00__Frame_Principal.getMenuPrincipal().explotar();
			Escenario.getEscenario().iniciarVentana(playerTipo, pantalla, dificultad);
		}
	}
	
	public static void startTimer() {
		if (myTimer == null) myTimer = new Frm__00__Timer__IniciarJuego();
		if (!myTimer.isRunning()) myTimer.start();
	}
	
    // Constructor privado para implementar patrón Singleton
    private Frm__00__Timer__IniciarJuego() {			
    	super(tiempo, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
            	Escenario.getEscenario().iniciarPartida();
            	myTimer.stop(); // Detener el timer después de ejecutarse
            	started = false;
            }
        });
    	setRepeats(false);
    }
}






