package frameMenuPrincipal;


import javax.swing.JLayeredPane;

public class Frm__MP__02__02__LayeredPane__Opciones extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	private final int ancho = 600;
	private final int alto = 576;
	private final int posX = ( CONFIG__MP.getAncho() - ancho ) / 2;
	private final int posY = 15;

	Frm__MP__03__02__00__Panel__PanelOpcionesFondo panelFondo;
	Frm__MP__03__02__01__Panel__TituloOpciones panelTitulosOpciones;
	Frm__MP__03__02__02__Panel__Botones panelBotonesOpciones;
	Frm__MP__03__02__03__Panel__ImagenMapa panelCombinacionPartida;
	
	public Frm__MP__02__02__LayeredPane__Opciones() {
    	setOpaque(false);
		setLayout(null);
		
		panelFondo = new Frm__MP__03__02__00__Panel__PanelOpcionesFondo();
		panelBotonesOpciones = new Frm__MP__03__02__02__Panel__Botones();
		panelTitulosOpciones = new Frm__MP__03__02__01__Panel__TituloOpciones();
		panelCombinacionPartida = new Frm__MP__03__02__03__Panel__ImagenMapa();
		panelCombinacionPartida.setBounds(250, 340, 190, 190);
		
		add(panelFondo				, Integer.valueOf(0));
		add(panelBotonesOpciones	, Integer.valueOf(1));
		add(panelTitulosOpciones	, Integer.valueOf(2));
		add(panelCombinacionPartida	, Integer.valueOf(3));
		
    	setBounds(posX, posY, ancho, alto);
    	setVisible(false);
	}
	

	

	public String[] getOpcionesSeleccionadas() {
		return panelBotonesOpciones.getOpcionesSeleccionadas();
	}
	public String getOpcionVolumen() {
		return panelBotonesOpciones.getOpcionVolumen();
	}

	public void ponerImagen(int fila, int boton) {
		panelCombinacionPartida.ponerImagen(fila, boton);
	}

	public int getNumOpcionBotonDificultad() { 		return panelBotonesOpciones.getNumOpcionBotonDificultad(); }
	public int getNumOpcionBotonMapa() {		 		return panelBotonesOpciones.getNumOpcionBotonMapa(); }

	
}
