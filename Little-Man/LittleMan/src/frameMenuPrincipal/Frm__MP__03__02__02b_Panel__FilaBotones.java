package frameMenuPrincipal;


import javax.swing.ButtonGroup;
import javax.swing.JPanel;


public class Frm__MP__03__02__02b_Panel__FilaBotones extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	Frm__MP__04__02__02__ToggleButton__Botones[] botones;
	ButtonGroup grupo;
	

	public Frm__MP__03__02__02b_Panel__FilaBotones(int fila) {
    	setOpaque(false);
		setLayout(null);
		
		botones = new Frm__MP__04__02__02__ToggleButton__Botones[4];
		grupo = new ButtonGroup();
        
        for (int boton = 0; boton < 4; boton++) {
			botones[boton] = new Frm__MP__04__02__02__ToggleButton__Botones(fila, boton);
			botones[boton].setBounds(boton * (63), 0, 63, 51); // posicionado desde el panel
			grupo.add(botones[boton]);
			add(botones[boton]);
		}
		
		setBounds(135, 160+51*fila, CONFIG__MENU_OPCIONES.getAlto(), CONFIG__MENU_OPCIONES.getAncho());
	}


	public String getOpcionSeleccionada() {
		for (int i = 0; i < botones.length; i++) {
			if (botones[i].isSelected()) return botones[i].getName();
		}
		return "";
	}
	public String getOpcionVolumen() {
		for (int i = 0; i < botones.length; i++) {
			if (botones[i].isSelected()) return botones[i].getName();
		}
		return "";
	}


	public int getNumOpcionBotonSeleccionada() {
		for (int i = 0; i < botones.length; i++) {
			if (botones[i].isSelected()) return i;
		}
		return 1;
	}
}
