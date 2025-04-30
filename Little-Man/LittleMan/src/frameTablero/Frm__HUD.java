package frameTablero;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Frm__HUD extends JLayeredPane{
	private static final long serialVersionUID = 1L;
    private Frm__HUD__Tiempo tiempoLabel;
    private Frm__HUD__Vida vidaPanel;
    private Frm__HUD__Puntuacion puntuacionLabel;
    private JPanel HUD;

	private static Frm__HUD hud;
    private Frm__CONFIG config;
	
	private Frm__HUD() {
		setLayout(null);
	}
	
	public static Frm__HUD getHUD(){
		if (hud == null) hud = new Frm__HUD();
		return hud;
	}
		
	public JPanel iniciarHUD() {
		config = Frm__CONFIG.getConfig();
		tiempoLabel=Frm__HUD__Tiempo.getHUDTiempo();
		vidaPanel=Frm__HUD__Vida.getHUDVida();
		puntuacionLabel=Frm__HUD__Puntuacion.getHUDPuntuacion();
		HUD = new JPanel(new GridLayout(1, 3));
    	HUD.setBackground(Color.BLACK);
    	HUD.setPreferredSize(new Dimension(config.getANCHO(), 45));
    	HUD.add(vidaPanel.vidaEnPantalla());
    	HUD.add(puntuacionLabel.puntuacionEnPantalla());
    	HUD.add(tiempoLabel.tiempoEnPantalla());
    	hud.setEnabled(true);
		hud.setVisible(true);
		return HUD;
	}
	
	
	public void gestionarTemporizador(int tiempo) {
    	tiempoLabel.gestionarTemporizador(tiempo);
    }
    
    public void gestionarVida(String vidaStr) {
        vidaPanel.gestionarVida(vidaStr);
    }
    public void gestionarPuntuacion(Double puntuacion) {
        puntuacionLabel.gestionarPuntuacion(puntuacion);
    }

	public void resetHUD() {
	    tiempoLabel.resetTiempo();
	    vidaPanel.resetVida();
	    puntuacionLabel.resetPuntuacion();
	    HUD=null;
	    hud.removeAll();
	    hud.setEnabled(false);
		hud.setVisible(false);
	}
    
	
}

