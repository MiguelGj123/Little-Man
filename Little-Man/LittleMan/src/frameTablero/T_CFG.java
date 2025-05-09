package frameTablero;

public class T_CFG {

	public static final int FILAS		= 11;
	public static final int COLUMNAS	= 17;
	public static final int PXL_LADO	= 45;
	
	// PANEL TABLERO
	public static final int ANCHO_PANE	= COLUMNAS * PXL_LADO;			// 765 PIXELS
	public static final int ALTO_PANE	= FILAS * PXL_LADO;				// 495 PIXELS
	
	// PANEL HUD
	public static final int ANCHO_HUD	= ANCHO_PANE;					// 765 PIXELS
	public static final int ALTO_HUD	= 55;
	
	// PANEL VENTANA
	public static final int ANCHO_VENTANA	= ANCHO_PANE;				// 765 PIXELS 
	public static final int ALTO_VENTANA	= ALTO_PANE + ALTO_HUD;		// 545 PIXELS
	
	// PANEL HUD
	// 	MARGENES DEL CONTENIDO
	private static final int MARGEN_X_HUD	= 10;
	private static final int MARGEN_Y_HUD	= 5;
	
	// PANEL HUD
	// 	DIMENSIONES DEL CONTENIDO
	private static final int ANCHO_CONT_HUD	= ANCHO_HUD - 2*MARGEN_X_HUD;
	private static final int ALTO_CONT_HUD	= ALTO_HUD - 2*MARGEN_Y_HUD;
	
	// PANEL HUD
	// BLOQUE DE LOS CORAZONES
	//	POSICION RESPECTO A PANEL HUD:
	public static final int X_BLQ_CORA		= MARGEN_X_HUD;
	public static final int Y_BLQ_CORA		= MARGEN_Y_HUD;
	// TAMAÑO QUE OCUPA CADA CORAZON
	public static final int PXL_CORA		= ALTO_CONT_HUD;
	private static final int MRGN_ENTR_CORA	= 10;
	public static final int DIST_ENTRE_CORA	= PXL_CORA + MRGN_ENTR_CORA;
	// DIMENSIONES DEL BLOQUE CORAZONES
	private static final int NUM_MAX_CORA	= 5;
	public static final int ANCHO_BLQ_CORA	= NUM_MAX_CORA * DIST_ENTRE_CORA;
	public static final int ALTO_BLQ_CORA	= PXL_CORA;
	
	// PANEL HUD
	// BLOQUE DE PUNTUACION
	// POSICION RESPECTO A PANEL HUD
	public static final int X_PNTS			= MARGEN_X_HUD + ANCHO_BLQ_CORA;
	public static final int Y_PNTS			= MARGEN_Y_HUD;
	// DIMENSIONES PUNTUACION
	public static final int ANCHO_BLQ_PNTS	= (ANCHO_CONT_HUD - ANCHO_BLQ_CORA)/2;
	public static final int ALTO_BLQ_PNTS	= PXL_CORA;
	
	// PANEL HUD
	// BLOQUE DE TIEMPO
	// POSICION RESPECTO A PANEL HUD
	public static final int X_TIME			= MARGEN_X_HUD + ANCHO_BLQ_CORA + ANCHO_BLQ_PNTS;
	public static final int Y_TIME			= MARGEN_Y_HUD;
	// DIMENSIONES PUNTUACION
	public static final int ANCHO_BLQ_TIME	= (ANCHO_CONT_HUD - ANCHO_BLQ_CORA)/2;
	public static final int ALTO_BLQ_TIME	= PXL_CORA;
	
	
	
	// PANEL MENU
	// DIMENSIONES MENU
	public static final int ANCHO_MENU	= 400;					// 765 PIXELS
	public static final int ALTO_MENU	= 300;
	// POSICION MENU
	public static final int X_MENU		= (ANCHO_VENTANA - ANCHO_MENU) / 2;
	public static final int Y_MENU		= (ALTO_VENTANA - ALTO_MENU) / 2;
	
	// PANEL MENU
	// DIMENSIONES BOTONES
	public static final int PXL_BTN_MENU		= ANCHO_MENU / 2;
	public static final int ALTO_TITULO_MENU	= ALTO_MENU - PXL_BTN_MENU;
	public static final int ANCHO_TITULO_MENU	= ANCHO_MENU;
	
	public static final int ALTO_BTNS_MENU		= PXL_BTN_MENU;
	public static final int ANCHO_BTNS_MENU		= ANCHO_MENU;
	
	public static final int X_TITULO_MENU		= 0;
	public static final int Y_TITULO_MENU		= 0;
	
	public static final int X_BTNS_MENU			= 0;
	public static final int Y_BTNS_MENU			= ALTO_TITULO_MENU;
	
	public static final int X_BTN1_MENU			= 0;
	public static final int Y_BTN1_MENU			= 0;
	
	public static final int X_BTN2_MENU			= PXL_BTN_MENU;
	public static final int Y_BTN2_MENU			= 0;
	
	
	
	
	
	public static final int X_PANE		= 0;
	public static final int Y_PANE		= ALTO_HUD;
	
	public static final int X_HUD		= 0;
	public static final int Y_HUD		= 0;
	
	public static final int X_VENTANA	= 0;
	public static final int Y_VENTANA	= 0;
	
	private T_CFG(){}
}
