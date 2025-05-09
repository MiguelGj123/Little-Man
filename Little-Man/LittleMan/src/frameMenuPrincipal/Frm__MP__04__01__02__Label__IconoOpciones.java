package frameMenuPrincipal;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Frm__MP__04__01__02__Label__IconoOpciones extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private IconoOpcionesController miController;
	
	ImageIcon iconIconoOpciones = new ImageIcon("Pixels/Icono_opciones.png");
	
	private final String nombre = "icono";
	
	private final int ancho = 58;
	private final int alto = 58;
	private final int posX = 20;
	private final int posY = 20;
	
	public Frm__MP__04__01__02__Label__IconoOpciones() {
    	setOpaque(false);
		setLayout(null);
		
    	Image iconoOpcionesEscalado = iconIconoOpciones.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    	ImageIcon iconoOpcionesEscaladoIcon = new ImageIcon(iconoOpcionesEscalado);
    	
    	setIcon(iconoOpcionesEscaladoIcon);
    	setBounds(posX, posY, ancho, alto);
    	
		setName(nombre);
    	
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		miController = new IconoOpcionesController();
		addMouseListener(miController);
	}
	


	
	
	
	
	
	
	class IconoOpcionesController implements MouseListener {

		@Override public void mouseClicked(MouseEvent e) {
			Frm__00__Frame_Principal.getMenuPrincipal().gestionarBotonOpciones();
			Frm__00__Frame_Principal.getMenuPrincipal().hacerSonido("SELECT_MENU"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());

		}

		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
		
		
	}






	
	
	
	
	
	
	
	
	
	
	
	


}
