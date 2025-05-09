package frameTablero;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//Panel que contiene todos los elementos visuales del tablero: fondo, bloques, bombas, jugador, enemigos y power-ups.
public class Frm__T__02__00__Layered__Pane extends JLayeredPane{
	private static final long serialVersionUID = 1L;

	private static Frm__T__02__00__Layered__Pane frame;
	
	private int[][][] codigosTableroActual;
	
	
	// CONTIENE:
	private Frm__T__03__00__00__Panel__Fondo fondo;
	private Frm__T__03__00__01__Panel__Bloques bloques;
	private Frm__T__03__00__02__Panel__Bombas bombas;
	private Frm__T__03__00__03__Panel__Enemigos enemigos;
	private Frm__T__03__00__04__Panel__Jugador jugador;
	private Frm__T__03__00__05__Panel__PowerUp powerup;
	
	ArrayList<ActualizableImagen> panelesActualizables;
	
	private Frm__T__02__00__Layered__Pane() {
		setLayout(null);
	}
	
	public static Frm__T__02__00__Layered__Pane getPane(){
		if (frame == null) frame = new Frm__T__02__00__Layered__Pane();
		return frame;
	}
	
	// Inicializa y coloca todos los paneles gráficos del tablero en sus capas respectivas.
	public void iniciarPane() {
		removeAll();
		
		panelesActualizables = new ArrayList<ActualizableImagen>();
		
		fondo = new Frm__T__03__00__00__Panel__Fondo();
		fondo.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		add(fondo);
		
		bloques = new Frm__T__03__00__01__Panel__Bloques();
		bloques.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		panelesActualizables.add(bloques);
		
		bombas = new Frm__T__03__00__02__Panel__Bombas();
		bombas.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		panelesActualizables.add(bombas);
		
		enemigos = new Frm__T__03__00__03__Panel__Enemigos();
		enemigos.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		panelesActualizables.add(enemigos);
		
		jugador = new Frm__T__03__00__04__Panel__Jugador();
		jugador.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		panelesActualizables.add(jugador);
		
		powerup = new Frm__T__03__00__05__Panel__PowerUp();
		powerup.setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		panelesActualizables.add(powerup);
		
		codigosTableroActual = new int[panelesActualizables.size()][T_CFG.COLUMNAS][T_CFG.FILAS];
		
		for (int panel = 0; panel < panelesActualizables.size(); panel ++) {
			for (int columna = 0; columna < T_CFG.COLUMNAS; columna ++) {
				for (int fila = 0; fila < T_CFG.FILAS; fila ++) {
					codigosTableroActual[panel][columna][fila] = 0;
				}
			}
			add((JPanel)panelesActualizables.get(panel), Integer.valueOf(panel + 1));
		}
		setVisible(true);
	}
	
	// Compara la nueva matriz con la actual y actualiza solo las celdas que han cambiado.
	public void actualizarTablero(int[][][] res) {
		
		// Recorre todo el estado nuevo comprobando cambios
		for (int panel = 0; panel < res.length; panel ++)
		{
			for (int columna = 0; columna < res[panel].length; columna ++)
			{
				for (int fila = 0; fila < res[panel][columna].length; fila ++)
				{		// Comprueba si hay cambio de estado de la label, y la actualiza si lo hay
					if (codigosTableroActual[panel][columna][fila] != res[panel][columna][fila])
					{
						panelesActualizables.get(panel).actualizarImagen(res[panel][columna][fila], columna, fila);
						codigosTableroActual[panel][columna][fila] = res[panel][columna][fila];
					}
				}
			}
		}
	}
	
	// Cambia el fondo visual del tablero según el nombre proporcionado.
	public void iniciarFondo(String fondo)
	{
		this.fondo.iniciarFondo(fondo);
	}


}
