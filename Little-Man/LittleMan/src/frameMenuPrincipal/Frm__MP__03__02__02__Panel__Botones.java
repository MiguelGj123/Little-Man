package frameMenuPrincipal;

import javax.swing.JPanel;


public class Frm__MP__03__02__02__Panel__Botones extends JPanel{

	private static final long serialVersionUID = 1L;
	private Frm__MP__03__02__02b_Panel__FilaBotones[] filaBotones;
	
	public Frm__MP__03__02__02__Panel__Botones() {
    	setOpaque(false);
		setLayout(null);
		
		filaBotones = new Frm__MP__03__02__02b_Panel__FilaBotones[3];
		
		for (int fila = 0; fila < 3; fila++) {
			filaBotones[fila] = new Frm__MP__03__02__02b_Panel__FilaBotones(fila);
			add(filaBotones[fila]);
		}
		
    	setBounds(0, 0, CONFIG__MENU_OPCIONES.getAncho(), CONFIG__MENU_OPCIONES.getAlto());
	}
	
	public String[] getOpcionesSeleccionadas() {
		String[] opciones = new String[filaBotones.length];
		
		for (int i = 0; i < filaBotones.length; i++) {
			opciones[i] = filaBotones[i].getOpcionSeleccionada();
		}
		
		return opciones;
	}
	public String getOpcionVolumen() {
		String opcion;
		

			opcion = filaBotones[1].getOpcionVolumen();
		
		return opcion;
	}
	
	public int getNumOpcionBotonDificultad() { 			return filaBotones[0].getNumOpcionBotonSeleccionada(); }
	public int getNumOpcionBotonMapa() {		 		return filaBotones[2].getNumOpcionBotonSeleccionada(); }

}
