package frameMenuPrincipal;

import javax.swing.JPanel;

public class Frm__MP__03__01__03__Panel__Personajes_Texto extends JPanel{

	private static final long serialVersionUID = 1L;
	Frm__MP__04__01__03__Label__Personaje_Texto textoPersonajes;
	
	public Frm__MP__03__01__03__Panel__Personajes_Texto() {
    	setOpaque(false);
		setLayout(null);
		
		textoPersonajes = new Frm__MP__04__01__03__Label__Personaje_Texto();
		add(textoPersonajes);
    	setBounds(0, 0, CONFIG__MP.getAncho(), CONFIG__MP.getAlto());
    	setVisible(true);
	}
	
	public void cambiarTexto(int personaje) {
		switch (personaje) {
		case 0:
			textoPersonajes.cambiarTexto("<html><div style='text-align:center;'>3 vidas y 10 bombas super de radio 1.<br>Versátil y equilibrado</div></html>");
			break;
		case 1:
			textoPersonajes.cambiarTexto("<html><div style='text-align:center;'>1 vida y 1 bomba ultra de radio máximo.<br>Alto riesgo, alta recompensa.</div></html>");
			break;
		case 2:
			textoPersonajes.cambiarTexto("<html><div style='text-align:center;'>3 vidas y 3 bombas hyper de radio 3 que decrece al ponerlas.<br>La estrategia es clave.</div></html>");
			break;
		case 3:
			textoPersonajes.cambiarTexto("<html><div style='text-align:center;'>2 vidas y 1 bomba mega de radio 3 a control remoto.<br>Perfecto para emboscadas, timing crucial.</div></html>");
			break;
		default:
			textoPersonajes.cambiarTexto("<html><div style='text-align:center;'>Versátil y equilibrado: 3 vidas, 10 bombas super de radio 1.<br>Ideal para todos.</div></html>");
			break;
		}
	}
	
	
}
