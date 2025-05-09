package frameTablero;

import java.util.ArrayList;

import javax.swing.JLayeredPane;

//Clase principal del tablero que contiene y coordina los paneles de juego, HUD y menú.
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
	
	// Inicializa los paneles secundarios del tablero.
	public void iniciarPaneles() {
		layeredPane.iniciarPane();
	}
	
	// Actualiza visualmente el tablero con la nueva matriz de imágenes.
	 public void gestionarMatrizCodigosImagenes(int[][][] res) {
		 layeredPane.actualizarTablero(res);
	 }
	 
	// Delegación: actualiza el tiempo restante en el HUD.
	 public void gestionarTemporizador(int tiempo) {
		layeredHUD.gestionarTemporizador(tiempo);
	 }
	 
	// Delegación: establece las vidas iniciales del jugador.
	 public void inicializarVidas(int vidaMaximaInicial) {
	    layeredHUD.inicializarVidas(vidaMaximaInicial);
	 }
	    
	// Delegación: actualiza el HUD con el número de vidas actual.
	public void gestionarVidas(int vidas)
	{
		layeredHUD.gestionarVidas(vidas);
	}
	 
	  public void iniciarFondo(String fondo)
	  {
		  layeredPane.iniciarFondo(fondo);
	  }
	  
	// Delegación: actualiza el HUD con la puntuación actual.
	  public void gestionarPuntuacion(int puntuacion)
	  {
		  layeredHUD.gestionarPuntuacion(puntuacion);
	  }

	// Muestra u oculta el menú del juego según el estado.
	public void mostrarMenu(String pMenu, Boolean visible) {
		layeredMenu.mostrarMenu(pMenu, visible);
		
	}
}
