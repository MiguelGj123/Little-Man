package frameMenuPrincipal;

import java.util.ArrayList;

import javax.swing.JLayeredPane;

//Panel principal que organiza y muestra los distintos subpaneles del menú (fondo, opciones, botones).
public class Frm__MP__01__LayeredPane_Principal extends JLayeredPane{

	private static final long serialVersionUID = 1L;
	
	private static Frm__MP__01__LayeredPane_Principal miPane;
	
	Frm__MP__02__00__LayeredPane__Fondo fondoLayeredPane;
	Frm__MP__02__01__LayeredPane__ElementosClickables elementosClickablesLayeredPane;
	Frm__MP__02__02__LayeredPane__Opciones opcionesLayeredPane;
	
	ArrayList<JLayeredPane> paneles;
	
	// Devuelve la instancia Singleton de este panel principal.
	public static Frm__MP__01__LayeredPane_Principal getPane() {
		if (miPane == null) miPane = new Frm__MP__01__LayeredPane_Principal();
		return miPane;
	}
	
	// Inicializa los paneles secundarios y los agrega con capas en el orden adecuado.
	private Frm__MP__01__LayeredPane_Principal() {
    	setOpaque(false);
		setLayout(null);
		
		paneles = new ArrayList<JLayeredPane>();
		
		fondoLayeredPane = new Frm__MP__02__00__LayeredPane__Fondo();
		paneles.add(fondoLayeredPane);
		
		elementosClickablesLayeredPane = new Frm__MP__02__01__LayeredPane__ElementosClickables();
		paneles.add(elementosClickablesLayeredPane);
		
		opcionesLayeredPane = new Frm__MP__02__02__LayeredPane__Opciones();
		paneles.add(opcionesLayeredPane);
		
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	
	
	public void explotar() { 						fondoLayeredPane.explotar(); }
	public void desExplotar() { 					fondoLayeredPane.desExplotar(); }
	public void seleccionar(int personaje) { 		elementosClickablesLayeredPane.seleccionar(personaje); }
	public void ponerImagenCombinadaMenuOpciones(int fila, int boton)  { opcionesLayeredPane.ponerImagen(fila, boton); }

	// Muestra u oculta el panel de opciones cuando se pulsa su icono.
	public void gestionarBotonOpciones() { 				opcionesLayeredPane.setVisible(!opcionesLayeredPane.isVisible()); }
	
	public boolean opcionesAbiertas() { 				return opcionesLayeredPane.isVisible(); }
	public String[] getOpcionesSeleccionadas() { 		return opcionesLayeredPane.getOpcionesSeleccionadas(); }
	public String getOpcionVolumen() { 		return opcionesLayeredPane.getOpcionVolumen(); }
	public String getJugadorSeleccionado() {			return elementosClickablesLayeredPane.getJugadorSeleccionado(); }
	public void rotarjugadorDerecha() {					elementosClickablesLayeredPane.rotarjugadorDerecha(); }
	public void rotarJugadorIzquierda() { 				elementosClickablesLayeredPane.rotarjugadorIzquierda(); }
	public int getNumOpcionBotonDificultad() { 			return opcionesLayeredPane.getNumOpcionBotonDificultad(); }
	public int getNumOpcionBotonMapa() {		 		return opcionesLayeredPane.getNumOpcionBotonMapa(); }
	
	
}
