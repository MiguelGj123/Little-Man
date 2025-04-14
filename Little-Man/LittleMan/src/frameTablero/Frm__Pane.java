package frameTablero;

import javax.swing.JLayeredPane;

public class Frm__Pane extends JLayeredPane{
	private static final long serialVersionUID = 1L;
	private String stage;

	private static Frm__Pane frame;
	
	private Frm__Fondo__Panel fondo;
	private Frm__Bloques__Panel bloques;
	private Frm__Bombas__Panel bombas;
	private Frm__Enemigos__Panel enemigos;
	private Frm__Jugador__Panel jugador;
	
	private Frm__CONFIG config = Frm__CONFIG.getConfig();
	
	private Frm__Pane() {
		setLayout(null);
	}
	
	public static Frm__Pane getPane(){
		if (frame == null) frame = new Frm__Pane();
		return frame;
	}
		
	public Frm__Pane iniciarPane(String param) {
		stage=param;
		fondo = new Frm__Fondo__Panel(param);
		fondo.setBounds(0, 0, config.getANCHO(), config.getALTO());
		
		bloques = new Frm__Bloques__Panel(param);
		bloques.setBounds(0, 0, config.getANCHO(), config.getALTO());
		
		bombas = new Frm__Bombas__Panel();
		bombas.setBounds(0, 0, config.getANCHO(), config.getALTO());
		
		enemigos = new Frm__Enemigos__Panel();
		enemigos.setBounds(0, 0, config.getANCHO(), config.getALTO());
		
		jugador = new Frm__Jugador__Panel();
		jugador.setBounds(0, 0, config.getANCHO(), config.getALTO());
		
		add(fondo, Integer.valueOf(0));
		add(bloques, Integer.valueOf(1));
		add(bombas, Integer.valueOf(2));
		add(enemigos, Integer.valueOf(3));
		add(jugador, Integer.valueOf(4));
		return frame;
	}
	
	
	public void actualizarTablero(int[][][] res) {
		bloques.actualizarBloques(res[0],stage);
		bombas.actualizarBombas(res[1]);
		enemigos.actualizarEnemigos(res[2]);
		jugador.actualizarJugador(res[3]);
	}
}
