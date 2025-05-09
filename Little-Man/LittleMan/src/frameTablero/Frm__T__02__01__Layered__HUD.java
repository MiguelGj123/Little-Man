package frameTablero;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//Panel HUD que contiene los elementos informativos del juego: tiempo, vidas y puntuación.
public class Frm__T__02__01__Layered__HUD extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	private static Frm__T__02__01__Layered__HUD miLayeredPane;
	
	// CONTIENE:
    private Frm__T__03__01__00__Panel__Tiempo PanelTiempo;
    private Frm__T__03__01__01__Panel__Vidas vidaPanel;
    private Frm__T__03__01__02__Panel__Puntuacion puntuacionLabel;
    
    ArrayList<JPanel> paneles;
    
	
	private Frm__T__02__01__Layered__HUD() {
		paneles = new ArrayList<JPanel>();
		
		setBackground(Color.BLACK);
		setOpaque(true);
		setLayout(null);
		
		PanelTiempo = Frm__T__03__01__00__Panel__Tiempo.getPanelTiempo();
		PanelTiempo.setBounds(T_CFG.X_TIME, T_CFG.Y_TIME, T_CFG.ANCHO_BLQ_TIME, T_CFG.ALTO_BLQ_TIME);
		paneles.add(PanelTiempo);
		
		vidaPanel = new Frm__T__03__01__01__Panel__Vidas();
		vidaPanel.setBounds(T_CFG.X_BLQ_CORA, T_CFG.Y_BLQ_CORA, T_CFG.ANCHO_BLQ_CORA, T_CFG.ALTO_BLQ_CORA);
		paneles.add(vidaPanel);
		
		puntuacionLabel = Frm__T__03__01__02__Panel__Puntuacion.getHUDPuntuacion();
		puntuacionLabel.setBounds(T_CFG.X_PNTS, T_CFG.Y_PNTS, T_CFG.ANCHO_BLQ_PNTS, T_CFG.ALTO_BLQ_PNTS);
		paneles.add(puntuacionLabel);
	
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
	}
	
	public static Frm__T__02__01__Layered__HUD getPane() 
    {
        if (miLayeredPane == null) miLayeredPane = new Frm__T__02__01__Layered__HUD();
        return miLayeredPane;
    }
	
	public void ocultarHUD(){
		setVisible(false);
	}
	
	// Actualiza el contador visual de tiempo.
	public void gestionarTemporizador(int tiempo) {
		PanelTiempo.gestionarTemporizador(tiempo);
    }
    
	// Inicializa la cantidad máxima de vidas visibles en el HUD.
    public void inicializarVidas(int vidaMaximaInicial) {
        vidaPanel.inicializarVidas(vidaMaximaInicial);
    }
    
 // Añade o quita vidas en el HUD según el número actual.
    public void gestionarVidas(int vidas) 
    {
    	vidaPanel.actualizarVidas(vidas);
    }
    
 // Actualiza el valor mostrado de la puntuación.
    public void gestionarPuntuacion(int puntuacion) {
        puntuacionLabel.gestionarPuntuacion(puntuacion);
    }

	
}

