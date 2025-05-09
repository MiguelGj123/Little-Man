package frameTablero;

import javax.swing.JPanel;

//Panel encargado de mostrar la puntuación del jugador.
public class Frm__T__03__01__02__Panel__Puntuacion extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static Frm__T__03__01__02__Panel__Puntuacion puntuacion;
	
	
	private Frm__T__04__01__02__Label__Puntuacion labelPuntuacion;
	
	private Frm__T__03__01__02__Panel__Puntuacion() {
		setOpaque(false);
		setLayout(null);
		labelPuntuacion = new Frm__T__04__01__02__Label__Puntuacion();
		labelPuntuacion.setBounds(0, 0, T_CFG.ANCHO_BLQ_PNTS, T_CFG.ALTO_BLQ_PNTS);
		add(labelPuntuacion);
    	setVisible(true);
	}
	
	public static Frm__T__03__01__02__Panel__Puntuacion getHUDPuntuacion(){
		if (puntuacion == null) puntuacion = new Frm__T__03__01__02__Panel__Puntuacion();
		return puntuacion;
	}
	
	// Actualiza la puntuación mostrada con el valor actual.
	public void gestionarPuntuacion(int pPuntuacion){
		labelPuntuacion.gestionarPuntuacion(pPuntuacion);
	}


	public void resetPuntuacion() {
		labelPuntuacion.resetPuntuacion();
	}
    
}
