package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__02__03__Panel__ImagenMapa extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Frm__MP__04__02__03__Label__ImagenMapa labelImagen;
	
	public Frm__MP__03__02__03__Panel__ImagenMapa() {
		setOpaque(false);
		setLayout(null);
		
		labelImagen = new Frm__MP__04__02__03__Label__ImagenMapa();
		labelImagen.setBounds(0, 0, 190, 190);
		add(labelImagen);
		
		setVisible(true);
	}
	
	public void ponerImagen(int fila, int boton) {
		labelImagen.ponerImagen(fila, boton);
	}

}
