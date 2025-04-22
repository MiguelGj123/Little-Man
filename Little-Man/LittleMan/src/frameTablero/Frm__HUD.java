package frameTablero;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Frm__HUD extends JLayeredPane{
	private static final long serialVersionUID = 1L;
    private Frm__HUD__Tiempo tiempoLabel;
    private Frm__HUD__Vida vidaPanel;
    private JPanel HUD;

	private static Frm__HUD hud;
    private Frm__CONFIG config;
	
	private Frm__HUD() {
		config = Frm__CONFIG.getConfig();
		tiempoLabel=Frm__HUD__Tiempo.getHUDTiempo();
		vidaPanel=Frm__HUD__Vida.getHUDVida();
		setLayout(null);
	}
	
	public static Frm__HUD getHUD(){
		if (hud == null) hud = new Frm__HUD();
		return hud;
	}
		
	public JPanel iniciarHUD() {
		HUD = new JPanel(new BorderLayout());
    	HUD.setBackground(Color.BLACK);
    	HUD.setPreferredSize(new Dimension(config.getANCHO(), 45));
    	tiempoLabel.tiempoEnPantalla(HUD);
    	vidaPanel.vidaEnPantalla(HUD);
		return HUD;
	}
	
	
	public void gestionarTemporizador(int tiempo) {
    	tiempoLabel.gestionarTemporizador(tiempo);
    }
    
    public void gestionarVida(String vidaStr) {
        vidaPanel.gestionarVida(vidaStr);
    }
    
	
}

