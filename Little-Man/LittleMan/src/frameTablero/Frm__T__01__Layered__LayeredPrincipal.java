package frameTablero;

import java.util.ArrayList;

import javax.swing.JLayeredPane;

import frameMenuPrincipal.CONFIG__MP;

public class Frm__T__01__Layered__LayeredPrincipal extends JLayeredPane{

	private static final long serialVersionUID = 1L;
	private static Frm__T__01__Layered__LayeredPrincipal miPane;
	
	
	
	// CONTIENE:
	Frm__T__02__00__Layered__Pane layeredPane;
	Frm__T__02__01__Layered__HUD  layeredHUD;
	Frm__T__02__02__Layered__Menu layeredMenu;
	
	ArrayList<JLayeredPane> paneles;
	
	
	public static Frm__T__01__Layered__LayeredPrincipal getPane() {
		if (miPane == null) miPane = new Frm__T__01__Layered__LayeredPrincipal();
		return miPane;
	}
	
	
	private Frm__T__01__Layered__LayeredPrincipal() {
    	setOpaque(false);
		setLayout(null);
		
		paneles = new ArrayList<JLayeredPane>();
		
		layeredPane = Frm__T__02__00__Layered__Pane.getPane();
		layeredPane.setBounds(T_CFG.X_PANE, T_CFG.Y_PANE, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE);
		paneles.add(layeredPane);
		
		layeredHUD = Frm__T__02__01__Layered__HUD.getPane();
		layeredHUD.setBounds(T_CFG.X_HUD, T_CFG.Y_HUD, T_CFG.ANCHO_HUD, T_CFG.ALTO_HUD);
		paneles.add(layeredHUD);
		
		layeredMenu = Frm__T__02__02__Layered__Menu.getPane();
		layeredMenu.setBounds(T_CFG.X_MENU, T_CFG.Y_MENU, T_CFG.ANCHO_MENU, T_CFG.ALTO_MENU);
		paneles.add(layeredMenu);
		
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
		//cambiar los setBounds a la clase que los invoca
    	setBounds(0, 0, T_CFG.ANCHO_PANE, T_CFG.ALTO_PANE+T_CFG.ALTO_HUD);
    	setVisible(true);
	}
	
	public void iniciarPaneles() {
		layeredPane.iniciarPane();
	}
	
	
	 public void gestionarMatrizCodigosImagenes(int[][][] res) {
		 layeredPane.actualizarTablero(res);
	 }
	 
	 public void gestionarTemporizador(int tiempo) {
		layeredHUD.gestionarTemporizador(tiempo);
	 }
	 
	 public void inicializarVidas(int vidaMaximaInicial) {
	    layeredHUD.inicializarVidas(vidaMaximaInicial);
	 }
	    
	public void gestionarVidas(int vidas)
	{
		layeredHUD.gestionarVidas(vidas);
	}
	 
	  public void iniciarFondo(String fondo)
	  {
		  layeredPane.iniciarFondo(fondo);
	  }
	  
	  public void gestionarPuntuacion(int puntuacion)
	  {
		  layeredHUD.gestionarPuntuacion(puntuacion);
	  }


	public void mostrarMenu(String pMenu, Boolean visible) {
		layeredMenu.mostrarMenu(pMenu, visible);
		
	}
}
