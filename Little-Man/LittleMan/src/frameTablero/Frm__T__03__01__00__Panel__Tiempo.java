package frameTablero;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frameMenuPrincipal.CONFIG__MP;

public class Frm__T__03__01__00__Panel__Tiempo extends JPanel{
	private static final long serialVersionUID = 1L;

	private static Frm__T__03__01__00__Panel__Tiempo tiempo;
	
	
	// CONTIENE:
	private Frm__T__04__01__00__Label__Tiempo labelTiempo;
	
	private Frm__T__03__01__00__Panel__Tiempo() {

		setOpaque(false);
		setLayout(null);
		labelTiempo = new Frm__T__04__01__00__Label__Tiempo();
		labelTiempo.setBounds(0, 0, T_CFG.ANCHO_BLQ_TIME, T_CFG.ALTO_BLQ_TIME);
		add(labelTiempo);
    	setVisible(true);
	}
	
	public static Frm__T__03__01__00__Panel__Tiempo getPanelTiempo(){
		if (tiempo == null) tiempo = new Frm__T__03__01__00__Panel__Tiempo();
		return tiempo;
	}
	
	public void gestionarTemporizador(int tiempo) 
	{
		labelTiempo.gestionarTemporizador(tiempo);
    }
}