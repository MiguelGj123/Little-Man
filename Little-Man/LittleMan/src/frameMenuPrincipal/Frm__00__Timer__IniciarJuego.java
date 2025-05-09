package frameMenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import escenario.Escenario;


//Temporizador que lanza la partida después de que se confirme desde el menú.
//Maneja un retraso intencional tras el efecto visual de "explosión".
public class Frm__00__Timer__IniciarJuego extends Timer{
	
	private static final long serialVersionUID = 1L;
	
	private static Frm__00__Timer__IniciarJuego myTimer;
	private static final int tiempo = 1000;
	
	private static String playerTipo;
	private static String dificultad;
	private static String pantalla;
	private static boolean started;
	
	// Inicializa los parámetros del jugador y lanza el escenario si aún no ha comenzado.
	public static void iniciarPartida(String pPlayerTipo, String pDificultad, String pVolumen, String pPantalla) {
		playerTipo = pPlayerTipo;
		dificultad = pDificultad;
		pantalla = pPantalla;
		if (!started) {
			started = true;
			Frm__00__Frame_Principal.getMenuPrincipal().explotar();
			Escenario.getEscenario().iniciarVentana(playerTipo, pantalla, dificultad);
		}
	}
	
	// Inicia el temporizador (si no está ya en marcha) para lanzar la partida tras un retardo.
	public static void startTimer() {
		if (myTimer == null) myTimer = new Frm__00__Timer__IniciarJuego();
		if (!myTimer.isRunning()) myTimer.start();
	}
	
    private Frm__00__Timer__IniciarJuego() {			
    	super(tiempo, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
            	Escenario.getEscenario().iniciarPartida();
            	myTimer.stop(); 
            	started = false;
            }
        });
    	setRepeats(false);
    }
}






